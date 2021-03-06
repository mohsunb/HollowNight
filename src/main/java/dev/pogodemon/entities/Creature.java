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

    public static final float DEFAULT_WIDTH = Launcher.game_width / 24F; // 1920 / 24 = 80
    public static final float DEFAULT_HEIGHT = Launcher.game_height / 13.5F; // 1080 / 13.5 = 80

    // 0 -> Player; // 1 -> Mob; 2 -> Geo; -1 -> Player slash
    protected int CREATURE_TYPE = 1;

    protected boolean grounded = false;
    public boolean ceiling_collide = false;
    protected boolean will_hard_fall = false;
    protected boolean superdash_shocked = false;
    public boolean fall_shocked = false;
    public boolean damage_shocked = false;
    public boolean damage_shocked_right = false;
    public boolean invulnerable = false;

    //Player abilities (temporary, will be loaded off of a save file in the future)
    protected boolean hasMothwingCloak = false;
    protected boolean hasShadeCloak = false;
    protected boolean hasMantisClaw = false;
    protected boolean hasMonarchWings = false;
    protected boolean hasCrystalHeart = false;

    //Charm effect
    protected boolean shamanStone = false;
    protected boolean quickSlash = false;

    //jumping helper dynamic flags
    protected boolean double_jumping = false;
    protected boolean can_double_jump = true;
    protected boolean did_double_jump = false;
    protected boolean illegal_double_jumping = true;
    public boolean jumping = false;
    protected boolean illegal_jumping = true;

    //dashing helper dynamic flags
    public boolean can_dash = false;
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

    private boolean crawling = false;

    protected boolean hit_knockback = false;

    public Creature(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
        this.health = DEFAULT_HEALTH;
        this.speedX = DEFAULT_SPEED;
        this.speedY = 0;
        xMove = 0;
        yMove = 0;
    }

    public boolean isCrawling()
    {
        return crawling;
    }

    public void toggleCrawling()
    {
        crawling = !crawling;
    }

    public boolean isGrounded()
    {
        return grounded;
    }

    public void setCrawling()
    {
        if (!crawling)
            crawling = true;
    }

    public void setNotCrawling()
    {
        if (crawling)
            crawling = false;
    }

    public void changeDirection()
    {
        facing_right = !facing_right;
    }

    public void move()
    {
        if (this != handler.getWorld().getEntityManager().getPlayer() && hasGravity()) //Implementation of gravity
        {
            yMove = 0;
            if (!grounded && speedY <= DEFAULT_SPEED * 4)
                speedY += DEFAULT_SPEED * 0.05  * 144 / Launcher.framerate_limit;

            if (grounded)
            {
                if (CREATURE_TYPE == 2) // geo bounce mechanic
                {
                    if (speedY - DEFAULT_SPEED * 0.5 < 0)
                        speedY = 0;
                    else
                    {
                        speedY -= DEFAULT_SPEED * 0.5;
                        speedY *= -1;
                    }

                    if (xMove > 0)
                    {
                        xMove -= DEFAULT_SPEED * 0.075 * 4;
                        if (xMove < 0)
                            xMove = 0;
                    }

                    else if (xMove < 0)
                    {
                        xMove += DEFAULT_SPEED * 0.075 * 4;
                        if (xMove > 0)
                            xMove = 0;
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
            int tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.SIZE);
            if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.SIZE))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.SIZE))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.SIZE))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.SIZE)) && !checkEntityMoveCollisions(xMove, 0))
            {
                if (!isCrawling())
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

                else if (grounded && !hit_knockback && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width + xMove) / Tile.SIZE), (int) Math.floor((y + bounds.y + bounds.height + 1) / Tile.SIZE)))
                    changeDirection();

                else
                    x += xMove;
            }

            else
            {
                if (CREATURE_TYPE == 2) //geo bounce mechanic
                    xMove *= -1;

                else
                {
                    if (isCrawling() && !hit_knockback)
                        changeDirection();

                    else if (checkEntityMoveCollisions(xMove, 0))
                        x = getCollidingSolidEntity(xMove, 0).getX() + getCollidingSolidEntity(xMove, 0).bounds.x - bounds.x - bounds.width - 1;

                    else
                        x = tx * Tile.SIZE - bounds.x - bounds.width - 1;

                    if (this == player && !player.damage_shocked && (collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE)) || (checkEntityMoveCollisions(xMove + 2, 0) && getCollidingSolidEntity(xMove, 0).isClimbable())) && !jumping && !grounded && !dashing && hasMantisClaw && !player.slashing)
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
                    int tx = (int) Math.floor((x + xMove + bounds.x + bounds.width + 1) / Tile.SIZE);
                    if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE)) && !checkEntityMoveCollisions(xMove + 2, 0))
                        cling_right = false;
                }

                else if (cling_left)
                {
                    int tx = (int) Math.floor((x + xMove + bounds.x - 1) / Tile.SIZE);
                    if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE)) && !checkEntityMoveCollisions(xMove - 2, 0))
                        cling_left = false;
                }
            }
        }

        else if (xMove < 0)
        {
            int tx = (int) Math.floor((x + xMove + bounds.x) / Tile.SIZE);
            if (!collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.SIZE))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.SIZE))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.SIZE))
                    && !collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.SIZE)) && !checkEntityMoveCollisions(xMove, 0))
            {
                if (!isCrawling())
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

                else if (grounded && !hit_knockback  && !collisionWithTile((int) Math.floor((x + bounds.x + xMove) / Tile.SIZE), (int) Math.floor((y + bounds.y + bounds.height + 1) / Tile.SIZE)))
                    changeDirection();

                else
                    x += xMove;
            }

            else
            {
                if (CREATURE_TYPE == 2) //geo bounce mechanic
                    xMove *= -1;

                else
                {
                    if (isCrawling() && !hit_knockback)
                        changeDirection();

                    else if (checkEntityMoveCollisions(xMove, 0))
                        x = getCollidingSolidEntity(xMove, 0).getX() + getCollidingSolidEntity(xMove, 0).bounds.x + getCollidingSolidEntity(xMove, 0).bounds.width - bounds.x + 1;

                    else
                        x = tx * Tile.SIZE + Tile.SIZE - bounds.x;

                    if (this == player && !player.damage_shocked && (collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE)) || (checkEntityMoveCollisions(xMove - 2, 0) && getCollidingSolidEntity(xMove, 0).isClimbable())) && !jumping  && !grounded && !dashing && hasMantisClaw && !player.slashing)
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
            int ty = (int) Math.floor((y + yMove + bounds.y) / Tile.SIZE);
            if (!collisionWithTile((int) Math.floor((x + bounds.x) / Tile.SIZE), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width) / Tile.SIZE), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width * 0.5) / Tile.SIZE), ty) && !checkEntityMoveCollisions(0, yMove))
            {
                grounded = false;
                ceiling_collide = false;
                y += yMove;
            }

            else
            {
                grounded = false;
                if (CREATURE_TYPE == 2)
                    speedY *= -1;
                else
                {
                    if (CREATURE_TYPE == 0)
                    {
                        if (!handler.getWorld().getEntityManager().getPlayer().pogo)
                            ceiling_collide = true;
                    }

                    else
                        ceiling_collide = true;
                }

                if (checkEntityMoveCollisions(0, yMove))
                    y = getCollidingSolidEntity(0, yMove).getY() + getCollidingSolidEntity(0, yMove).bounds.y + getCollidingSolidEntity(0, yMove).bounds.height - bounds.y + 1;
                else
                    y = ty * Tile.SIZE + Tile.SIZE - bounds.y;
            }

        }

        else if (yMove == 0)
        {
            int ty = (int) Math.floor((y + 1 + bounds.y + bounds.height) / Tile.SIZE);
            if (!collisionWithTile((int) Math.floor((x + bounds.x) / Tile.SIZE), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width) / Tile.SIZE), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width * 0.5) / Tile.SIZE), ty))
            {
                grounded = false;

                if (checkEntityMoveCollisions(0, 10))
                    grounded = true;

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
                if (!will_hard_fall && fall_distance < 2000)
                    fall_distance += yMove;
                else
                {
                    will_hard_fall = true;
                    fall_distance = 0;
                }
            }

            int ty = (int) Math.floor((y + yMove + bounds.y + bounds.height) / Tile.SIZE);
            if (!collisionWithTile((int) Math.floor((x + bounds.x) / Tile.SIZE), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width) / Tile.SIZE), ty)
                    && !collisionWithTile((int) Math.floor((x + bounds.x + bounds.width * 0.5) / Tile.SIZE), ty) && !checkEntityMoveCollisions(0, yMove))
            {
                grounded = false;
                ceiling_collide = false;
                y += yMove;
            }

            else
            {
                if (checkEntityMoveCollisions(0, yMove))
                    y = getCollidingSolidEntity(0, yMove).getY() + getCollidingSolidEntity(0, yMove).bounds.y - bounds.y - bounds.height - 1;

                else
                    y = ty * Tile.SIZE - bounds.y - bounds.height - 1;
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
