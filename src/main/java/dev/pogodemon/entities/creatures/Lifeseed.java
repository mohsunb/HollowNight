package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.particles.Colors;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.entities.particles.ParticleHit;
import dev.pogodemon.entities.particles.ParticleLifebloodEffects;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;
import java.util.Random;

public class Lifeseed extends Creature
{
    private boolean bounce = false;
    private boolean can_be_killed = false;
    private boolean bool = false;

    private boolean dead = false;
    private int timer = 0;

    public Lifeseed(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);
        Random rand = new Random();
        xMove = (float) Math.floor(Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(0, 101) * 0.01 * speedX);
        speedY = -rand.nextInt(1, 11) * DEFAULT_SPEED / 4.0F;
        facing_right = xMove > 0;

        bounds.x = 20;
        bounds.y = 20;
        bounds.width = 50;
        bounds.height = 55;
    }

    @Override
    public void update()
    {
        if (!dead)
        {
            float speed_limit = DEFAULT_SPEED * 0.5F;
            Player player = handler.getWorld().getEntityManager().getPlayer();

            if (was_just_fireball_hit)
            {
                fireball_timer++;
                if (fireball_timer >= Launcher.framerate_limit * 0.15)
                {
                    was_just_fireball_hit = false;
                    fireball_timer = 0;
                }
            }

            if (!can_be_killed && !player.slashing)
                can_be_killed = true;

            if (!bool && grounded)
            {
                if (speedX > speed_limit)
                    speedX = speed_limit;
                bool = true;
            }

            int lim = (int) (Launcher.framerate_limit * 0.5F);

            if (bool)
            {
                if (bounce)
                {
                    speedX *= -1;
                    bounce = false;
                }

                if (player.getCenterX() > getCenterX())
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

                xMove = speedX;
            }

            yMove = 0;
            if (!grounded && speedY <= DEFAULT_SPEED * 4)
                speedY += DEFAULT_SPEED * 0.05  * 144 / Launcher.framerate_limit;

            if (grounded)
                speedY = 0;

            yMove += speedY;

            if (xMove > 0)
            {
                int tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.SIZE);
                if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.SIZE))
                        && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.SIZE))
                        && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE))
                        && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.SIZE))
                        && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.SIZE)) && !checkEntityMoveCollisions(xMove, 0))
                    x += xMove;

                else
                    bounce = true;
            }

            else if (xMove < 0)
            {
                int tx = (int) Math.floor((x + xMove + bounds.x) / Tile.SIZE);
                if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.SIZE))
                        && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.SIZE))
                        && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE))
                        && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.SIZE))
                        && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.SIZE)) && !checkEntityMoveCollisions(xMove, 0))
                    x += xMove;

                else
                    bounce = true;
            }

            moveY();
        }

        else if (handler.getWorld().getEntityManager().getPlayer().room_transitioning || timer++ >= Launcher.framerate_limit * 1.5)
        {
            handler.getWorld().removeEntity(this);
            handler.getWorld().getEntityManager().getPlayer().lifeblood += 20;
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
        if (!dead)
        {
            if (facing_right)
                gfx.drawImage(Assets.lifeseed_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);
            else
                gfx.drawImage(Assets.lifeseed_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

            if (Launcher.show_hitboxes)
            {
                gfx.setColor(Color.blue);
                gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
            }
        }
    }

    @Override
    public void hasBeenHit()
    {
        if (can_be_killed)
            fireballHit();
    }

    @Override
    public void fireballHit()
    {
        if (!dead)
        {
            dead = true;
            for (int i = 0; i < 40; i++)
                handler.getWorld().spawnEntity(new ParticleLifebloodEffects(handler, getCenterX(), getCenterY()));
            handler.getWorld().spawnEntity(new ParticleHit(handler, Colors.lifeblood, getCenterX(), getCenterY()));
            Player p = handler.getWorld().getEntityManager().getPlayer();
            float yy = (p.up_slashing || p.down_slashing) ? getCenterY() : p.getCenterY();
            handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), yy));
        }
    }

    @Override
    public void playerContact()
    {

    }
}
