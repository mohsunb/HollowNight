package dev.pogodemon.entities;

import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager
{
    private Handler handler;
    private Player player;
    private CameraFocusPoint cam;
    private ArrayList<Entity> entities;

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
            e.update();
    }

    public void render(Graphics gfx)
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
