package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.particles.Colors;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.entities.particles.ParticleHit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;
import dev.pogodemon.world.World;

import java.awt.*;

public class Crawlid extends Creature
{
    private boolean hit_knockback_up = false;
    private boolean hit_knockback_down = false;
    private boolean hit_knockback_left = false;
    private boolean hit_knockback_right = false;
    private long hit_knockback_timer = 0;

    private boolean flag1 = false;

    private boolean dead = false;

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
        if (!dead)
        {
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

            if (hit_knockback)
            {
                hit_knockback_timer++;
                if (hit_knockback_timer >= Launcher.framerate_limit * 0.1)
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

                float s = DEFAULT_SPEED * 2.71f;
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
                if (!flag1)
                {
                    flag1 = true;
                    World world = handler.getWorld();
                    world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                    world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                }

                if (!hit_knockback)
                {
                    dead = true;
                    is_pogoable = false;
                    has_knockback = false;

                    facing_right = !handler.getWorld().getEntityManager().getPlayer().isFacingRight();
                }
            }
        }

        if (dead && (!hit_knockback || grounded))
        {
            speedX = 0;
            xMove = 0;
        }

        move();
    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (dead)
        {
            if (facing_right)
                gfx.drawImage(Assets.crawlid_dead_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 40), null);
            else
                gfx.drawImage(Assets.crawlid_dead_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 40), null);
        }

        else
        {
            if (facing_right)
                gfx.drawImage(Assets.crawlid_right, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 5), null);
            else
                gfx.drawImage(Assets.crawlid_left, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 5), null);
        }

        if (Launcher.show_hitboxes)
        {
            if (dead)
                gfx.setColor(Color.blue);
            else
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
            handler.getWorld().spawnEntity(new ParticleHit(handler, Colors.infected, getCenterX(), getCenterY()));
            Player p = handler.getWorld().getEntityManager().getPlayer();
            float yy = (p.up_slashing || p.down_slashing) ? getCenterY() : p.getCenterY();
            handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), yy));

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
            handler.getWorld().spawnEntity(new ParticleHit(handler, Colors.infected, getCenterX(), getCenterY()));
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
        if (!dead)
        {
            Player player = handler.getWorld().getEntityManager().getPlayer();
            if (!player.dead && !player.invulnerable && !player.shadow_dashing)
            {
                player.dealDamageGeneric();

                if ((player.getX() + bounds.width * 0.5) <= (getX() + bounds.width * 0.5))
                    player.damage_shocked_right = false;

                else
                    player.damage_shocked_right = true;
            }
        }
    }
}
