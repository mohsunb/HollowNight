package dev.pogodemon.world;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.ImageLoader;
import dev.pogodemon.entities.CameraFocusPoint;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.entities.EntityManager;
import dev.pogodemon.entities.Player;
import dev.pogodemon.utils.Coordinate;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;

import java.awt.*;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

public class World
{
    public static final int NEUTRAL = 0;
    public static final int BENCHED = 1;
    public static final int SLEEPING = 2;

    private Handler handler;
    private int width, height;
    private int[][] tiles;
    private EntityManager entityManager;
    private int map_id;

    //Transitions
    public World(Handler handler, int map_id, int entrance_id)
    {
        this.width = ImageLoader.loadImage(MapHelper.path(map_id)).getWidth();
        this.height = ImageLoader.loadImage(MapHelper.path(map_id)).getHeight();
        this.map_id = map_id;

        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 0, 0), new CameraFocusPoint(handler));

        loadWorldFromImage(MapHelper.path(map_id));

        entityManager.getPlayer().setX(MapHelper.coordinate(map_id, entrance_id).getX());
        entityManager.getPlayer().setY(MapHelper.coordinate(map_id, entrance_id).getY());
        entityManager.getPlayerCamera().setX(MapHelper.coordinate(map_id, entrance_id).getX() + entityManager.getPlayer().bounds.x + entityManager.getPlayer().bounds.width * 0.5F);
        entityManager.getPlayerCamera().setY(MapHelper.coordinate(map_id, entrance_id).getY() + entityManager.getPlayer().bounds.y + entityManager.getPlayer().bounds.height * 0.5F);
    }

    // Benched / sleeping (no autowakeup)
    public World(Handler handler, int map_id, Coordinate spawn, int spawn_status)
    {
        this.width = ImageLoader.loadImage(MapHelper.path(map_id)).getWidth();
        this.height = ImageLoader.loadImage(MapHelper.path(map_id)).getHeight();
        this.map_id = map_id;

        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 0, 0), new CameraFocusPoint(handler));

        loadWorldFromImage(MapHelper.path(map_id));

        if (spawn_status == BENCHED)
        {
            entityManager.getPlayer().benched = true;
            entityManager.getPlayer().bench_target_x = spawn.getX();
            entityManager.getPlayer().bench_target_y = spawn.getY();
        }

        if (spawn_status == SLEEPING)
        {
            entityManager.getPlayer().sleeping = true;
            entityManager.getPlayer().waking_up = false;
        }

        entityManager.getPlayer().setX(spawn.getX());
        entityManager.getPlayer().setY(spawn.getY());
        entityManager.getPlayerCamera().setX(spawn.getX() + entityManager.getPlayer().bounds.x + entityManager.getPlayer().bounds.width * 0.5F);
        entityManager.getPlayerCamera().setY(spawn.getY() + entityManager.getPlayer().bounds.y + entityManager.getPlayer().bounds.height * 0.5F);
    }

    // Benched / sleeping + autowakeup
    public World(Handler handler, int map_id, Coordinate spawn, int spawn_status, boolean autowakeup)
    {
        this.width = ImageLoader.loadImage(MapHelper.path(map_id)).getWidth();
        this.height = ImageLoader.loadImage(MapHelper.path(map_id)).getHeight();
        this.map_id = map_id;

        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 0, 0), new CameraFocusPoint(handler));

        loadWorldFromImage(MapHelper.path(map_id));

        if (spawn_status == BENCHED)
        {
            entityManager.getPlayer().benched = true;
            entityManager.getPlayer().bench_target_x = spawn.getX();
            entityManager.getPlayer().bench_target_y = spawn.getY();
        }

        if (spawn_status == SLEEPING)
        {
            entityManager.getPlayer().sleeping = true;
            entityManager.getPlayer().waking_up = autowakeup;
        }

        entityManager.getPlayer().setX(spawn.getX());
        entityManager.getPlayer().setY(spawn.getY());
        entityManager.getPlayerCamera().setX(spawn.getX() + entityManager.getPlayer().bounds.x + entityManager.getPlayer().bounds.width * 0.5F);
        entityManager.getPlayerCamera().setY(spawn.getY() + entityManager.getPlayer().bounds.y + entityManager.getPlayer().bounds.height * 0.5F);
    }

    public int getID()
    {
        return map_id;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void spawnEntity(Entity e)
    {
        ArrayList<Entity> list = new ArrayList<Entity>(getEntityManager().getEntities());
        list.add(e);
        getEntityManager().setEntities(list);
    }

    public void removeEntity(Entity e)
    {
        ArrayList<Entity> list = new ArrayList<Entity>(getEntityManager().getEntities());
        for (Entity et : getEntityManager().getEntities())
        {
            if (et.equals(e))
            {
                list.remove(e);
                break;
            }
        }
        getEntityManager().setEntities(list);
    }

    public void update()
    {
        entityManager.update();
    }

    public void render(Graphics2D gfx)
    {
        entityManager.render(gfx);

        if (Launcher.show_world_bounds)
        {
            int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.SIZE);
            int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.SIZE + 1);
            int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.SIZE);
            int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.SIZE + 1);

            for (int y = yStart; y < yEnd; y++)
            {
                for (int x = xStart; x < xEnd; x++)
                {
                    if (getTile(x, y).isSolid())
                        getTile(x, y).render(gfx, (int) (x * Tile.SIZE - handler.getCamera().getxOffset()), (int) (y * Tile.SIZE - handler.getCamera().getyOffset()));
                }
            }
        }
    }

    public Tile getTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.air;

        Tile tile = Tile.tiles[tiles[x][y]];
        if (tile == null)
            return Tile.air;
        return tile;
    }


    private void loadWorldFromImage(String path)
    {
        /*
        The input image has to be grayscale (8-bit color)
         */

        DataBufferByte data = (DataBufferByte) ImageLoader.loadImage(path).getRaster().getDataBuffer();
        int[] map_data = new int[data.getData().length];

        for (int i = 0; i < map_data.length; i++)
        {
            if (data.getData()[i] == 0) // 0 -> black pixel; -1 -> white pixel;
                map_data[i] = 1;

            else
                map_data[i] = 0;
        }

        tiles = new int[width][height];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                tiles[x][y] = map_data[x + y * width];
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
