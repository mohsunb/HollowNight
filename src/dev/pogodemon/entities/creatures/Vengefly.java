package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.Player;
<<<<<<< HEAD
=======
import dev.pogodemon.entities.particles.Colors;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.entities.particles.ParticleHit;
>>>>>>> 6aee207 (v0.3.6)
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.World;

import java.awt.*;
import java.util.Random;

public class Vengefly extends Creature
{
    private boolean agro = false;
    private float agro_range, de_agro_range;

    private boolean hit_knockback_up = false;
    private boolean hit_knockback_down = false;
    private boolean hit_knockback_left = false;
    private boolean hit_knockback_right = false;
    private long hit_knockback_timer = 0;

    private long change_direction_timer = 0;

    Player player = handler.getWorld().getEntityManager().getPlayer();

    private boolean hit = false;
    private float hitX = 0;
    private float hitY = 0;
    private int hit_counter = 0;

    public Vengefly(Handler handler, float x, float y, float agro_range, float de_agro_range)
    {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        CREATURE_TYPE = 1;
        health = 10;
        this.agro_range = agro_range;
        this.de_agro_range = de_agro_range;
        is_pogoable = true;
        disableGravity();

        bounds.x = 20;
        bounds.y = 60;
    }

    @Override
    public void update()
    {
        if (hit && ++hit_counter >= Launcher.framerate_limit / 3F)
        {
            hit = false;
            hit_counter = 0;
        }

        if (exists)
        {
            if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
                was_just_attacked = false;

            move();

            if (!agro)
            {
                xMove = 0;
                yMove = 0;

                change_direction_timer++;

                if (change_direction_timer >= Launcher.framerate_limit * 4.0f)
                {
                    change_direction_timer = 0;
                    Random rand = new Random();
                    if (rand.nextInt(0, 2) == 0)
                        facing_right = !facing_right;
                }
            }

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

            if (agro && (Math.sqrt(Math.pow(getX() - player.getX(), 2) + Math.pow(getY() + bounds.height * 0.5 - player.getY() - player.bounds.height * 0.5, 2)) >= de_agro_range))
                agro = false;

            else if (Math.sqrt(Math.pow(getX() + bounds.width * 0.5 - player.getX() - player.bounds.width * 0.5, 2) + Math.pow(getY() - player.getY(), 2)) <= agro_range)
                agro = true;

            float sp = DEFAULT_SPEED * 0.5f; // movement speed
            if (agro && !hit_knockback)
            {
                if (getX() + bounds.x + bounds.width * 0.5 < player.getX() + player.bounds.x + player.bounds.width * 0.5 - 10)
                    xMove = sp;

                else if (getX() + bounds.x + bounds.width * 0.5 > player.getX() + player.bounds.x + player.bounds.width * 0.5 + 10)
                    xMove = -sp;

                else
                    xMove = 0;


                if (getY() + bounds.y + bounds.height * 0.5 < player.getY() + player.bounds.y + player.bounds.height * 0.5 - 10)
                    yMove = sp;

                else if (getY() + bounds.y + bounds.height * 0.5 > player.getY() + player.bounds.y + player.bounds.height * 0.5 + 10)
                    yMove = -sp;

                else
                    yMove = 0;


                if (xMove > 0 && !facing_right)
                    facing_right = true;
                else if (xMove < 0 && facing_right)
                    facing_right = false;
            }

            if (health <= 0 && !hit_knockback) // death;
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
            agro = false;
            if (getX() != 0 || getY() != 0)
            {
                setX(0);
                setY(0);
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
            if (facing_right)
            {
                if (agro)
                    gfx.drawImage(Assets.vengefly_agro_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
                else
                    gfx.drawImage(Assets.vengefly_idle_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else
            {
                if (agro)
                    gfx.drawImage(Assets.vengefly_agro_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
                else
                    gfx.drawImage(Assets.vengefly_idle_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            }
        }

        if (hit)
        {
            float rate = hit_counter / (Launcher.framerate_limit / 3F);
            gfx.setColor(new Color(255, 84, 0));
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - rate));
            gfx.fillOval((int) (hitX - handler.getCamera().getxOffset() - 100 - 200 * rate), (int) (hitY - handler.getCamera().getyOffset() - 100 - 200 * rate), (int) (200 + 2 * 200 * rate), (int) (200 + 2 * 200 * rate));
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
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
            health -= player.nail_damage;
<<<<<<< HEAD
            if (!hit)
            {
                hit = true;
                hitX = getCenterX();
                hitY = getCenterY();
            }
=======
            handler.getWorld().spawnEntity(new ParticleHit(handler, Colors.infected, getCenterX(), getCenterY()));
            handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), getCenterY()));
>>>>>>> 6aee207 (v0.3.6)
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
        if (!player.invulnerable && !player.shadow_dashing)
        {
            player.dealDamage();
            player.triggerScreenShake();
            player.setScreenShakeLength(Launcher.framerate_limit);
            player.setScreenShakeLevel(10);
            player.triggerDamageFreeze();
            player.setDamageShockFreezeLength(Launcher.framerate_limit / 3F);
            player.invulnerable = true;
            player.damage_shocked = true;
            if ((player.getX() + bounds.width * 0.5) <= (getX() + bounds.width * 0.5))
                player.damage_shocked_right = false;

            else
                player.damage_shocked_right = true;
        }
    }
}
