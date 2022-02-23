package dev.pogodemon.entities;

import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager
{
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;

    public EntityManager(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
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

    public void setPlayer(Player player) {
        this.player = player;
    }
}
