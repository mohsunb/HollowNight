package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class Hazard extends StaticEntity
{

    public Hazard(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
        has_knockback = false;
    }

    @Override
    public void update()
    {
        for (Entity e : getCollidingEntities(0, 0))
            e.kill();
    }

    @Override
    public void render(Graphics gfx)
    {
        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.red);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {

    }

    @Override
    public void playerContact()
    {
        handler.getWorld().getEntityManager().getPlayer().hazardRespawn();
    }
}
