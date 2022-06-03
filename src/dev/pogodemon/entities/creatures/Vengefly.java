package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.particles.Colors;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.entities.particles.ParticleHit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.Utils;

import java.awt.*;
import java.util.Random;

public class Vengefly extends Creature
{
    private final Animation anim_idle = new Animation(Math.round(1000F/12F), Assets.vengefly_idle);
    private final Animation anim_startle = new Animation(Math.round(1000F/12F), Assets.vengefly_startle);
    private final Animation anim_attack = new Animation(Math.round(1000F/12F), Assets.vengefly_attack);
    private final Animation anim_death = new Animation(Math.round(1000F/60F), Assets.vengefly_death);

    private boolean idle = true;
    private boolean startle = false;
    private boolean agro = false;
    private boolean death = false;

    private boolean hit_knockback_up = false;
    private boolean hit_knockback_down = false;
    private boolean hit_knockback_left = false;
    private boolean hit_knockback_right = false;
    private long hit_knockback_timer = 0;

    private long change_direction_timer = 0;

    Player player = handler.getWorld().getEntityManager().getPlayer();

    public Vengefly(Handler handler, float x, float y)
    {
        super(handler, x, y, 40, 40);
        CREATURE_TYPE = 1;
        health = 10;
        is_pogoable = true;

        speedX = 0;
        speedY = 0;

        disableGravity();

        bounds.x = 20;
        bounds.y = 60;
    }

    @Override
    public void update()
    {
        float dX = Math.abs(player.getCenterX() - getCenterX());
        float dY = Math.abs(player.getCenterY() - 80 - getCenterY());
        float d = (float) Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));

        if (death && !anim_death.lastFrame())
            anim_death.update();

        if (idle)
        {
            anim_idle.update();
            anim_startle.reset();
        }

        if (startle)
        {
            anim_startle.update();
            if (anim_startle.lastFrame())
            {
                startle = false;
                agro = true;
            }
        }

        if (agro)
        {
            anim_attack.update();
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
            if (!death && hit_knockback_timer >= Launcher.framerate_limit * 0.1)
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

        else if (!startle && !agro && d <= 600)
        {
            startle = true;
            idle = false;
        }

        if (agro)
        {
            //De-agro
            if (d >= 1500)
            {
                agro = false;
                idle = true;
            }

            if (!hit_knockback)
            {
                float speed_limit = DEFAULT_SPEED * 0.5F;
                int lim = (int) (Launcher.framerate_limit * 0.25F);

                if (player.getCenterX() < getCenterX())
                {
                    if (speedX > -speed_limit)
                    {
                        if (speedX - speed_limit / lim < -speed_limit)
                            speedX = -speed_limit;
                        else
                            speedX -= speed_limit / lim;
                    }


                    if (facing_right)
                        facing_right = false;
                }

                else
                {
                    if (speedX < speed_limit)
                    {
                        if (speedX + speed_limit / lim > speed_limit)
                            speedX = speed_limit;
                        else
                            speedX += speed_limit / lim;
                    }

                    if (!facing_right)
                        facing_right = true;
                }

                if (player.getCenterY() < getCenterY())
                {
                    if (speedY > -speed_limit)
                    {
                        if (speedY - speed_limit / lim < -speed_limit)
                            speedY = -speed_limit;
                        else
                            speedY -= speed_limit / lim;
                    }

                    if (dY <= 30)
                    {
                        if (speedY > 0)
                        {
                            if (speedY - speed_limit / lim < 0)
                                speedY = 0;
                            else
                                speedY -= speed_limit / lim;
                        }

                        else if (speedY < 0)
                        {
                            if (speedY + speed_limit / lim > 0)
                                speedY = 0;
                            else
                                speedY += speed_limit / lim;
                        }
                    }
                }

                else if (player.getCenterY() > getCenterY())
                {
                    if (speedY < speed_limit)
                    {
                        if (speedY + speed_limit / lim > speed_limit)
                            speedY = speed_limit;
                        else
                            speedY += speed_limit / lim;
                    }
                }

                xMove = speedX;
                yMove = speedY;
            }
        }

        if (health <= 0 && !death) // death;
        {
            handler.getWorld().spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
            handler.getWorld().spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
            handler.getWorld().spawnEntity(new Geo(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5), 0));
            death = true;
            agro = false;
            idle = false;
            startle = false;
            speedY = 0;
            enableGravity();
        }

        if (death && grounded)
            handler.getWorld().removeEntity(this);
    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (facing_right)
        {
            if (idle)
                gfx.drawImage(anim_idle.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset()), null);
            else if (startle)
                gfx.drawImage(anim_startle.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset()), null);
            else if (agro)
                gfx.drawImage(anim_attack.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset()), null);
            else if (death)
                gfx.drawImage(anim_death.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset()), null);
        }

        else
        {
            if (idle)
                gfx.drawImage(Utils.mirrorImage(anim_idle.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else if (startle)
                gfx.drawImage(Utils.mirrorImage(anim_startle.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else if (agro)
                gfx.drawImage(Utils.mirrorImage(anim_attack.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else if (death)
                gfx.drawImage(Utils.mirrorImage(anim_death.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
        }

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.red);
            if (death)
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
        if (!death)
        {
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
