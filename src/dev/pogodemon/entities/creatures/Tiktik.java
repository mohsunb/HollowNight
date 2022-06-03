package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.particles.Colors;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.entities.particles.ParticleHit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.Utils;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class Tiktik extends Entity
{
    /*
    0 -> up
    1 -> right
    2 -> down
    3 -> left
     */
    private int direction = 1;

    private float speed = Creature.DEFAULT_SPEED * 0.1875F;
    private float xMove, yMove;

    public Tiktik(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);
        bounds.width = 40;
        bounds.height = 40;
        health = 10;
        is_pogoable = true;
    }

    private boolean collisionWithTile(int x, int y)
    {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    @Override
    public void update()
    {
        if (health <= 0)
        {
            handler.getWorld().removeEntity(this);
            if (direction == 0)
            {
                handler.getWorld().spawnEntity(new Geo(handler, getCenterX() - 50, getCenterY(), 0));
                handler.getWorld().spawnEntity(new Geo(handler, getCenterX() - 50, getCenterY(), 0));
            }

            else if (direction == 2)
            {
                handler.getWorld().spawnEntity(new Geo(handler, getCenterX() + 50, getCenterY(), 0));
                handler.getWorld().spawnEntity(new Geo(handler, getCenterX() + 50, getCenterY(), 0));
            }

            else
            {
                handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY(), 0));
                handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY(), 0));
            }
        }

        if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
            was_just_attacked = false;

        if (direction == 1)
        {
            xMove = speed;
            yMove = 0;

            int tx1 = (int) Math.floor((x + xMove) / Tile.SIZE);
            int tx2 = (int) Math.floor((x + bounds.width + xMove) / Tile.SIZE);
            int ty = (int) Math.floor((y + bounds.height + 10) / Tile.SIZE);

            int tyi = (int) Math.floor((y + bounds.height * 0.5) / Tile.SIZE);

            if (!collisionWithTile(tx1, ty) && !collisionWithTile(tx2, ty))
            {
                direction = 2;
                x = tx1 * Tile.SIZE;
                y = ty * Tile.SIZE - bounds.height + speed;
            }

            else if (collisionWithTile(tx2, tyi))
            {
                direction = 0;
                x = tx2 * Tile.SIZE - bounds.width - 1;
            }
        }

        else if (direction == 2)
        {
            xMove = 0;
            yMove = speed;

            int ty1 = (int) Math.floor((y + yMove) / Tile.SIZE);
            int ty2 = (int) Math.floor((y + bounds.height + yMove) / Tile.SIZE);

            int tx = (int) Math.floor((x - 10) / Tile.SIZE);
            int txi = (int) Math.floor((x + bounds.width * 0.5) / Tile.SIZE);

            if (!collisionWithTile(tx, ty1) && !collisionWithTile(tx, ty2))
            {
                direction = 3;
                x = tx * Tile.SIZE + Tile.SIZE - speed;
                y = ty1 * Tile.SIZE;
            }

            else if (collisionWithTile(txi, ty2))
            {
                direction = 1;
                y = ty2 * Tile.SIZE - bounds.width;
            }
        }

        else if (direction == 3)
        {
            xMove = -speed;
            yMove = 0;

            int tx1 = (int) Math.floor((x + xMove) / Tile.SIZE);
            int tx2 = (int) Math.floor((x + bounds.width + xMove) / Tile.SIZE);
            int ty = (int) Math.floor((y - 10) / Tile.SIZE);

            int tyi = (int) Math.floor((y + bounds.height * 0.5) / Tile.SIZE);

            if (!collisionWithTile(tx1, ty) && !collisionWithTile(tx2, ty))
            {
                direction = 0;
                x = tx2 * Tile.SIZE - 1;
                y = ty * Tile.SIZE + Tile.SIZE - speed;
            }

            else if (collisionWithTile(tx1, tyi))
            {
                direction = 2;
                x = tx1 * Tile.SIZE + Tile.SIZE + 1;
            }
        }

        else
        {
            xMove = 0;
            yMove = -speed;

            int ty1 = (int) Math.floor((y + yMove) / Tile.SIZE);
            int ty2 = (int) Math.floor((y + bounds.height + yMove) / Tile.SIZE);

            int tx = (int) Math.floor((x + bounds.width + 10) / Tile.SIZE);
            int txi = (int) Math.floor((x + bounds.width * 0.5) / Tile.SIZE);

            if (!collisionWithTile(tx, ty1) && !collisionWithTile(tx, ty2))
            {
                direction = 1;
                x = tx * Tile.SIZE - bounds.width + speed;
                y = ty1 * Tile.SIZE + Tile.SIZE * 2 - bounds.height - 1;
            }

            else if (collisionWithTile(txi, ty1))
            {
                direction = 3;
                y = ty1 * Tile.SIZE + Tile.SIZE + 1;
            }
        }

        if (!was_just_attacked)
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
        switch (direction)
        {
            case 0 -> gfx.drawImage(Utils.rotateImageByDegrees(Assets.tiktik, 270), (int) (x - handler.getCamera().getxOffset() - 34), (int) (y - handler.getCamera().getyOffset() - 30), null);
            case 1 -> gfx.drawImage(Assets.tiktik, (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset() - 34), null);
            case 2 -> gfx.drawImage(Utils.rotateImageByDegrees(Assets.tiktik, 90), (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() - 30), null);
            case 3 -> gfx.drawImage(Utils.rotateImageByDegrees(Assets.tiktik, 180), (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset() - 10), null);
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

            player.addSoul(11);
        }
    }

    @Override
    public void fireballHit()
    {
        health = 0;
        handler.getWorld().spawnEntity(new ParticleHit(handler, Colors.infected, getCenterX(), getCenterY()));
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
