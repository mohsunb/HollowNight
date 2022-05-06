package dev.pogodemon.entities.particles;

import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.entities.Player;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.Random;

public class ParticleShadeAbsorb extends Entity
{
    public ParticleShadeAbsorb(Handler handler, float x, float y)
    {
        super(handler, 0, 0, 0, 0);

        Random rand = new Random();

        bounds.width = rand.nextInt(10, 31);
        bounds.height = bounds.width;

        float xOffset = rand.nextInt(-40, 41);
        float yOffset = rand.nextInt(-40, 41);

        setX(x + xOffset);
        setY(y + yOffset);
    }

    @Override
    public void update()
    {
        float dx = handler.getWorld().getEntityManager().getPlayer().getCenterX() - getCenterX();
        float dy = handler.getWorld().getEntityManager().getPlayer().getCenterY() - getCenterY();
        float d = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        float speed = Creature.DEFAULT_SPEED * 2;

        x += dx / d * speed;
        y += dy / d * speed;
    }

    @Override
    public int renderRank()
    {
        return 3;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.setColor(Color.black);
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
        handler.getWorld().removeEntity(this);
    }
}
