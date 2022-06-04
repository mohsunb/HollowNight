package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Animation;
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
import java.util.Random;

public class Gruzzer extends Entity
{
    private float xMove, yMove;

    private final Animation anim = new Animation(100, Assets.gruzzer);

    public Gruzzer(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);
        health = 10;
        bounds.width = 50;
        bounds.height = 50;
        is_pogoable = true;
        Random rand = new Random();
        xMove = (float) (Creature.DEFAULT_SPEED * 0.5 * Math.pow(-1, rand.nextInt(0, 2)));
        yMove = (float) (Creature.DEFAULT_SPEED * 0.5 * Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(50, 101) * 0.01);
    }

    private boolean collisionWithTile(int x, int y)
    {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    @Override
    public void update()
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

        anim.update();
        int tx;
        if (xMove > 0)
            tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.SIZE);
        else
            tx = (int) Math.floor((x + xMove + bounds.x) / Tile.SIZE);

        if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.SIZE))
                && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.SIZE))
                && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE))
                && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.SIZE))
                && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.SIZE)) && !checkEntityMoveCollisions(xMove, 0))
            x += xMove;

        else
            xMove *= -1;

        int ty;
        if (yMove < 0)
            ty = (int) Math.floor((y + yMove + bounds.y) / Tile.SIZE);
        else
            ty = (int) Math.floor((y + yMove + bounds.y + bounds.height) / Tile.SIZE);

        if (!collisionWithTile((int) Math.floor((x + bounds.x) / Tile.SIZE), ty)
                && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width) / Tile.SIZE), ty)
                && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width * 0.5) / Tile.SIZE), ty) && !checkEntityMoveCollisions(0, yMove))
            y += yMove;
        else
            yMove *= -1;

        if (health <= 0)
        {
            handler.getWorld().removeEntity(this);
            handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY(), 0));
            handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY(), 0));
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
        if (xMove > 0)
            gfx.drawImage(anim.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 25), (int) (y - handler.getCamera().getyOffset() - 25), null);
        else
            gfx.drawImage(Utils.mirrorImage(anim.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 25), (int) (y - handler.getCamera().getyOffset() - 25), null);

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
