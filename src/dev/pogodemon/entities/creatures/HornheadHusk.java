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

public class HornheadHusk extends Creature
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

    private boolean flag1 = false;

    public HornheadHusk(Handler handler, float x, float y)
    {
        super(handler, x, y, DEFAULT_WIDTH , DEFAULT_HEIGHT);
        CREATURE_TYPE = 1;
        health = 15;
        bounds.x = 5;
        bounds.y = 15;
        bounds.width = 95;
        bounds.height = 115;

        this.agro_range = 400;
        this.de_agro_range = 1000;

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
                hit_knockback_timer++;
                if (hit_knockback_timer >= Launcher.framerate_limit * 0.1)
                {
                    hit_knockback_timer = 0;
                    hit_knockback = false;
                }

                float s = DEFAULT_SPEED * 2.71f;
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
                if (attack_timer >= Launcher.framerate_limit)
                {
                    attack_timer = 0;
                    attacking = false;
                }

                speedX = DEFAULT_SPEED * 0.95f;
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

            double d = Math.sqrt(Math.pow(player.getCenterX() - getCenterX(), 2) + Math.pow(player.getCenterY() - getCenterY(), 2));
            if (!agro && d <= agro_range && player.getY() + player.bounds.y <= getY() + bounds.y + bounds.height)
            {
                if (isCrawling())
                    setNotCrawling();
                agro = true;
            }


            if (!attacking && agro && d >= de_agro_range)
            {
                agro = false;
                if (!isCrawling())
                    setCrawling();
            }


            if (health <= 0) // death;
            {
                if (!flag1)
                {
                    flag1 = true;
                    World world = handler.getWorld();
                    world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                    world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                    world.spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
                }

                if (!hit_knockback)
                    exists = false;
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
                    gfx.drawImage(Assets.hornhead_husk_idle, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 55), null);
                else
                    gfx.drawImage(Utils.mirrorImage(Assets.hornhead_husk_idle), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 55), null);
            }

            else if (attacking)
            {
                if (facing_right)
                    gfx.drawImage(Assets.hornhead_husk_attack, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 20), null);
                else
                    gfx.drawImage(Utils.mirrorImage(Assets.hornhead_husk_attack), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 20), null);
            }

            else
            {
                if (facing_right)
                    gfx.drawImage(Assets.hornhead_husk_walk, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 25), null);
                else
                    gfx.drawImage(Utils.mirrorImage(Assets.hornhead_husk_walk), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() - 25), null);
            }
        }

        else
        {
            if (facing_right)
                gfx.drawImage(Assets.hornhead_husk_corpse, (int) (x - handler.getCamera().getxOffset() - 100), (int) (y - handler.getCamera().getyOffset() + 45), null);
            else
                gfx.drawImage(Utils.mirrorImage(Assets.hornhead_husk_corpse), (int) (x - handler.getCamera().getxOffset() + 50), (int) (y - handler.getCamera().getyOffset() + 45), null);
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
            handler.getWorld().spawnEntity(new ParticleHit(handler, Colors.infected, getCenterX(), getCenterY()));
            Player p = handler.getWorld().getEntityManager().getPlayer();
            float yy = (p.up_slashing || p.down_slashing) ? getCenterY() : p.getCenterY();
            handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), yy));

            if (attacking)
                attacking = false;
            if (!player.up_slashing && !player.down_slashing)
                hit_knockback = true;

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
