package dev.pogodemon.entities;

import dev.pogodemon.utils.Handler;

import java.awt.*;

public abstract class Entity
{
    public int health;
    protected boolean exists = true;
    protected Handler handler;
    protected float x, y;
    protected float width, height;
    public Rectangle bounds;

    public boolean is_harmful = false; //by default all entities don't do contact damage
    public boolean is_hazard = false;
    public boolean is_hazard_respawn = false;
    public boolean is_pogoable = false;
    public boolean has_knockback = true;

    public Entity(Handler handler, float x, float y, float width, float height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, (int) width, (int) height);
    }

    //Hazard respawn points
    protected float respawnX = 0;
    protected float respawnY = 0;

    public float getRespawnX()
    {
        if (is_hazard_respawn)
            return respawnX;
        else
            return 0;
    }

    public float getRespawnY()
    {
        if (is_hazard_respawn)
            return respawnY;
        else
            return 0;
    }

    public void removeEntity()
    {
        exists = false;
    }

    public boolean doesExist()
    {
        return exists;
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset)
    {
        for (Entity e : handler.getWorld().getEntityManager().getEntities())
        {
           if (e.equals(handler.getWorld().getEntityManager().getPlayer()) || e.equals(this) || !e.doesExist())
                continue;

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }

        return false;
    }

    public Entity getCollidingEntity(float xOffset, float yOffset)
    {
        Entity entity = null;
        for (Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if (e.equals(handler.getWorld().getEntityManager().getPlayer()) || e.equals(this) || !e.doesExist())
                continue;

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
            {
                entity = e;
                break;
            }
        }
        return entity;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset)
    {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public abstract void update();

    public abstract void render(Graphics gfx);

    public abstract void hasBeenHit();
}
