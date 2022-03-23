package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class SolidArea extends StaticEntity
{

    public SolidArea(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
        setSolid(true);
        has_knockback = false;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics2D gfx)
    {
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
