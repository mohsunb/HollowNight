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
import dev.pogodemon.utils.Utils;
import dev.pogodemon.world.World;

import java.awt.*;
import java.util.Random;

public class LeapingHusk extends Creature
{
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

    private boolean flag1 = false;

    private boolean hit_knockback_up;
    private boolean hit_knockback_down;

    public LeapingHusk(Handler handler, float x, float y)
    {
        super(handler, x, y, DEFAULT_WIDTH , DEFAULT_HEIGHT);
        CREATURE_TYPE = 1;
        health = 15;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 60;
        bounds.height = 150;

        this.agro_range = 300;
        this.de_agro_range = 500;

        is_pogoable = true;
        setCrawling();
    }

    Player player = handler.getWorld().getEntityManager().getPlayer();
    Random rand = new Random();

    @Override
    public void update()
    {
        if (exists)
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

            move();

            if (hit_knockback)
            {
                if (hit_knockback_up)
                {
                    speedY -= 2.5 * DEFAULT_SPEED;
                    yMove = speedY;
                    hit_knockback_up = false;
                    hit_knockback = false;
                }

                else if (hit_knockback_down)
                {
                    speedY += 2.5 * DEFAULT_SPEED;
                    yMove = speedY;
                    hit_knockback_down = false;
                    hit_knockback = false;
                }
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
                if (grounded)
                    speedY = -2.2F * DEFAULT_SPEED;
                grounded = false;
            }

            if (!walking && !walking_blocked && !agro)
                walking = true;

            if (attacking)
            {
                if (!attack_blocked)
                    attack_blocked = true;
                attack_timer++;
                if (attack_timer >= Launcher.framerate_limit * 2f || grounded)
                {
                    attack_timer = 0;
                    attacking = false;
                }

                speedX = DEFAULT_SPEED * 0.5F;
                if (facing_right)
                    xMove = speedX;

                else
                    xMove = -speedX;

                if (Math.abs(player.getCenterX() - getCenterX()) <= 20)
                    xMove = 0;
            }

            else if (!hit_knockback)
                xMove = 0;

            if (!attacking && attack_blocked)
            {
                attack_cooldown_timer++;
                if (attack_cooldown_timer >= Launcher.framerate_limit * 0.8F)
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

            double d = Math.sqrt(Math.pow(player.getCenterX() - getCenterX(), 2) + Math.pow(player.getCenterY() - getCenterY(), 2));
            if (!agro && d <= agro_range && player.getY() + player.bounds.y <= getY() + bounds.y + bounds.height)
            {
                if (isCrawling()) {
                    setNotCrawling();
                }
                agro = true;
            }

            if (!attacking && agro && d >= de_agro_range)
            {
                agro = false;
                attack_blocked = false;
                if (!isCrawling())
                    setCrawling();
            }


            if (health <= 0) // death;
            {
                exists = false;
                xMove = 0;
                yMove = 0;
                speedY = 0;
                speedX = 0;
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

            move();
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
            if (!walking && !attacking)
            {
                if (facing_right)
                    gfx.drawImage(Assets.leaping_husk_idle, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 50), null);
                else
                    gfx.drawImage(Utils.mirrorImage(Assets.leaping_husk_idle), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() - 50), null);
            }

            else if (attacking)
            {
                if (facing_right)
                {
                    if (yMove < 0)
                        gfx.drawImage(Assets.leaping_husk_jump_1, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
                    else
                        gfx.drawImage(Assets.leaping_husk_jump_2, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
                }

                else
                {
                    if (yMove < 0)
                        gfx.drawImage(Utils.mirrorImage(Assets.leaping_husk_jump_1), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset()), null);
                    else
                        gfx.drawImage(Utils.mirrorImage(Assets.leaping_husk_jump_2), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset()), null);
                }
            }

            else
            {
                if (facing_right)
                    gfx.drawImage(Assets.leaping_husk_walk, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 30), null);
                else
                    gfx.drawImage(Utils.mirrorImage(Assets.leaping_husk_walk), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() - 30), null);
            }
        }

        else
        {
            if (facing_right)
                gfx.drawImage(Assets.leaping_husk_corpse, (int) (x - handler.getCamera().getxOffset() - 100), (int) (y - handler.getCamera().getyOffset() + 80), null);
            else
                gfx.drawImage(Utils.mirrorImage(Assets.leaping_husk_corpse), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 80), null);
        }

        if (Launcher.show_hitboxes)
        {
            if (exists)
                gfx.setColor(Color.red);
            else
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
            Player player = handler.getWorld().getEntityManager().getPlayer();
            health -= player.nail_damage;
            handler.getWorld().spawnEntity(new ParticleHit(handler, Colors.infected, getCenterX(), getCenterY()));
            Player p = handler.getWorld().getEntityManager().getPlayer();
            float yy = (p.up_slashing || p.down_slashing) ? getCenterY() : p.getCenterY();
            handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), yy));

            if (player.up_slashing)
            {
                hit_knockback = true;
                hit_knockback_up = true;
            }

            else if (player.down_slashing)
            {
                hit_knockback = true;
                hit_knockback_down = true;
            }

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
        }
    }

    @Override
    public void playerContact()
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
