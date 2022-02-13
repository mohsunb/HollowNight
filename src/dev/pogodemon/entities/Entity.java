package dev.pogodemon.entities;

import dev.pogodemon.Game;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public abstract class Entity
{
    protected Handler handler;
    protected float x, y;
    protected float width, height;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, float width, float height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0, 0, (int) width, (int) height);
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
}
