package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class BreakableWallKingsPass extends BreakableWall
{
    private final boolean facing_right;
    public BreakableWallKingsPass(Handler handler, float x, float y, float width, float height, boolean facing_right)
    {
        super(handler, x, y, width, height, 1);
        this.facing_right = facing_right;
        has_knockback = false;
    }

    @Override
    public void render(Graphics gfx)
    {
        if (exists)
        {
            if (facing_right)
                gfx.drawImage(Assets.breakable_wall_generic_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

            else
                gfx.drawImage(Assets.breakable_wall_generic_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

            if (Launcher.show_hitboxes)
            {
                gfx.setColor(Color.blue);
                gfx.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
            }
        }
    }
}
