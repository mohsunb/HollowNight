package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.Player;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.World;

import java.awt.*;
import java.util.Random;

public class WanderingHusk extends Creature
{
    private long hit_knockback_timer = 0;

    private boolean agro = false;
    private boolean walking = false;
    private boolean walking_blocked = false;
    private boolean attacking = false;
    private boolean attack_blocked = false;
    private boolean changed_direction = false;
    private final float agro_range;
    private final float de_agro_range;

    private long walk_timer;
    private long walk_cooldown_timer;
    private long attack_timer;
    private long attack_cooldown_timer;

    public WanderingHusk(Handler handler, float x, float y, float agro_range, float de_agro_range)
    {
        super(handler, x, y, DEFAULT_WIDTH , DEFAULT_HEIGHT);
        CREATURE_TYPE = 1;
        health = 15;
        bounds.x = 5;
        bounds.y = 15;
        bounds.width = 95;
        bounds.height = 115;

        this.agro_range = agro_range;
        this.de_agro_range = de_agro_range;

        is_pogoable = true;
    }

    Player player = handler.getWorld().getEntityManager().getPlayer();
    Random rand = new Random();

    @Override
    public void update()
    {
        if (exists)
        {
            move();

            if (hit_knockback)
            {
                hit_knockback_timer++;
                if (hit_knockback_timer >= Launcher.framerate_limit * 0.2)
                {
                    hit_knockback_timer = 0;
                    hit_knockback = false;
                }

                float s = DEFAULT_SPEED * 0.92f;
                if (facing_right)
                    xMove = -s;
                else
                    xMove = s;
            }

            if (agro && !attacking)
            {
                if (player.getX() + player.bounds.width * 0.5 <= getX() + bounds.width * 0.5)
                {
                    if (facing_right)
                        facing_right = false;
                }

                else
                {
                    if (!facing_right)
                        facing_right = true;
                }
            }

            if (!attack_blocked && agro)
            {
                if (walking)
                    walking = false;
                attacking = true;
            }

            if (!walking && !walking_blocked && !agro)
                walking = true;

            if (attacking)
            {
                if (!attack_blocked)
                    attack_blocked = true;
                attack_timer++;
                if (attack_timer >= Launcher.framerate_limit * 1.0f)
                {
                    attack_timer = 0;
                    attacking = false;
                }

                speedX = DEFAULT_SPEED * 0.5f;
                if (facing_right)
                    xMove = speedX;

                else
                    xMove = -speedX;
            }

            else if (!hit_knockback)
                xMove = 0;

            if (!attacking && attack_blocked)
            {
                attack_cooldown_timer++;
                if (attack_cooldown_timer >= Launcher.framerate_limit * 0.5)
                {
                    attack_cooldown_timer = 0;
                    attack_blocked = false;
                }
            }

            if (!walking && walking_blocked && !agro)
            {
                walk_cooldown_timer++;
                if (walk_cooldown_timer >= Launcher.framerate_limit * 2.0f)
                {
                    walk_cooldown_timer = 0;
                    walking_blocked = false;
                }

                if (!changed_direction)
                {
                    int i = rand.nextInt(0, 2);
                    if (i == 0)
                        facing_right = !facing_right;
                    changed_direction = true;
                }
            }

            if (walking)
            {
                if (changed_direction)
                    changed_direction = false;
                walk_timer++;
                walking_blocked = true;
                if (walk_timer >= Launcher.framerate_limit * 5.0f)
                {
                    walk_timer = 0;
                    walking = false;
                }

                speedX = DEFAULT_SPEED * 0.1f;
                if (facing_right)
                    xMove = speedX;
                else
                    xMove = -speedX;
            }

            if (!agro
                    && player.getX() + player.bounds.width * 0.5 >= getX() + bounds.width * 0.5 - agro_range
                    && player.getX() + player.bounds.width * 0.5 <= getX() + bounds.width * 0.5 + agro_range
                    && player.getY() + player.bounds.height >= getY()
                    && player.getY() <= getY() + bounds.height)
            {
                if (isCrawling())
                    setNotCrawling();
                agro = true;
            }


            if (agro && (player.getX() + player.bounds.width * 0.5 < getX() + bounds.width * 0.5 - de_agro_range
                    || player.getX() + player.bounds.width * 0.5 > getX() + bounds.width * 0.5 + de_agro_range
                    || player.getY() + player.bounds.height < getY()
                    || player.getY() > getY() + bounds.height))
            {
                agro = false;
                if (!isCrawling())
                    setCrawling();
            }


            if (health <= 0) // death;
            {
                exists = false;
                World world = handler.getWorld();
                world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
            }
        }

        else
        {
            attacking = false;
            walking = false;
            agro = false;
        }
    }

    @Override
    public void render(Graphics gfx)
    {
        if (exists)
        {
            if (!walking && !attacking)
            {
                if (facing_right)
                    gfx.drawImage(Assets.wandering_husk_idle_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
                else
                    gfx.drawImage(Assets.wandering_husk_idle_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (attacking)
            {
                if (facing_right)
                    gfx.drawImage(Assets.wandering_husk_attack_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 20), null);
                else
                    gfx.drawImage(Assets.wandering_husk_attack_left, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 20), null);
            }

            else
            {
                if (facing_right)
                    gfx.drawImage(Assets.wandering_husk_walk_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 15), null);
                else
                    gfx.drawImage(Assets.wandering_husk_walk_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 15), null);
            }
        }

        else
        {
            if (facing_right)
                gfx.drawImage(Assets.wandering_husk_dead_right, (int) (x - handler.getCamera().getxOffset() - 100), (int) (y - handler.getCamera().getyOffset() + 45), null);
            else
                gfx.drawImage(Assets.wandering_husk_dead_left, (int) (x - handler.getCamera().getxOffset() + 50), (int) (y - handler.getCamera().getyOffset() + 45), null);
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
        Player player = handler.getWorld().getEntityManager().getPlayer();
        health -= player.nail_damage;

        if (attacking)
            attacking = false;
        if (!player.up_slashing && !player.down_slashing)
            hit_knockback = true;

        player.addSoul(11);
    }

    @Override
    public void playerContact()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (!player.invulnerable && !player.shadow_dashing)
        {
            player.health -= 20;
            player.invulnerable = true;
            player.damage_shocked = true;
            if ((player.getX() + bounds.width * 0.5) <= (getX() + bounds.width * 0.5))
                player.damage_shocked_right = false;

            else
                player.damage_shocked_right = true;
        }
    }
}
