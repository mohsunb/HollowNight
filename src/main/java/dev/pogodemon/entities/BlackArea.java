package dev.pogodemon.entities;

import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.Utils;

import java.awt.*;

public class BlackArea extends Entity
{
    private final boolean bool;
    private boolean triggered = false;
    private float opacity = 1.0F;
    private final String save_file_entry;

    public BlackArea(Handler handler, float x, float y, float width, float height, boolean bool, String save_file_entry)
    {
        super(handler, x, y, width, height);
        this.bool = bool;
        has_knockback = false;
        is_pogoable = false;
        this.save_file_entry = save_file_entry;
    }

    @Override
    public void update()
    {
        if (triggered)
        {
            opacity -= 2.0F / Launcher.framerate_limit;
            if (opacity <= 0)
            {
                handler.getWorld().removeEntity(this);
                GameFlags.data.updateValue(save_file_entry, true);
                GameFlags.data.updateLocalFile();
            }
        }
    }

    @Override
    public int renderRank()
    {
        return 5;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.setColor(Color.black);
        Utils.setOpacity(gfx, opacity);
        gfx.fillRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        Utils.setOpacity(gfx, 1.0F);
    }

    @Override
    public void hasBeenHit()
    {
        if (bool && !triggered)
            triggered = true;
    }

    @Override
    public void fireballHit()
    {
        if (!triggered)
            triggered = true;
    }

    @Override
    public void playerContact()
    {
        if (!triggered)
            triggered = true;
    }
}
