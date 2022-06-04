package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.particles.ParticleSpikeHit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class SpikesSmall extends StaticEntity
{
    private int facing = 0; // 0 -> up; 1 -> right; 2 -> down; 3 -> left;
    public SpikesSmall(Handler handler, float x, float y, int facing)
    {
        super(handler, x, y, Tile.SIZE, Tile.SIZE);
        this.facing = facing;
        is_pogoable = true;
    }

    @Override
    public void update()
    {
        if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
            was_just_attacked = false;
    }

    @Override
    public int renderRank()
    {
        return 3;
    }

    @Override
    public void render(Graphics2D gfx)
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
    public void hasBeenHit()
    {
        if (!was_just_attacked)
        {
            Player player = handler.getWorld().getEntityManager().getPlayer();
            player.triggerScreenShake();
            player.setScreenShakeLength(Launcher.framerate_limit * 0.0625F);
            player.setScreenShakeLevel(5);
            was_just_attacked = true;

            float xx = getCenterX();
            float yy = getCenterY();

            if (player.down_slashing || player.up_slashing)
                xx = player.getCenterX();

            else
                yy = player.getCenterY();

            handler.getWorld().spawnEntity(new ParticleSpikeHit(handler, xx, yy));
        }
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
