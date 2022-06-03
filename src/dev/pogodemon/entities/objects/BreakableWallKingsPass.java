package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class BreakableWallKingsPass extends BreakableWall
{
    private final boolean facing_right;
    public BreakableWallKingsPass(Handler handler, float x, float y, float width, float height, boolean facing_right, String save_file_entry)
    {
        super(handler, x, y, width, height, 1, save_file_entry);
        this.facing_right = facing_right;
        has_knockback = false;
        setClimbable(false);
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

    @Override
    public void fireballHit()
    {
        if (!was_just_fireball_hit)
        {
            was_just_fireball_hit = true;
            health = 0;
        }
    }
}
