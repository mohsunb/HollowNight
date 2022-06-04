package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class HardenedSurfaceFront extends StaticEntity
{
    public HardenedSurfaceFront(Handler handler, float x, float y)
    {
        super(handler, x, y, 400, 96);
        setSolid(true);
        setClimbable(true);
        health = 3;
        has_knockback = false;
    }

    @Override
    public void update()
    {
        if (health <= 0)
            handler.getWorld().removeEntity(this);
    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (health == 3)
            gfx.drawImage(Assets.hardened_surface_front_1, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 6), null);
        else if (health == 2)
            gfx.drawImage(Assets.hardened_surface_front_2, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 6), null);
        else if (health == 1)
            gfx.drawImage(Assets.hardened_surface_front_3, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 6), null);

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
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
