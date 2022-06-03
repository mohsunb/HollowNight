package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class Stalagmite extends Creature
{
    private boolean falling = false;
    private boolean diagonal = false;
    private boolean impact = false;
    private boolean triggered = false;
    private int timer = 0;

    private float d = 0;

    public Stalagmite(Handler handler, float x, float y, float d)
    {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.d = d;
        disableGravity();
        bounds.width = 40;
        bounds.height = 80;
        can_be_killed = false;
    }

    @Override
    public void update()
    {
        if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
            was_just_attacked = false;

        if (triggered)
        {
            timer++;
            if (timer >= Launcher.framerate_limit * 0.2)
            {
                falling = true;
                enableGravity();
            }
        }

        if (falling && !impact)
            for (Entity e : getCollidingEntities(xMove, yMove))
                e.kill();

        if (!impact && grounded)
            impact = true;

        if (!impact)
            move();

        if (impact)
            falling = false;

        if (diagonal && bounds.height != 40)
            bounds.height = 40;

        if (diagonal)
        {
            disableGravity();
            if (!impact)
                yMove = DEFAULT_SPEED * 4;
            else
                yMove = 0;
            if (facing_right)
                xMove = yMove;

            else
                xMove = -yMove;
        }

        if (!impact && !triggered &&
                handler.getWorld().getEntityManager().getPlayer().getCenterX() >= getCenterX() - 100 &&
                handler.getWorld().getEntityManager().getPlayer().getCenterX() <= getCenterX() + 100 &&
                handler.getWorld().getEntityManager().getPlayer().getCenterY() >= getCenterY() &&
                handler.getWorld().getEntityManager().getPlayer().getCenterY() <= getCenterY() + d)
            triggered = true;

        if (!impact)
        {
            int tx = 0;
            if (xMove > 0)
                tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.SIZE);
            else if (xMove < 0)
                tx = (int) Math.floor((x + xMove + bounds.x) / Tile.SIZE);

            if ((collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.SIZE))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.SIZE))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.SIZE))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.SIZE))) && !impact)
                impact = true;
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
        if (!diagonal)
            gfx.drawImage(Assets.stalagmite, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        else if (facing_right)
            gfx.drawImage(Assets.stalagmite_right, (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset() - 30), null);

        else
            gfx.drawImage(Assets.stalagmite_left, (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset() - 35), null);

        if (Launcher.show_hitboxes)
        {
            if (falling)
                gfx.setColor(Color.red);
            else
            {
                if (!impact)
                {
                    gfx.setColor(Color.green);
                    gfx.drawRect((int) (getCenterX() - 100 - handler.getCamera().getxOffset()), (int) (getCenterY() - handler.getCamera().getyOffset()), 200, (int) d);
                }
                gfx.setColor(Color.blue);
            }
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
            if (!falling)
                falling = true;

            if (!diagonal && !player.up_slashing && !player.down_slashing)
            {
                diagonal = true;
                Player p = handler.getWorld().getEntityManager().getPlayer();
            float yy = (p.up_slashing || p.down_slashing) ? getCenterY() : p.getCenterY();
            handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), yy));
                facing_right = player.isFacingRight();
            }

            if (impact)
            {
                if (player.down_slashing)
                    player.pogo = true;

                if (!player.down_slashing && !player.up_slashing)
                    player.attack_knockback = true;

                handler.getWorld().removeEntity(this);
            }
        }
    }

    @Override
    public void fireballHit() {

    }

    @Override
    public void playerContact()
    {
        if (falling)
        {
            Player player = handler.getWorld().getEntityManager().getPlayer();
            if (!player.invulnerable && !player.shadow_dashing)
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
