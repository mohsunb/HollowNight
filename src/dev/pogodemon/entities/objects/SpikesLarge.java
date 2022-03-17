package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class SpikesLarge extends StaticEntity
{
    private int facing = 0; // 0 -> up; 1 -> right; 2 -> down; 3 -> left;
    public SpikesLarge(Handler handler, float x, float y, int facing)
    {
        super(handler, x, y, 0, 0);
        this.facing = facing;
        is_pogoable = true;

        if (facing == 0 || facing == 2)
        {
            bounds.width = 138;
            bounds.height = 89;
        }

        else
        {
            bounds.width = 89;
            bounds.height = 138;
        }
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics gfx)
    {
        if (facing == 0)
            gfx.drawImage(Assets.spikes_large_up, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (facing == 1)
            gfx.drawImage(Assets.spikes_large_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (facing == 2)
            gfx.drawImage(Assets.spikes_large_down, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (facing == 3)
            gfx.drawImage(Assets.spikes_large_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

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
    public void playerContact()
    {

    }
}
