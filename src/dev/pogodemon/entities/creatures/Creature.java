package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.entities.HazardRespawnPoint;
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

    protected int CREATURE_TYPE = 1;    // 0 -> Player
                                        // 1 -> Mob
                                        // 2 -> Miscellaneous (e.g: geo)
                                        // -1 -> Player slash

    protected boolean grounded = false;
    protected boolean ceiling_collide = false;
    protected boolean will_hard_fall = false;
    protected boolean superdash_shocked = false;
    protected boolean fall_shocked = false;
    protected boolean damage_shocked = false;
    protected boolean damage_shocked_right = false;
    protected boolean invulnerable = false;

    //Abilities (temporary, will be loaded off of a save file in the future)
    protected boolean hasMothwingCloak = true;
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
    protected boolean dashing = false;
    protected boolean just_dashed = false;
    protected boolean can_dash_twice = false;

    //wall jump helper flags
    protected boolean cling_right = false;
    protected boolean cling_left = false;
    protected boolean wall_jumping_right = false;
    protected boolean wall_jumping_left = false;

    protected long minimum_superdash_timer = 0;
    protected boolean superdash = false;

    public Creature(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
        is_harmful = true; //by default all creatures deal damage
        this.health = DEFAULT_HEALTH;
        this.speedX = DEFAULT_SPEED;
        this.speedY = 0;
        xMove = 0;
        yMove = 0;
    }

    public void move()
    {
        if (CREATURE_TYPE != -1)
        {
            if (damage_shocked)
            {
                if (!damage_shocked_right)
                    xMove = -speedX;
                else
                {
                    xMove = speedX;
                    facing_right = false;
                }
            }

            if (CREATURE_TYPE == 1 || CREATURE_TYPE == 2)
            {
                yMove = 0;
                if (!grounded && speedY <= DEFAULT_SPEED * 2)
                    speedY += 0.1;

                if (grounded)
                    speedY = 0;

                yMove += speedY;
            }

            //Contact damage to player
            if (CREATURE_TYPE == 0)
            {
                if (!invulnerable && checkEntityCollisions(xMove, yMove))
                {
                    if (getCollidingEntity(xMove, yMove).is_hazard_respawn)
                    {
                        if (handler.getWorld().getEntityManager().getPlayer().getRespawnX() != getCollidingEntity(xMove, yMove).getRespawnX() || handler.getWorld().getEntityManager().getPlayer().getRespawnY() != getCollidingEntity(xMove, yMove).getRespawnY())
                            handler.getWorld().getEntityManager().getPlayer().updateRespawnPoint(getCollidingEntity(xMove, yMove).getRespawnX(), getCollidingEntity(xMove, yMove).getRespawnY());
                    }

                    else if (getCollidingEntity(xMove, yMove).is_harmful && getCollidingEntity(xMove, yMove).doesExist())
                    {
                        health -= 20;
                        invulnerable = true;

                        if (getCollidingEntity(xMove, yMove).is_hazard)
                        {
                            handler.getWorld().getEntityManager().getPlayer().hazardRespawn();
                            fall_shocked = true;
                            dashing = false;
                            superdash = false;
                        }

                        else
                        {
                            damage_shocked = true;
                            if ((getX() + bounds.width * 0.5) <= (getCollidingEntity(xMove, yMove).getX() + getCollidingEntity(xMove, yMove).bounds.width * 0.5))
                                damage_shocked_right = false;

                            else
                                damage_shocked_right = true;
                        }

                    }
                }
            }

            moveX();
            moveY();
        }

        //Player's Slash
        if (CREATURE_TYPE == -1)
        {
            if (checkEntityCollisions(xMove, yMove))
            {
                Player player = handler.getWorld().getEntityManager().getPlayer();
                if (player.slashing && !player.just_attacked)
                {
                    if (getCollidingEntity(xMove, yMove) != null)
                    {
                        getCollidingEntity(xMove, yMove).health -= player.nail_damage;
                        player.just_attacked = true;
                        if (!handler.getWorld().getEntityManager().getPlayer().up_slashing && !handler.getWorld().getEntityManager().getPlayer().down_slashing && getCollidingEntity(xMove, yMove).has_knockback)
                            handler.getWorld().getEntityManager().getPlayer().attack_knockback = true;

                        else if (handler.getWorld().getEntityManager().getPlayer().down_slashing && getCollidingEntity(xMove, yMove).is_pogoable)
                            handler.getWorld().getEntityManager().getPlayer().pogo = true;
                    }
                }
            }
        }
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
                if (cling_left)
                    cling_left = false;
                if (cling_right)
                    cling_right = false;
            }

            else
            {
                if (CREATURE_TYPE == 0)
                {
                    x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;

                    if (collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT)) && !jumping && !grounded && !dashing && hasMantisClaw)
                    {
                        facing_right = false;
                        cling_right = true;
                    }

                    if (minimum_superdash_timer == 0)
                        superdash = false;
                }
            }
        }

        else if (xMove == 0)
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
                if (cling_right)
                    cling_right = false;
                if (cling_left)
                    cling_left = false;
            }

            else
            {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;

                if (collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT)) && !jumping  && !grounded && !dashing && hasMantisClaw)
                {
                    cling_left = true;
                    facing_right = true;
                }

                if (minimum_superdash_timer == 0)
                    superdash = false;
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

                if (cling_left || cling_right)
                    yMove += speedY * 0.25;

                else
                    yMove += speedY;
            }
        }

        else
        {
            
            if (!will_hard_fall && fall_distance < 1600)
                fall_distance += yMove;
            else
            {
                will_hard_fall = true;
                fall_distance = 0;
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
