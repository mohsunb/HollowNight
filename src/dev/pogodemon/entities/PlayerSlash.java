package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class PlayerSlash extends Creature
{
    private Player player;
    private boolean hit_wall = false;
    private long hit_wall_timer = 0;

    public PlayerSlash(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        player = handler.getWorld().getEntityManager().getPlayer();
        CREATURE_TYPE = -1;
        can_be_killed = false;
    }

    @Override
    public void update()
    {
        if (hit_wall)
        {
            hit_wall_timer++;
            if (hit_wall_timer >= Launcher.framerate_limit * 0.41)
            {
                hit_wall = false;
                hit_wall_timer = 0;
            }
        }

        if (exists && !player.down_slashing && !player.up_slashing && !hit_wall) // wall knockback
        {
            if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT))
            {
                player.attack_knockback = true;
                hit_wall = true;
            }
        }

        if (player.isFacingRight())
        {
            if (!player.down_slashing && !player.up_slashing)
            {
                bounds.x = 51;
                bounds.y = 0;
                bounds.width = 150;
                bounds.height = 127;
            }

            else if (player.up_slashing)
            {
                bounds.x = -55;
                bounds.y = -95;
                bounds.width = 180;
                bounds.height = 220;
            }

            else
            {
                bounds.x = -55;
                bounds.y = 0;
                bounds.width = 170;
                bounds.height = 230;
            }
        }

        else
        {
            if (!player.down_slashing && !player.up_slashing)
            {
                bounds.x = -147;
                bounds.y = 0;
                bounds.width = 150;
                bounds.height = 127;
            }

            else if (player.up_slashing)
            {
                bounds.x = -75;
                bounds.y = -95;
                bounds.width = 180;
                bounds.height = 220;
            }

            else
            {
                bounds.x = -55;
                bounds.y = 0;
                bounds.width = 170;
                bounds.height = 230;
            }
        }

        exists = player.isSlashing();

        if (exists)
        {
            setX(player.getX());
            setY(player.getY());

            if (checkEntityCollisions(xMove, yMove))
            {
                Player player = handler.getWorld().getEntityManager().getPlayer();
                if (getCollidingEntity(xMove, yMove) != null)
                {
                    for (Entity e : getCollidingEntities(xMove, yMove))
                        e.hasBeenHit();
                    player.was_just_attacked = true;

                    boolean bool1 = false;
                    boolean bool2 = false;
                    for (Entity e : getCollidingEntities(xMove, yMove))
                    {
                        if (e.is_pogoable)
                            bool1 = true;

                        if (e.has_knockback)
                            bool2 = true;
                    }

                    if (!player.up_slashing && !player.down_slashing && bool2 && !player.just_knocked_back)
                        player.attack_knockback = true;

                    else if (player.down_slashing && bool1)
                        player.pogo = true;
                }
            }
        }

        else if (getX() != 0 && getY() != 0)
        {
            setX(0);
            setY(0);
        }
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (exists)
        {
            if (player.isFacingRight())
            {
                if (!player.down_slashing && !player.up_slashing)
                {
                    if (player.alternative_slash_sprite)
                        gfx.drawImage(Assets.slash1_right, (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset()), null);
                    else
                        gfx.drawImage(Assets.slash2_right, (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset() - 15), null);
                }

                else if (player.up_slashing)
                {
                    gfx.drawImage(Assets.upslash_right, (int) (x - handler.getCamera().getxOffset() - 50), (int) (y - handler.getCamera().getyOffset() - 100), null);
                }

                else
                {
                    gfx.drawImage(Assets.downslash_right, (int) (x - handler.getCamera().getxOffset() - 60), (int) (y - handler.getCamera().getyOffset() - 20), null);
                }
            }

            else
            {
                if (!player.down_slashing && !player.up_slashing)
                {
                    if (player.alternative_slash_sprite)
                        gfx.drawImage(Assets.slash1_left, (int) (x - handler.getCamera().getxOffset() - 157), (int) (y - handler.getCamera().getyOffset()), null);
                    else
                        gfx.drawImage(Assets.slash2_left, (int) (x - handler.getCamera().getxOffset() - 140), (int) (y - handler.getCamera().getyOffset() - 15), null);
                }

                else if (player.up_slashing)
                {
                    gfx.drawImage(Assets.upslash_left, (int) (x - handler.getCamera().getxOffset() - 75), (int) (y - handler.getCamera().getyOffset() - 100), null);
                }

                else
                {
                    gfx.drawImage(Assets.downslash_left, (int) (x - handler.getCamera().getxOffset() - 62), (int) (y - handler.getCamera().getyOffset() - 20), null);
                }
            }

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

    }

    @Override
    public void fireballHit() {

    }

    @Override
    public void playerContact()
    {

    }
}
