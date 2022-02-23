package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

public abstract class Creature extends Entity
{
    protected float speedX;
    protected float speedY;
    protected float xMove, yMove;
    protected boolean facing_right = true;

    protected float fall_distance = 0;

    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 4.0f * 144 / Launcher.framerate_limit; // To make the movement speed independent of the framerate

    public static final float DEFAULT_WIDTH = Launcher.game_width / 24; // 1920 / 24 = 80
    public static final float DEFAULT_HEIGHT = (float) (Launcher.game_height / 13.5); // 1080 / 13.5 = 80

    // 0 -> Player; // 1 -> Mob; 2 -> Geo; -1 -> Player slash
    protected int CREATURE_TYPE = 1;

    protected boolean grounded = false;
    protected boolean ceiling_collide = false;
    protected boolean will_hard_fall = false;
    protected boolean superdash_shocked = false;
    public boolean fall_shocked = false;
    public boolean damage_shocked = false;
    public boolean damage_shocked_right = false;
    public boolean invulnerable = false;

    //Abilities (temporary, will be loaded off of a save file in the future)
    protected boolean hasMothwingCloak = true;
    protected boolean hasShadeCloak = true;
    protected boolean hasMantisClaw = true;
    protected boolean hasMonarchWings = true;
    protected boolean hasCrystalHeart = true;

    //jumping helper dynamic flags
    protected boolean double_jumping = false;
    protected boolean can_double_jump = true;
    protected boolean did_double_jump = false;
    protected boolean illegal_double_jumping = false;
    protected boolean jumping = false;
    protected boolean illegal_jumping = false;

    //dashing helper dynamic flags
    protected boolean can_dash = false;
    public boolean dashing = false;
    public boolean shadow_dashing = false;
    protected boolean can_shadow_dash = false;
    protected long shadow_dash_cooldown = 0;
    protected boolean just_dashed = false;
    protected boolean can_dash_twice = false;

    //wall jump helper flags
    protected boolean cling_right = false;
    protected boolean cling_left = false;
    protected boolean wall_jumping_right = false;
    protected boolean wall_jumping_left = false;

    protected long minimum_superdash_timer = 0;
    public boolean superdash = false;

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
        if (this != handler.getWorld().getEntityManager().getPlayer() && hasGravity()) //Implementation of gravity
        {
            yMove = 0;
            if (!grounded && speedY <= DEFAULT_SPEED * 4)
                speedY += 0.2;

            if (grounded)
            {
                if (CREATURE_TYPE == 2) // geo bounce mechanic
                {
                    if (speedY - 2 < 0)
                        speedY = 0;
                    else
                    {
                        speedY -= 2;
                        speedY *= -1;
                    }

                    if (xMove > 0)
                    {
                        if (xMove - 0.3 < 0)
                            xMove = 0;
                        xMove -= 0.3;
                    }

                    else if (xMove < 0)
                    {
                        if (xMove + 0.3 > 0)
                            xMove = 0;
                        xMove += 0.3;
                    }
                }

                else
                    speedY = 0;
            }

            yMove += speedY;
        }

        moveX();
        moveY();
    }

    public void moveX()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
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
                if (this == player)
                {
                    if (cling_left)
                        cling_left = false;
                    if (cling_right)
                        cling_right = false;
                }
            }

            else
            {
                if (CREATURE_TYPE == 2) //geo bounce mechanic
                    xMove *= -1;

                else
                {
                    x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;

                    if (this == player && collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT)) && !jumping && !grounded && !dashing && hasMantisClaw)
                    {
                        facing_right = false;
                        cling_right = true;
                    }
                }
            }
        }

        else if (xMove == 0)
        {
            if (this == player)
            {
                if (cling_right)
                {
                    int tx = (int) Math.floor((x + xMove + bounds.x + bounds.width + 1) / Tile.TILE_WIDTH);
                    if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT)))
                        cling_right = false;
                }

                else if (cling_left)
                {
                    int tx = (int) Math.floor((x + xMove + bounds.x - 1) / Tile.TILE_WIDTH);
                    if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT)))
                        cling_left = false;
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
                if (this == player)
                {
                    if (cling_right)
                        cling_right = false;
                    if (cling_left)
                        cling_left = false;
                }
            }

            else
            {
                if (CREATURE_TYPE == 2) //geo bounce mechanic
                    xMove *= -1;

                else
                {
                    x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;

                    if (this == player && collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT)) && !jumping  && !grounded && !dashing && hasMantisClaw)
                    {
                        cling_left = true;
                        facing_right = true;
                    }
                }
            }
        }
    }

    public void moveY()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();

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

                if (this == player && (cling_left || cling_right))
                    yMove += speedY * 0.25;

                else
                    yMove += speedY;
            }
        }

        else
        {
            if (this == player)
            {
                if (!will_hard_fall && fall_distance < 1600)
                    fall_distance += yMove;
                else
                {
                    will_hard_fall = true;
                    fall_distance = 0;
                }
            }

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
