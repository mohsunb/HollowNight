package dev.pogodemon.entities.particles;

import dev.pogodemon.Game;
import dev.pogodemon.Launcher;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.Random;

public class ParticleLowHealth extends Entity
{
    private final Random rand = new Random();
    private float xOffset, yOffset, size;

    public ParticleLowHealth(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);
        size = rand.nextInt(30, 51);
        xOffset = rand.nextInt(-20, 21);
        yOffset = rand.nextInt(-30, 31);
    }

    @Override
    public void update()
    {
        if (size <= 0)
            handler.getWorld().removeEntity(this);

        else
        {
            size -= (30.0F / Launcher.framerate_limit);
            yOffset -= (200.0F / Launcher.framerate_limit);
        }
    }

    @Override
    public int renderRank()
    {
        return -1;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.setColor(Color.black);
        gfx.fillOval((int) (getCenterX() + xOffset - handler.getCamera().getxOffset() - size * 0.5), (int) (getCenterY() + yOffset - handler.getCamera().getyOffset() - size * 0.5), Math.round(size), Math.round(size));
    }

    @Override
    public void hasBeenHit()
    {

    }

    @Override
    public void fireballHit()
    {

    }

    @Override
    public void playerContact()
    {

    }
}
