package dev.pogodemon.entities.creatures;

import dev.pogodemon.Game;
import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.EnemySlash;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.particles.*;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class Shade extends Creature
{
    private final Player player = handler.getWorld().getEntityManager().getPlayer();

    private final Animation anim_idle_right = new Animation(Math.round(1000F/12F), Assets.shade_idle_right);
    private final Animation anim_idle_left = new Animation(Math.round(1000F/12F), Assets.shade_idle_left);

    private final Animation anim_startle_right = new Animation(Math.round(1000F/12F), Assets.shade_startle_right);
    private final Animation anim_startle_left = new Animation(Math.round(1000F/12F), Assets.shade_startle_left);

    private final Animation anim_move_right = new Animation(Math.round(1000F/12F), Assets.shade_move_right);
    private final Animation anim_move_left = new Animation(Math.round(1000F/12F), Assets.shade_move_left);

    private final Animation anim_attack_right = new Animation(Math.round(1000F/60F), Assets.shade_attack_right);
    private final Animation anim_attack_left = new Animation(Math.round(1000F/60F), Assets.shade_attack_left);

    private final Animation anim_despawn_right = new Animation(Math.round(1000F/60F), Assets.shade_despawn_right);
    private final Animation anim_despawn_left = new Animation(Math.round(1000F/60F), Assets.shade_despawn_left);

    private final Animation anim_death_right = new Animation(Math.round(1000F/60F), Assets.shade_death_right);
    private final Animation anim_death_left = new Animation(Math.round(1000F/60F), Assets.shade_death_left);

    private boolean idle = true;
    private boolean startled = false;
    private boolean agro = false;
    private boolean despawning = false;
    private boolean despawned = false;
    private boolean death = false;

    private final float spawnX, spawnY;
    private boolean too_far = false;

    private boolean attacking = false;
    private boolean slash_active = false;
    private int attack_trigger_timer = 0;
    private boolean attack_cooldown = false;
    private int attack_cooldown_timer = 0;

    private final float range = 400;

    private boolean hit_knockback_up = false;
    private boolean hit_knockback_down = false;
    private boolean hit_knockback_left = false;
    private boolean hit_knockback_right = false;
    private long hit_knockback_timer = 0;

    private int particle_limiter = 0;
    private int death_particle_limiter = 0;

    public Shade(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);

        spawnX = x;
        spawnY = y;

        bounds.x = 6;
        bounds.y = 40;
        bounds.width = 44;
        bounds.height = 87;

        facing_right = true;
        is_pogoable = true;
        speedX = 0;
        speedY = 0;
        disableGravity();

        health = (int) (player.nail_damage * Math.floor(player.max_health / 40F));
    }

    @Override
    public void update()
    {
        if (!death && health <= 0)
        {
            death = true;
            attacking = false;
            attack_trigger_timer = 0;
        }

        if (death)
        {
            anim_death_left.update();
            anim_death_right.update();

            if (anim_death_right.getIndex() >= 35 || anim_death_left.getIndex() >= 35)
            {
                if (death_particle_limiter++ >=  Launcher.framerate_limit / 60F)
                {
                    death_particle_limiter = 0;
                    handler.getWorld().spawnEntity(new ParticleShadeAbsorb(handler, getCenterX(), getCenterY()));
                }
            }

            if (anim_death_right.lastFrame() || anim_death_left.lastFrame())
            {
                handler.getWorld().removeEntity(this);
                player.addGeo(GameFlags.shadeGeo);
                player.max_soul = 99;
                GameFlags.hasShade = false;
                GameFlags.data.updateValue("has_shade", false);
                GameFlags.data.updateValue("shade_geo", 0);
                GameFlags.data.updateLocalFile();
            }
            if (attacking)
                attacking = false;
            if (attack_trigger_timer != 0)
                attack_trigger_timer = 0;

            // Die immediately when player leaves the room
            if (player.room_transitioning)
            {
                handler.getWorld().removeEntity(this);
                player.addGeo(GameFlags.shadeGeo);
                player.max_soul = 99;
                GameFlags.hasShade = false;
                GameFlags.data.updateValue("has_shade", false);
                GameFlags.data.updateValue("shade_geo", 0);
                GameFlags.data.updateLocalFile();
            }
        }

        if (!despawned && !despawning && player.dead && player.dead_state >= 2)
        {
            despawning = true;
            attacking = false;
            attack_trigger_timer = 0;
        }

        if (despawning)
        {
            anim_despawn_left.update();
            anim_despawn_right.update();

            if (anim_despawn_left.lastFrame() || anim_despawn_right.lastFrame())
            {
                despawning = false;
                despawned = true;
            }
        }

        if (too_far)
        {
            if (xMove > 0)
            {
                if (x + xMove > spawnX)
                {
                    setX(spawnX);
                    xMove = 0;
                }
            }

            else if (xMove < 0)
            {
                if (x + xMove < spawnX)
                {
                    setX(spawnX);
                    xMove = 0;
                }
            }

            if (yMove > 0)
            {
                if (y + yMove > spawnY)
                {
                    setY(spawnY);
                    yMove = 0;
                }
            }

            else if (yMove < 0)
            {
                if (y + yMove < spawnY)
                {
                    setY(spawnY);
                    yMove = 0;
                }
            }

            if (xMove == 0 && yMove == 0)
                too_far = false;
        }

        if (particle_limiter++ >= (too_far ? Launcher.framerate_limit / 60F : Launcher.framerate_limit * 0.1F))
        {
            particle_limiter = 0;
            handler.getWorld().spawnEntity(new ParticleShadeCloakTrailCircles(handler, getCenterX(), getCenterY()));
            handler.getWorld().spawnEntity(new ParticleShadeCloakTrailCircles(handler, getCenterX(), getCenterY()));
            if (too_far)
            {
                handler.getWorld().spawnEntity(new ParticleShadeCloakTrailCircles(handler, getCenterX(), getCenterY()));
                handler.getWorld().spawnEntity(new ParticleShadeCloakTrailCircles(handler, getCenterX(), getCenterY()));
            }
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

        if (agro && !too_far && !death && Math.sqrt(Math.pow(getX() - spawnX, 2) + Math.pow(getY() - spawnY, 2)) >= 2000)
        {
            too_far = true;
            xMove = (spawnX - getX()) / (Launcher.framerate_limit);
            yMove = (spawnY - getY()) / (Launcher.framerate_limit);
            idle = true;
            startled = false;
            agro = false;
            attacking = false;
        }

        if (agro)
        {
            float dX = Math.abs(player.getCenterX() - getCenterX());
            float dY = Math.abs(player.getCenterY() - getCenterY());

            if (attacking)
            {
                anim_attack_left.update();
                anim_attack_right.update();

                if (anim_attack_right.lastFrame() || anim_attack_left.lastFrame())
                    attacking = false;

                if (!slash_active && (anim_attack_left.getIndex() >= 29 || anim_attack_right.getIndex() >= 29))
                {
                    slash_active = true;
                    handler.getWorld().spawnEntity(new EnemySlash(handler, getCenterX() + (facing_right ? 40 : -200), getCenterY(), 150, 40, Launcher.framerate_limit * 0.1F, xMove));
                }
            }

            else
            {
                if (slash_active)
                    slash_active = false;
                anim_attack_left.reset();
                anim_attack_right.reset();
            }

            if (!attack_cooldown && dX <= 300 && dY <= 100)
            {
                if (attack_trigger_timer++ >= Launcher.framerate_limit)
                {
                    attack_cooldown = true;
                    attacking = true;
                }
            }

            if (attack_cooldown && attack_cooldown_timer++ >= Launcher.framerate_limit * 5F)
            {
                attack_cooldown_timer = 0;
                attack_cooldown = false;
            }

            if (!attacking)
            {
                anim_move_left.update();
                anim_move_right.update();
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

        if (idle)
        {
            anim_idle_right.update();
            anim_idle_left.update();
        }

        if (!too_far && !agro && !startled && player.getCenterX() >= getCenterX() - range
                && player.getCenterX() <= getCenterX() + range
                && player.getCenterY() >= getCenterY() - range + 100
                && player.getCenterY() <= getCenterY() + range - 100)
        {
            startled = true;
            idle = false;
        }

        if (startled && !agro)
        {
            anim_startle_left.update();
            anim_startle_right.update();
            if (anim_startle_left.lastFrame() || anim_startle_right.lastFrame())
            {
                agro = true;
                startled = false;
            }
        }

        if (agro && !player.dead && !death)
            move();

        if (too_far)
        {
            x += xMove;
            y += yMove;
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
        if (!too_far && !despawned)
        {
            if (facing_right)
            {
                if (death)
                    gfx.drawImage(anim_death_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 50), (int) (y - handler.getCamera().getyOffset()), null);
                else if (despawning)
                    gfx.drawImage(anim_despawn_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset() - 40), null);
                else if (idle)
                    gfx.drawImage(anim_idle_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
                else if (startled)
                    gfx.drawImage(anim_startle_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset()), null);
                else if (agro)
                {
                    if (attacking)
                        gfx.drawImage(anim_attack_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 80), (int) (y - handler.getCamera().getyOffset()), null);
                    else
                        gfx.drawImage(anim_move_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 50), (int) (y - handler.getCamera().getyOffset()), null);
                }
            }
            else
            {
                if (death)
                    gfx.drawImage(anim_death_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset()), null);
                else if (despawning)
                    gfx.drawImage(anim_despawn_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() - 40), null);
                else if (idle)
                    gfx.drawImage(anim_idle_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
                else if (startled)
                    gfx.drawImage(anim_startle_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset()), null);
                else if (agro)
                {
                    if (attacking)
                        gfx.drawImage(anim_attack_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 150), (int) (y - handler.getCamera().getyOffset()), null);
                    else
                        gfx.drawImage(anim_move_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
                }

            }

            if (Launcher.show_hitboxes)
            {
                if (death)
                    gfx.setColor(Color.blue);
                else
                    gfx.setColor(Color.red);

                gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);

                if (!agro)
                {
                    gfx.setColor(Color.orange);
                    gfx.drawRect((int) (getCenterX() - range - handler.getCamera().getxOffset()), (int) (getCenterY() - range + 100 - handler.getCamera().getyOffset()), (int) (range * 2), (int) (range * 2 - 200));
                }

                if (agro && !attacking)
                {
                    gfx.setColor(Color.yellow);
                    gfx.drawRect((int) (getCenterX() - 300 - handler.getCamera().getxOffset()), (int) (getCenterY() - 100 - handler.getCamera().getyOffset()), 600, 200);
                }
            }
        }
    }

    @Override
    public void hasBeenHit()
    {
        if (!was_just_attacked)
        {
            was_just_attacked = true;
            health -= player.nail_damage;
            handler.getWorld().spawnEntity(new ParticleHit(handler, Color.black, getCenterX(), getCenterY()));
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
        }
    }

    @Override
    public void fireballHit()
    {
        if (idle)
            idle = false;

        if (!startled)
            startled = true;

        if (!was_just_fireball_hit)
        {
            was_just_fireball_hit = true;
            health -= player.fireball_damage;
            handler.getWorld().spawnEntity(new ParticleHit(handler, Color.black, getCenterX(), getCenterY()));
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
