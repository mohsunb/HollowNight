package dev.pogodemon.entities.particles;

import dev.pogodemon.Launcher;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class ParticleHit extends Entity
{
    private int counter = 0;
    private final Color color;
    private final int size;

    public ParticleHit(Handler handler, Color color, float x, float y)
    {
        super(handler, x, y, 0, 0);
        this.color = color;
        size = 200;
    }

    public ParticleHit(Handler handler, Color color, float x, float y, int size)
    {
        super(handler, x, y, 0, 0);
        this.color = color;
        this.size = size;
    }

    @Override
    public void update()
    {
        if (++counter >= Launcher.framerate_limit / 3F)
            handler.getWorld().removeEntity(this);
    }

    @Override
    public int renderRank()
    {
        return -1;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        float rate = counter / (Launcher.framerate_limit / 3F);
        gfx.setColor(color);
        gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - rate));
        gfx.fillOval((int) (getCenterX() - handler.getCamera().getxOffset() - size * 0.5 - size * rate), (int) (getCenterY() - handler.getCamera().getyOffset() - size * 0.5 - size * rate), (int) (size + 2 * size * rate), (int) (size + 2 * size * rate));
        gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
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
