package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.particles.ParticleGeoCollect;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.Random;

public class Geo extends Creature
{
    private boolean can_be_collected = false;
    private int size = 0; // 0 -> 1 geo; 1 -> 5; 2 -> 25;

    public Geo(Handler handler, float x, float y, int size)
    {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.size = size;
        CREATURE_TYPE = 2;
        has_knockback = false;
        can_be_killed = false;

        if (size == 1)
        {
            bounds.x = 5;
            bounds.y = 5;
            bounds.width = 30;
            bounds.height = 45;
        }

        else if (size == 2)
        {
            bounds.x = 5;
            bounds.y = 5;
            bounds.width = 45;
            bounds.height = 45;
        }

        else
        {
            bounds.x = 5;
            bounds.y = 5;
            bounds.width = 30;
            bounds.height = 30;
        }

        Random rand = new Random();
        speedX = DEFAULT_SPEED;
        xMove = (float) Math.floor(Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(0, 101) * 0.01 * speedX);
        speedY = -rand.nextInt(1, 11) * DEFAULT_SPEED / 4.0F;
    }

    @Override
    public int renderRank()
    {
        return 2;
    }

    @Override
    public void update()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (!can_be_collected)
        {
            boolean bool = false;
            for (Entity e : player.getCollidingEntities(player.xMove, player.yMove))
                if (e.equals(this))
                {
                    bool = true;
                    break;
                }

            if (!bool)
                can_be_collected = true;
        }

        move();
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (size == 0)
            gfx.drawImage(Assets.geo_small, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (size == 1)
            gfx.drawImage(Assets.geo_medium, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (size == 2)
            gfx.drawImage(Assets.geo_large, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else
            gfx.drawImage(Assets.geo_small, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    public int getCount()
    {
        int i = 0;
        if (size == 0)
            i = 1;
        else if (size == 1)
            i = 5;
        else if (size == 2)
            i = 25;

        return i;
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
        if (can_be_collected)
        {
            handler.getWorld().getEntityManager().getPlayer().addGeo(getCount());
            handler.getWorld().spawnEntity(new ParticleGeoCollect(handler));
            handler.getWorld().removeEntity(this);
        }
    }
}
