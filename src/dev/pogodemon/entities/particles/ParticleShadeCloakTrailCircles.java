package dev.pogodemon.entities.particles;

import dev.pogodemon.Launcher;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.Random;

public class ParticleShadeCloakTrailCircles extends Entity
{
    private final float xMove, yMove;
    private final float xOffset, yOffset;
    private float size;

    public ParticleShadeCloakTrailCircles(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);
        Random rand = new Random();
        size = rand.nextInt(30, 51);

        xOffset = rand.nextInt(-20, 21);
        yOffset = rand.nextInt(-30, 31);

        xMove = (float) (rand.nextInt(0, 101) * 0.01F * Creature.DEFAULT_SPEED * 0.05F * Math.pow(-1, rand.nextInt(0, 2)));
        yMove = (float) (rand.nextInt(0, 101) * 0.01F * Creature.DEFAULT_SPEED * 0.05F * Math.pow(-1, rand.nextInt(0, 2)));
    }

    @Override
    public void update()
    {
        if (size <= 0)
            handler.getWorld().removeEntity(this);

        else
            size -= (45.0F / Launcher.framerate_limit);

        x += xMove;
        y += yMove;
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
