package dev.pogodemon.entities.objects;

import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class ChestExtra extends Entity
{
    /*
    ONLY PURPOSE OF THIS ENTITY IS FOR CHEST'S HATCH TO BE RENDERED BEHIND THE PLAYER
     */

    public ChestExtra(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);
    }

    @Override
    public void update()
    {

    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.drawImage(Assets.chest_open_back, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() - 73), null);
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
