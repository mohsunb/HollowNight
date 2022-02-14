package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

public abstract class Creature extends Entity
{
    protected int health;
    protected float speedX;
    protected float speedY;
    protected float xMove, yMove;
    protected boolean facing_right = true;

    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 4.0f * 144 / Launcher.framerate_limit; // To make the movement speed independent of the framerate

    public static final float DEFAULT_WIDTH = Launcher.game_width / 24; // 1920 / 24 = 80
    public static final float DEFAULT_HEIGHT = (float) (Launcher.game_height / 13.5); // 1080 / 13.5 = 80

    protected int CREATURE_TYPE = 1;    // 0 -> Player
                                        // 1 -> Mob
                                        // 2 -> Miscellaneous (e.g: geo)

    protected boolean grounded = false;
    protected boolean ceiling_collide = false;

    public Creature(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
        this.health = DEFAULT_HEALTH;
        this.speedX = DEFAULT_SPEED;
        this.speedY = 0;
        xMove = 0;
        yMove = 0;
    }

    public void move()
    {
        if (CREATURE_TYPE != 0)
        {
            yMove = 0;
            if (!grounded && speedY <= DEFAULT_SPEED * 2)
                speedY += 0.1;

            if (grounded)
                speedY = 0;

            yMove += speedY;
        }

        moveX();
        moveY();
    }

    public void moveX()
    {
        if (xMove > 0)
        {
            int tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH);
            if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)))
            {
                x += xMove;
            }

            else
            {

                if (CREATURE_TYPE == 0)
                    x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
                else
                {
                    facing_right = !facing_right;
                }
            }
        }

        else if (xMove < 0)
        {
            int tx = (int) Math.floor((x + xMove + bounds.x) / Tile.TILE_WIDTH);
            if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.TILE_HEIGHT))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)))
            {
                x += xMove;
            }

            else
            {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY()
    {
        if (yMove < 0)
        {
            int ty = (int) Math.floor((y + yMove + bounds.y) / Tile.TILE_HEIGHT);
            if (!collisionWithTile((int) Math.floor((x + bounds.x) / Tile.TILE_WIDTH), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width * 0.5) / Tile.TILE_WIDTH), ty))
            {
                grounded = false;
                ceiling_collide = false;
                y += yMove;
            }

            else
            {
                grounded = false;
                ceiling_collide = true;
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }

        else if (yMove == 0)
        {
            int ty = (int) Math.floor((y + 1 + bounds.y + bounds.height) / Tile.TILE_HEIGHT);
            if (!collisionWithTile((int) Math.floor((x + bounds.x) / Tile.TILE_WIDTH), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width * 0.5) / Tile.TILE_WIDTH), ty))
            {
                grounded = false;
                ceiling_collide = false;
                yMove += speedY;
            }
        }

        else
        {
            int ty = (int) Math.floor((y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT);
            if (!collisionWithTile((int) Math.floor((x + bounds.x) / Tile.TILE_WIDTH), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width * 0.5) / Tile.TILE_WIDTH), ty))
            {
                grounded = false;
                ceiling_collide = false;
                y += yMove;
            }

            else
            {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
                ceiling_collide = false;
                grounded = true;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y)
    {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public float getxSpeed()
    {
        return speedX;
    }

    public void setxSpeed(float speed)
    {
        this.speedX = speed;
    }

    public float getxMove()
    {
        return xMove;
    }

    public void setxMove(float xMove)
    {
        this.xMove = xMove;
    }

    public float getyMove()
    {
        return yMove;
    }

    public void setyMove(float yMove)
    {
        this.yMove = yMove;
    }
}
