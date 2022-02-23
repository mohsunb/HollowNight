package dev.pogodemon.entities;

import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity
{
    public int health = 1;
    protected boolean exists = true;
    protected Handler handler;
    protected float x, y;
    protected float width, height;
    public Rectangle bounds;
    public boolean is_pogoable = false;
    public boolean has_knockback = true;
    protected boolean gravity = true;

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
        return respawnX;
    }

    public float getRespawnY()
    {
        return respawnY;
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

    public ArrayList<Entity> getCollidingEntities(float xOffset, float yOffset)
    {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if (e.equals(handler.getWorld().getEntityManager().getPlayer()) || e.equals(this) || !e.doesExist())
                continue;

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                entities.add(e);
        }
        return entities;
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

    public boolean hasGravity()
    {
        return gravity;
    }

    public void enableGravity()
    {
        if (!gravity)
            gravity = true;
    }

    public void disableGravity()
    {
        if (gravity)
            gravity = false;
    }

    public void toggleGravity()
    {
        gravity = !gravity;
    }

    public abstract void update();

    public abstract void render(Graphics gfx);

    public abstract void hasBeenHit();

    public abstract void playerContact();
}
