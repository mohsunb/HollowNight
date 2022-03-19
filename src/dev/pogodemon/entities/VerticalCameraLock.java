package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class VerticalCameraLock extends StaticEntity
{

    public VerticalCameraLock(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
        has_knockback = false;
        is_camera_lock = true;
    }

    @Override
    public void update()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        boolean bool = false;
        for (Entity e : handler.getWorld().getEntityManager().getPlayer().getCollidingEntities(0, 0))
            if (e.is_camera_lock)
            {
                bool = true;
                break;
            }
        if (player.grounded && bool)
            handler.getWorld().getEntityManager().getPlayerCamera().lock();

        else if (!bool)
            handler.getWorld().getEntityManager().getPlayerCamera().unlock();
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.orange);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {

    }

    @Override
    public void fireballHit() {

    }

    @Override
    public void playerContact()
    {

    }
}
