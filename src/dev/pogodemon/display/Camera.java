package dev.pogodemon.display;

import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

public class Camera
{
    private Handler handler;
    private float xOffset, yOffset;

    public Camera(Handler handler, float xOffset, float yOffset)
    {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace()
    {
        if (xOffset < 0)
            xOffset = 0;

        else if (xOffset > handler.getWorld().getWidth() * Tile.SIZE - handler.getWidth())
            xOffset = handler.getWorld().getWidth() * Tile.SIZE - handler.getWidth();

        if (yOffset < 0)
            yOffset = 0;

        else if (yOffset > handler.getWorld().getHeight() * Tile.SIZE - handler.getHeight())
            yOffset = handler.getWorld().getHeight() * Tile.SIZE - handler.getHeight();
    }

    public void centerOnEntity(Entity e)
    {
        xOffset = e.getX() - handler.getWidth() * 0.5f;
        yOffset = e.getY() - handler.getHeight() * 0.6f;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt)
    {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
