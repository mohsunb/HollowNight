package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.Player;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.World;

import java.awt.*;

public class GeoDeposit extends Creature
{
    private int facing = 0; // 0 -> up; 1 -> right; 2 -> down; 3 -> left;

    public GeoDeposit(Handler handler, float x, float y, int facing)
    {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        has_knockback = false;
        is_pogoable = true;
        health = handler.getWorld().getEntityManager().getPlayer().nail_damage * 5;

        this.facing = facing;

        if (facing == 0 || facing == 2)
            bounds.width = 105;
        else
            bounds.height = 105;
    }

    @Override
    public void update()
    {
        if (exists)
        {
            if (health <= 0) // death
            {
                exists = false;
                for (int i = 0; i < 5; i++)
                    handler.getWorld().spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
            }
        }
    }

    @Override
    public void render(Graphics gfx)
    {
        if (exists)
        {
            if (facing == 0)
                gfx.drawImage(Assets.geo_deposit_up, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else if (facing == 1)
                gfx.drawImage(Assets.geo_deposit_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else if (facing == 2)
                gfx.drawImage(Assets.geo_deposit_down, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else if (facing == 3)
                gfx.drawImage(Assets.geo_deposit_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
        }

        else
        {
            if (facing == 0)
                gfx.drawImage(Assets.geo_deposit_broken_up, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 50), null);
            else if (facing == 1)
                gfx.drawImage(Assets.geo_deposit_broken_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else if (facing == 2)
                gfx.drawImage(Assets.geo_deposit_broken_down, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else if (facing == 3)
                gfx.drawImage(Assets.geo_deposit_broken_left, (int) (x - handler.getCamera().getxOffset() + 50), (int) (y - handler.getCamera().getyOffset()), null);
        }

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        health -= player.nail_damage;

        World world = handler.getWorld();
        world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
        world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
    }

    @Override
    public void playerContact()
    {

    }
}
