package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.particles.ParticleWallHit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class PlayerSlash extends Creature
{
    private final Player player;
    private boolean hit_wall = false;
    private long hit_wall_timer = 0;

    private final Animation slash1_right = new Animation(Math.round(1000F / 60F), Assets.slash1_right);
    private final Animation slash1_left = new Animation(Math.round(1000F / 60F), Assets.slash1_left);
    private final Animation slash2_right = new Animation(Math.round(1000F / 60F), Assets.slash2_right);
    private final Animation slash2_left = new Animation(Math.round(1000F / 60F), Assets.slash2_left);
    private final Animation upslash_left = new Animation(Math.round(1000F / 60F), Assets.upslash_left);
    private final Animation upslash_right = new Animation(Math.round(1000F / 60F), Assets.upslash_right);
    private final Animation downslash_right = new Animation(Math.round(1000F / 60F), Assets.downslash_right);
    private final Animation downslash_left = new Animation(Math.round(1000F / 60F), Assets.downslash_left);

    public PlayerSlash(Handler handler)
    {
        super(handler, 0, 0, 0, 0);
        player = handler.getWorld().getEntityManager().getPlayer();
        CREATURE_TYPE = -1;
        can_be_killed = false;
    }

    @Override
    public void update()
    {
        slash1_right.update();
        slash1_left.update();
        slash2_right.update();
        slash2_left.update();
        upslash_left.update();
        upslash_right.update();
        downslash_right.update();
        downslash_left.update();

        if (!player.isSlashing() || slash1_right.getIndex() == 9 || (player.down_slashing && player.flatSurface()))
            handler.getWorld().removeEntity(this);

        if (hit_wall)
        {
            hit_wall_timer++;
            if (hit_wall_timer >= Launcher.framerate_limit * 0.41)
            {
                hit_wall = false;
                hit_wall_timer = 0;
            }
        }

        if (!player.down_slashing && !player.up_slashing && !hit_wall) // wall knockback
        {
            float xx;
            float yy = y + bounds.y + bounds.height * 0.5F;

            boolean bool = false;

            if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.0F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.0F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.0F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.2F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.2F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.2F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.4F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.4F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.4F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.6F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.6F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.6F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.8F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.8F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 0.8F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 1.0F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 1.0F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.SIZE))
            {
                bool = true;
                xx = x + bounds.x + bounds.width * 1.0F;
            }

            else
                xx = 0;

            if (bool)
            {
                player.attack_knockback = true;
                handler.getWorld().spawnEntity(new ParticleWallHit(handler, xx, yy));
                hit_wall = true;
            }
        }

        if ((player.down_slashing || player.up_slashing) && !hit_wall) // wall knockback
        {
            float xx = x + bounds.x + bounds.width * 0.5F;
            float yy;

            boolean bool = false;

            if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.50) / Tile.SIZE, (int) Math.floor(getY() + bounds.y) / Tile.SIZE))
            {
                bool = true;
                yy = y + bounds.y + bounds.height * 0.0F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.50) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.2) / Tile.SIZE))
            {
                bool = true;
                yy = y + bounds.y + bounds.height * 0.2F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.50) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.4) / Tile.SIZE))
            {
                bool = true;
                yy = y + bounds.y + bounds.height * 0.4F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.50) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.6) / Tile.SIZE))
            {
                bool = true;
                yy = y + bounds.y + bounds.height * 0.6F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.50) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height * 0.8) / Tile.SIZE))
            {
                bool = true;
                yy = y + bounds.y + bounds.height * 0.8F;
            }

            else if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.50) / Tile.SIZE, (int) Math.floor(getY() + bounds.y + bounds.height) / Tile.SIZE))
            {
                bool = true;
                yy = y + bounds.y + bounds.height * 1.0F;
            }

            else
                yy = 0;

            if (bool)
            {
                handler.getWorld().spawnEntity(new ParticleWallHit(handler, xx, yy));
                hit_wall = true;
            }
        }

//        if (!hit_wall) // wall knockback
//        {
//            if (collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.2) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.4) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.6) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 0.8) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.34) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.50) / Tile.TILE_HEIGHT)
//             || collisionWithTile((int) Math.floor(getX() + bounds.x + bounds.width * 1.0) / Tile.TILE_WIDTH, (int) Math.floor(getY() + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT))
//            {
//                if (!player.down_slashing && !player.up_slashing)
//                    player.attack_knockback = true;
//
//                float xx = player.isFacingRight() ? x + bounds.x + bounds.width : x + bounds.x;
//                float yy = y + bounds.y + bounds.height * 0.5F;
//                handler.getWorld().spawnEntity(new ParticleWallHit(handler, xx, yy));
//                hit_wall = true;
//            }
//        }

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
                bounds.height = 220;
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
                bounds.height = 220;
            }
        }

        setX(player.getX());
        setY(player.getY());

        if (checkEntityCollisions(xMove, yMove))
        {
            Player player = handler.getWorld().getEntityManager().getPlayer();
            if (getCollidingEntity(xMove, yMove) != null)
            {
                boolean bool1 = false;
                boolean bool2 = false;

                for (Entity e : getCollidingEntities(xMove, yMove))
                {
                    e.hasBeenHit();

                    if (!bool1 && e.is_pogoable)
                        bool1 = true;

                    if (!bool2 && e.has_knockback)
                        bool2 = true;
                }

                if (!player.up_slashing && !player.down_slashing && bool2 && !player.just_knocked_back)
                    player.attack_knockback = true;

                else if (player.down_slashing && bool1)
                {
                    if (!player.just_pogoed)
                    {
                        player.pogo = true;
                        player.just_pogoed = true;
                    }
                }
            }
        }
    }

    @Override
    public int renderRank()
    {
        return 3;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (player.isFacingRight())
        {
            if (!player.down_slashing && !player.up_slashing)
            {
                if (player.alternative_slash_sprite)
                    gfx.drawImage(slash1_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 71), (int) (y - handler.getCamera().getyOffset()), null);
                else
                    gfx.drawImage(slash2_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset() - 15), null);
            }

            else if (player.up_slashing)
            {
                gfx.drawImage(upslash_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 50), (int) (y - handler.getCamera().getyOffset() - 100), null);
            }

            else
            {
                gfx.drawImage(downslash_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 60), (int) (y - handler.getCamera().getyOffset() - 20), null);
            }
        }

        else
        {
            if (!player.down_slashing && !player.up_slashing)
            {
                if (player.alternative_slash_sprite)
                    gfx.drawImage(slash1_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 157), (int) (y - handler.getCamera().getyOffset()), null);
                else
                    gfx.drawImage(slash2_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 140), (int) (y - handler.getCamera().getyOffset() - 15), null);
            }

            else if (player.up_slashing)
            {
                gfx.drawImage(upslash_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 75), (int) (y - handler.getCamera().getyOffset() - 100), null);
            }

            else
            {
                gfx.drawImage(downslash_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 62), (int) (y - handler.getCamera().getyOffset() - 20), null);
            }
        }

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
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
