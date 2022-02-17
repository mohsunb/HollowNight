package dev.pogodemon.world;

import dev.pogodemon.entities.Entity;
import dev.pogodemon.entities.EntityManager;
import dev.pogodemon.entities.creatures.Player;
import dev.pogodemon.utils.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class World
{
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private EntityManager entityManager;

    public World(Handler handler, String path, int spawnX, int spawnY)
    {
        try
        {
            this.width = ImageIO.read(new File(path)).getWidth();
            this.height = ImageIO.read(new File(path)).getHeight();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));

        loadWorldFromImage(path, spawnX, spawnY);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void addEntity(Entity e)
    {
        entityManager.addEntity(e);
    }

    public void update()
    {
        entityManager.update();
    }

    public void render(Graphics gfx)
    {
        int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++)
        {
            for (int x = xStart; x < xEnd; x++)
            {
                getTile(x, y).render(gfx, (int) (x * Tile.TILE_WIDTH - handler.getCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getCamera().getyOffset()));
            }
        }

        entityManager.render(gfx);
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


    private void loadWorldFromImage(String ImageName, int spawnX, int spawnY)
    {
        /*
        The input image has to be grayscale (8-bit color)
         */

        this.spawnX = spawnX;
        this.spawnY = spawnY;
        DataBufferByte data = null;
        try
        {
            File imgPath = new File(ImageName);
            BufferedImage bufferedImage = ImageIO.read(imgPath);
            WritableRaster raster = bufferedImage.getRaster();
            data = (DataBufferByte) raster.getDataBuffer();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

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
