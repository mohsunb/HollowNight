package dev.pogodemon.entities;

import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager
{
    private Handler handler;
    private Player player;
    private CameraFocusPoint cam;
    private ArrayList<Entity> entities;
    private final Comparator<Entity> renderSorter = new Comparator<Entity>()
    {
        @Override
        public int compare(Entity o1, Entity o2)
        {
            if (o1.renderRank() < o2.renderRank())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player, CameraFocusPoint cam)
    {
        this.handler = handler;
        this.player = player;
        this.cam = cam;
        entities = new ArrayList<Entity>();
        addEntity(player);
        addEntity(cam);
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    public void setEntities(ArrayList<Entity> e)
    {
        entities = e;
    }

    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    public void update()
    {
        for (Entity e : entities)
        {
            if (e.equals(cam))
                continue;
            e.update();
        }

        entities.sort(renderSorter);
    }

    public void render(Graphics2D gfx)
    {
        for (Entity e : entities)
            e.render(gfx);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public CameraFocusPoint getPlayerCamera()
    {
        return cam;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
