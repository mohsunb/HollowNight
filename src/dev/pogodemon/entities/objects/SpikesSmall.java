package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class SpikesSmall extends StaticEntity
{
    private int facing = 0; // 0 -> up; 1 -> right; 2 -> down; 3 -> left;
    public SpikesSmall(Handler handler, float x, float y, int facing)
    {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.facing = facing;
        is_harmful = true;
        is_hazard = true;
        is_pogoable = true;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics gfx)
    {
       if (facing == 0)
            gfx.drawImage(Assets.spikes_small_up, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (facing == 1)
            gfx.drawImage(Assets.spikes_small_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (facing == 2)
            gfx.drawImage(Assets.spikes_small_down, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (facing == 3)
            gfx.drawImage(Assets.spikes_small_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit() {

    }
}
