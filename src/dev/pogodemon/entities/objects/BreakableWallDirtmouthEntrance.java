package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class BreakableWallDirtmouthEntrance extends BreakableWall
{
    public BreakableWallDirtmouthEntrance(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height, 13);
    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (exists)
        {
            gfx.setColor(Color.darkGray);
            gfx.fillRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);

            if (Launcher.show_hitboxes)
            {
                gfx.setColor(Color.blue);
                gfx.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
            }
        }
    }

    @Override
    public void fireballHit()
    {

    }
}
