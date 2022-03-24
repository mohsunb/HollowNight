package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class Fireball extends Creature
{
    private boolean facing_right;
    private boolean big;

    public Fireball(Handler handler, float x, float y, boolean big, boolean facing_right)
    {
        super(handler, x - (facing_right ? 0 : (big ? 120 : 90)), y - (big ? 80 : 50), DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.big = big;
        this.facing_right = facing_right;
        if (big)
        {
            bounds.width = 120;
            bounds.height = 160;
        }

        else
        {
            bounds.width = 90;
            bounds.height = 100;
        }
    }

    @Override
    public void update()
    {
        if (facing_right)
            xMove = DEFAULT_SPEED * 3F;
        else
            xMove = -DEFAULT_SPEED * 3F;

        if (xMove > 0)
        {
            int tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH);
            if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)) && !checkEntityMoveCollisions(xMove, 0))
                x += xMove;

            else
                handler.getWorld().removeEntity(this);
        }

        else
        {
            int tx = (int) Math.floor((x + xMove + bounds.x) / Tile.TILE_WIDTH);
            if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)) && !checkEntityMoveCollisions(xMove, 0))
                x += xMove;

            else
                handler.getWorld().removeEntity(this);
        }

        if (checkEntityCollisions(xMove, 0))
        {
            for (Entity e : getCollidingEntities(xMove, 0))
                e.fireballHit();
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
        if (facing_right)
        {
            if (big)
                gfx.drawImage(Assets.vengeful_spirit_big_right, (int) (x - handler.getCamera().getxOffset() - 295), (int) (y - handler.getCamera().getyOffset() - 92 + bounds.height * 0.5), null);
            else
                gfx.drawImage(Assets.vengeful_spirit_right, (int) (x - handler.getCamera().getxOffset() - 160), (int) (y - handler.getCamera().getyOffset() - 56 + bounds.height * 0.5), null);
        }

        else
        {
            if (big)
                gfx.drawImage(Assets.vengeful_spirit_big_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 92 + bounds.height * 0.5), null);
            else
                gfx.drawImage(Assets.vengeful_spirit_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 56 + bounds.height * 0.5), null);
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
    public void fireballHit()
    {

    }

    @Override
    public void playerContact()
    {

    }
}
