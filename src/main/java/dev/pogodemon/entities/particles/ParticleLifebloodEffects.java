package dev.pogodemon.entities.particles;

import dev.pogodemon.Launcher;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.Random;

public class ParticleLifebloodEffects extends Entity
{
    private float iX, iY;
    private final float iiX, iiY;

    private int timer = 0;

    public ParticleLifebloodEffects(Handler handler, float x, float y)
    {
        super(handler, 0, 0, 0, 0);
        Random rand = new Random();

        bounds.width = rand.nextInt(10, 61);
        bounds.height = bounds.width;

        float xOffset = rand.nextInt(-40, 41);
        float yOffset = rand.nextInt(-40, 41);

        iX = (float) (rand.nextInt(1, 6) * Creature.DEFAULT_SPEED / 10 * Math.pow(-1, rand.nextInt(0, 2)));
        iY = (float) (rand.nextInt(1, 6) * Creature.DEFAULT_SPEED / 10 * Math.pow(-1, rand.nextInt(0, 2)));
        iiX = iX;
        iiY = iY;

        setX(x + xOffset);
        setY(y + yOffset);
    }

    @Override
    public void update()
    {
        if (timer++ >= Launcher.framerate_limit)
        {
            float dx = handler.getWorld().getEntityManager().getPlayer().getCenterX() - getCenterX();
            float dy = handler.getWorld().getEntityManager().getPlayer().getCenterY() - getCenterY();
            float d = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            float speed = Creature.DEFAULT_SPEED * 2;

            x += dx / d * speed;
            y += dy / d * speed;
        }

        else
        {
            x += iX;
            y += iY;

            iX -= iiX / Launcher.framerate_limit;
            iY -= iiY / Launcher.framerate_limit;
        }
    }

    @Override
    public int renderRank()
    {
        return 5;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.setColor(Colors.lifeblood);
        gfx.fillOval((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
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
        if (timer >= Launcher.framerate_limit)
            handler.getWorld().removeEntity(this);
    }
}
