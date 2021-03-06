package dev.pogodemon.entities.objects;

import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.World;

import java.awt.*;
import java.util.Random;

public class GeoDeposit extends Creature
{
    private int facing = 0; // 0 -> up; 1 -> right; 2 -> down; 3 -> left;
    private boolean hit = false;
    private int hit_counter = 0;
    private float xBak;
    private float yBak;

    private final String entry;

    public GeoDeposit(Handler handler, float x, float y, int facing, String entry)
    {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        can_be_killed = false;
        has_knockback = false;
        is_pogoable = true;

        this.entry = entry;
        health = (int) GameFlags.data.getValue(entry);
        exists = health > 0;

        xBak = x;
        yBak = y;

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
            if (hit && ++hit_counter >= Launcher.framerate_limit * 0.125)
            {
                hit = false;
                hit_counter = 0;
            }

            if (hit)
            {
                Random rand = new Random();
                setX((float) (getX() + Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(1, 101) * 0.01 * 2));
                setY((float) (getY() + Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(1, 101) * 0.01 * 2));

                if (Math.abs(getX() - xBak) >= 20)
                    setX(xBak);
                if (Math.abs(getY() - yBak) >= 20)
                    setY(yBak);
            }

            else
            {
                if (getX() != xBak)
                    setX(xBak);
                if (getY() != yBak)
                    setY(yBak);
            }

            if (was_just_fireball_hit)
            {
                fireball_timer++;
                if (fireball_timer >= Launcher.framerate_limit * 0.15)
                {
                    was_just_fireball_hit = false;
                    fireball_timer = 0;
                }
            }

            if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
                was_just_attacked = false;

            if (health <= 0) // death
            {
                exists = false;
                for (int i = 0; i < 5; i++)
                    handler.getWorld().spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
            }
        }
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
        if (!was_just_attacked)
        {
            was_just_attacked = true;
            health--;
            GameFlags.data.updateValue(entry, health);
            GameFlags.data.updateLocalFile();
            if (!hit)
            {
                hit = true;
                xBak = getX();
                yBak = getY();
            }

            World world = handler.getWorld();
            world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
            world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));

            Player p = handler.getWorld().getEntityManager().getPlayer();
            float yy = (p.up_slashing || p.down_slashing) ? getCenterY() : p.getCenterY();
            handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), yy));
        }
    }

    @Override
    public void fireballHit()
    {
        if (!was_just_fireball_hit)
        {
            was_just_fireball_hit = true;
            Player player = handler.getWorld().getEntityManager().getPlayer();
            health--;
            GameFlags.data.updateValue(entry, health);
            GameFlags.data.updateLocalFile();
            if (!hit)
            {
                hit = true;
                xBak = getX();
                yBak = getY();
            }

            World world = handler.getWorld();
            world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
            world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
        }
    }

    @Override
    public void playerContact()
    {

    }
}
