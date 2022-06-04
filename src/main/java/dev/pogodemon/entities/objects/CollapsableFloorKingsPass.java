package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class CollapsableFloorKingsPass extends CollapsableFloor
{
    public CollapsableFloorKingsPass(Handler handler, float x, float y)
    {
        super(handler, x, y, 1000, 200, (int) (Launcher.framerate_limit * 0.5F));
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (exists)
        {
            gfx.drawImage(Assets.collapsing_floor_1, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 20), null);

            if (Launcher.show_hitboxes)
            {
                gfx.setColor(Color.yellow);
                gfx.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 200), (int) (bounds.width), 200);
                gfx.setColor(Color.blue);
                gfx.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
            }
        }
    }
}
