package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.WorldLoader;

import java.awt.*;

public class Geo extends Creature
{
    private int size = 0; // 0 -> 1 geo; 1 -> 5; 2 -> 25;

    public Geo(Handler handler, float x, float y, int size)
    {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.size = size;
        CREATURE_TYPE = 2;
        is_pogoable = false;
        has_knockback = false;

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
    }

    @Override
    public void update()
    {
        if (exists)
            move();

        else if (getX() != 0 || getY() != 0)
        {
            setX(0);
            setY(0);
        }
    }

    @Override
    public void render(Graphics gfx)
    {
        if (exists)
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
    public void playerContact()
    {
        handler.getWorld().getEntityManager().getPlayer().addGeo(getCount());
        exists = false;
    }
}
