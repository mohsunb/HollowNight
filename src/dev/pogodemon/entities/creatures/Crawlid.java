package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.Player;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.World;

import java.awt.*;

public class Crawlid extends Creature
{
    private boolean hit_knockback_up = false;
    private boolean hit_knockback_down = false;
    private boolean hit_knockback_left = false;
    private boolean hit_knockback_right = false;
    private long hit_knockback_timer = 0;

    public Crawlid(Handler handler, float x, float y)
    {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        CREATURE_TYPE = 1;
        health = 10;

        is_pogoable = true;
        setCrawling();
    }

    @Override
    public void update()
    {
        if (exists)
        {
            if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
                was_just_attacked = false;

            move();

            if (hit_knockback)
            {
                hit_knockback_timer++;
                if (hit_knockback_timer >= Launcher.framerate_limit * 0.2)
                {
                    hit_knockback_timer = 0;
                    hit_knockback = false;

                    if (hit_knockback_up)
                        hit_knockback_up = false;

                    else if (hit_knockback_down)
                        hit_knockback_down = false;

                    else if (hit_knockback_left)
                        hit_knockback_left = false;

                    else
                        hit_knockback_right = false;
                    xMove = 0;
                    yMove = 0;
                }

                float s = DEFAULT_SPEED * 0.92f;
                if (hit_knockback_right)
                    xMove = s;
                else if (hit_knockback_left)
                    xMove = -s;
                else if (hit_knockback_up)
                    yMove = -s;
                else
                    yMove = s;
            }

            if (!hit_knockback)
            {
                speedX = DEFAULT_SPEED * 0.4f; // crawling speed
                if (facing_right)
                    xMove = speedX;
                else
                    xMove = -speedX;
            }


            if (health <= 0) // death
            {
                exists = false;
                World world = handler.getWorld();
                world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                facing_right = !handler.getWorld().getEntityManager().getPlayer().isFacingRight();
            }
        }
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (exists)
        {
            if (facing_right)
                gfx.drawImage(Assets.crawlid_right, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 5), null);
            else
                gfx.drawImage(Assets.crawlid_left, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 5), null);
        }

        else
        {
            if (facing_right)
                gfx.drawImage(Assets.crawlid_dead_right, (int) (x - handler.getCamera().getxOffset() - 150), (int) (y - handler.getCamera().getyOffset() + 40), null);
            else
                gfx.drawImage(Assets.crawlid_dead_left, (int) (x - handler.getCamera().getxOffset() + 100), (int) (y - handler.getCamera().getyOffset() + 40), null);
        }

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.red);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {
        if (!was_just_attacked)
        {
            was_just_attacked = true;
            Player player = handler.getWorld().getEntityManager().getPlayer();
            health -= player.nail_damage;

            hit_knockback = true;
            if (!player.up_slashing && !player.down_slashing)
            {
                if (player.isFacingRight())
                    hit_knockback_right = true;
                else
                    hit_knockback_left = true;
            }

            else if (player.up_slashing)
            {
                player.ceiling_collide = true;
                hit_knockback_up = true;
            }

            else
                hit_knockback_down = true;

            player.addSoul(11);
        }
    }

    @Override
    public void fireballHit()
    {
        if (!was_just_fireball_hit)
        {

            was_just_fireball_hit = true;
            Player player = handler.getWorld().getEntityManager().getPlayer();
            health -= player.fireball_damage;
            hit_knockback = true;

            if (player.isFacingRight())
                hit_knockback_right = true;
            else
                hit_knockback_left = true;
        }
    }

    @Override
    public void playerContact()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (!player.invulnerable && !player.shadow_dashing)
        {
            player.health -= 20;
            player.triggerScreenShake();
            player.triggerDamageFreeze();
            player.invulnerable = true;
            player.damage_shocked = true;
            if ((player.getX() + bounds.width * 0.5) <= (getX() + bounds.width * 0.5))
                player.damage_shocked_right = false;

            else
                player.damage_shocked_right = true;
        }
    }
}
