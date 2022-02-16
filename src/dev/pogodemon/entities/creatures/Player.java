package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class Player extends Creature
{
    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_WIDTH * 0.75f, Creature.DEFAULT_HEIGHT * 1.625f);

        bounds.x = 6;
        bounds.y = 40;
        bounds.width = 44;
        bounds.height = 87;

        CREATURE_TYPE = 0; //Player
    }

    //slashing helper flags
    public int nail_damage = 5;
    public boolean alternative_slash_sprite = true; // = !self to change
    public boolean slashing = false;
    private boolean slash_blocked = false;
    private boolean illegal_slash = false;
    public boolean down_slashing = false;
    public boolean up_slashing = false;
    public boolean just_attacked = false;
    public boolean attack_knockback = false;
    public boolean pogo = false;
    long attack_knockback_timer = 0;
    long pogo_timer = 0;
    long invulnerable_timer = 0;
    long damage_shock_timer = 0;
    long slash_cooldown_timer = 0;
    long slash_timer = 0;

    @Override
    public void update()
    {
        if (attack_knockback)
        {
            if (facing_right)
            {
                handler.getKeyManager().left = true;
            }

            else
            {
                handler.getKeyManager().right = true;
            }
        }

        if (attack_knockback)
        {
            attack_knockback_timer++;
            if (attack_knockback_timer >= Launcher.framerate_limit * 0.2)
            {
                attack_knockback_timer = 0;
                attack_knockback = false;
            }
        }

        if (pogo)
        {
            speedY = 0;
            pogo_timer++;
            if (pogo_timer >= Launcher.framerate_limit * 0.25)
            {
                pogo_timer = 0;
                pogo = false;
                yMove = 0;
            }
        }

        if (just_attacked && !slashing)
            just_attacked = false;

        if (!handler.getKeyManager().x && !slashing)
        {
            if (handler.getKeyManager().up)
            {
                up_slashing = true;
                down_slashing = false;
            }

            else if (!handler.getKeyManager().up)
            {
                up_slashing = false;
            }
        }

        if (!handler.getKeyManager().x && !slashing && !grounded)
        {
            if (handler.getKeyManager().down)
            {
                down_slashing = true;
                up_slashing = false;
            }

            else if (!handler.getKeyManager().down)
            {
                down_slashing = false;
            }
        }

        if (grounded && down_slashing && !slashing)
            down_slashing = false;

        if (!handler.getKeyManager().x && handler.getKeyManager().up && handler.getKeyManager().down)
        {
            down_slashing = false;
            up_slashing = false;
        }

        if (slashing)
        {
            slash_timer++;
            if (!slash_blocked)
                slash_blocked = true;
        }

        if (slash_blocked)
        {
            slash_cooldown_timer++;
        }

        if (slash_timer >= Launcher.framerate_limit * 0.35)
        {
            slash_timer = 0;
            slashing = false;
        }

        if (slash_cooldown_timer >= Launcher.framerate_limit * 0.41)
        {
            slash_cooldown_timer = 0;
            slash_blocked = false;
        }

        if (health <= 0)
            handler.getKeyManager().q = true;

        if (invulnerable)
            invulnerable_timer++;

        if (damage_shocked)
            damage_shock_timer++;

        if (invulnerable_timer >= Launcher.framerate_limit * 1.3)
        {
            invulnerable = false;
            invulnerable_timer = 0;
        }

        if (damage_shock_timer >= Launcher.framerate_limit * 0.2)
        {
            damage_shocked = false;
            damage_shock_timer = 0;
        }

        if (cling_right || cling_left)
            can_dash = true;

        //prevent hard fall by dashing or clinging
        if (dashing || grounded || cling_right || cling_left)
        {
            fall_distance = 0;
        }
        
        if ((cling_right && !jumping) || (cling_left && !jumping))
        {
            wall_jumping_right = false;
            wall_jumping_left = false;
        }
        
        getInput();
        move();

        handler.getCamera().centerOnEntity(this);

        //wall cling reset
        if (grounded || dashing)
        {
            if (cling_right)
                cling_right = false;

            if (cling_left)
                cling_left = false;
        }

        if (dashing || cling_right || cling_left)
        {
            will_hard_fall = false;
            if (-speedY > DEFAULT_SPEED * 2 * 0.25 || yMove > DEFAULT_SPEED * 2 * 0.25)
            {
                yMove = 0;
                speedY = DEFAULT_SPEED * 2 * 0.25f;
            }
        }

        if (pogo)
            yMove = -5f;
    }

    long dash_length_timer = 0;
    long dash_cooldown_timer = 0;
    long fall_shock_timer = 0;
    long wall_jump_force_horizontal_move_timer = 0;

    private void getInput()
    {
        if (!dashing && !fall_shocked && !damage_shocked && !slashing && !slash_blocked && !illegal_slash && handler.getKeyManager().x)
        {
            slashing = true;
            alternative_slash_sprite = !alternative_slash_sprite;
        }

        if (slash_blocked && handler.getKeyManager().x)
            illegal_slash = true;

        if (!handler.getKeyManager().x)
            illegal_slash = false;

        //So that player doesn't stop during dash
        if(!dashing)
            xMove = 0;
        if (!pogo)
            yMove = 0;

        //Limit dash to 0.272 seconds
        if (dashing)
        {
            yMove = 0;
            if (slashing)
                slashing = false;

            dash_length_timer++;
            if (dash_length_timer >= Launcher.framerate_limit * 0.272)
            {
                dashing = false;
                can_dash = false;
                dash_length_timer = 0;
                just_dashed = true;
            }

            if (!can_dash_twice && grounded)
                can_dash_twice = true;
        }

        //Freeze for 0.8167 seconds after a hard fall
        if (grounded && (will_hard_fall || fall_shocked))
        {
            if (will_hard_fall)
                will_hard_fall = false;
            if (!fall_shocked)
                fall_shocked = true;
            fall_shock_timer++;
            if (fall_shock_timer >= Launcher.framerate_limit * 0.8167)
            {
                fall_shocked = false;
                fall_shock_timer = 0;
            }
        }

        //Add dash cooldown of 0.4 seconds (0.6 in the original is too much for us)
        if (just_dashed)
        {
            dash_cooldown_timer++;
            if (dash_cooldown_timer >= Launcher.framerate_limit * 0.4)
            {
                just_dashed = false;
                dash_cooldown_timer = 0;
            }
        }

        if (!fall_shocked && !damage_shocked)
        {
            if (!dashing && !can_dash && (grounded || can_dash_twice) && !handler.getKeyManager().c)
            {
                if (hasMothwingCloak)
                    can_dash = true;
            }

            if (!just_dashed && !dashing && can_dash && handler.getKeyManager().c)
            {
                dashing = true;
                if (can_dash_twice)
                    can_dash_twice = false;

                if (facing_right)
                    xMove += DEFAULT_SPEED * 2.51027861;
                else
                    xMove += -DEFAULT_SPEED * 2.51027861;

                speedY = 0;
            }

            //Jumping START
            if (!grounded && !cling_left && !cling_right && !jumping && handler.getKeyManager().z)
                illegal_jumping = true;

            if (illegal_jumping && (grounded || cling_right || cling_left) && !handler.getKeyManager().z)
                illegal_jumping = false;

            if (!illegal_jumping && (grounded || cling_right || cling_left) && !jumping && handler.getKeyManager().z)
            {
                if (cling_left)
                {
                    wall_jumping_right = true;
                }

                else if (cling_right)
                {
                    wall_jumping_left = true;
                }
                
                illegal_jumping = true;
                jumping = true;
                
                speedY = 10.0f; //Jumping initial speed
                if (!dashing || !just_dashed)
                    yMove += -speedY;
            }

            else if (jumping && handler.getKeyManager().z && (speedY - 0.15) > 0)
            {
                yMove += -speedY;
                speedY -= 0.15;

                if (ceiling_collide)
                {
                    jumping = false;
                    speedY = 0;
                }
                
                if (wall_jumping_right)
                {
                    wall_jump_force_horizontal_move_timer++;
                    if (wall_jump_force_horizontal_move_timer >= Launcher.framerate_limit * 0.15)
                    {
                        wall_jump_force_horizontal_move_timer = 0;
                        wall_jumping_right = false;
                    }
                    
                    else
                    {
                        handler.getKeyManager().right = true;
                        handler.getKeyManager().left = false;
                    }
                }

                else if (wall_jumping_left)
                {
                    wall_jump_force_horizontal_move_timer++;
                    if (wall_jump_force_horizontal_move_timer >= Launcher.framerate_limit * 0.15)
                    {
                        wall_jump_force_horizontal_move_timer = 0;
                        wall_jumping_left = false;
                    }

                    else
                    {
                        handler.getKeyManager().right = false;
                        handler.getKeyManager().left = true;
                    }
                }
            }

            else if (jumping && !handler.getKeyManager().z && speedY != 0)
            {
                speedY = 0;
            }

            else
            {
                jumping = false;

                int t = (int) (DEFAULT_SPEED * 2);

                if (cling_left || cling_right)
                    t = (int) (DEFAULT_SPEED * 2 * 0.25);

                if (!grounded && speedY <= t)
                    speedY += 0.2;

                if (grounded || dashing)
                    speedY = 0;

                if (!dashing)
                    yMove += speedY;
            }

            if ((grounded || cling_right || cling_left) && !handler.getKeyManager().z)
                illegal_jumping = false;
            //Jumping END

            if (!dashing && handler.getKeyManager().right)
            {
                if (!slashing)
                {
                    if(!facing_right)
                        facing_right = true;
                }
                xMove += speedX;
            }

            if (!dashing && handler.getKeyManager().left)
            {
                if (!slashing && !attack_knockback)
                {
                    if(facing_right)
                        facing_right = false;
                }
                xMove += -speedX;
            }
        }

        //Quit game (for quick debugging)
        if (handler.getKeyManager().q)
        {
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics gfx)
    {
        /*gfx.setColor(Color.white);
        gfx.drawString("X: " + (int) x , 5, 15);
        gfx.drawString("Y: " + (int) y, 5, 30);
        gfx.drawString("xMove: " + (int) xMove , 5, 45);
        gfx.drawString("yMove: " + (int) yMove , 5, 60);
        gfx.drawString("speedX: " + (int) speedX, 5, 90);
        gfx.drawString("speedY: " + (int) speedY, 5, 105);*/

        //Render player
        if (hasMantisClaw && cling_left)
            gfx.drawImage(Assets.cling_left, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset()), null);

        else if (hasMantisClaw && cling_right)
            gfx.drawImage(Assets.cling_right, (int) (x - handler.getCamera().getxOffset() - 14), (int) (y - handler.getCamera().getyOffset()), null);

        else if (facing_right)
        {
            if (fall_shocked)
                gfx.drawImage(Assets.fall_shock_right, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 20), null);

            else if (dashing)
            {
                gfx.drawImage(Assets.dash_right, (int) (x - handler.getCamera().getxOffset() - 4 - 111), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (slashing)
            {
                if (up_slashing)
                {
                    gfx.drawImage(Assets.upslash_char_right, (int) (x - handler.getCamera().getxOffset() - 43), (int) (y - handler.getCamera().getyOffset() + 30), null);
                }

                else if (down_slashing)
                {
                    gfx.drawImage(Assets.downslash_char_right, (int) (x - handler.getCamera().getxOffset() - 55), (int) (y - handler.getCamera().getyOffset() + 8), null);
                }

                else if (alternative_slash_sprite)
                {
                    gfx.drawImage(Assets.slash1_char_right, (int) (x - handler.getCamera().getxOffset() - 35), (int) (y - handler.getCamera().getyOffset()), null);
                }
                else
                {
                    gfx.drawImage(Assets.slash2_char_right, (int) (x - handler.getCamera().getxOffset() - 35), (int) (y - handler.getCamera().getyOffset()), null);
                }
            }

            else if (grounded)
            {
                if (xMove == 0)
                    gfx.drawImage(Assets.player_right, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);

                else
                    gfx.drawImage(Assets.walk_right, (int) (x - handler.getCamera().getxOffset() - 4 - 7), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else
            {
                if (jumping)
                    gfx.drawImage(Assets.jump_right, (int) (x - handler.getCamera().getxOffset() - 4 - 15), (int) (y - handler.getCamera().getyOffset()),null);
                else
                    gfx.drawImage(Assets.fall_right, (int) (x - handler.getCamera().getxOffset() - 4 - 30), (int) (y - handler.getCamera().getyOffset()), null);
            }
        }

        else
        {
            if (fall_shocked)
                gfx.drawImage(Assets.fall_shock_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 20), null);

            else if (dashing)
            {
                gfx.drawImage(Assets.dash_left, (int) (x - handler.getCamera().getxOffset() - 4 + 1), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (slashing)
            {
                if (up_slashing)
                {
                    gfx.drawImage(Assets.upslash_char_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 30), null);
                }

                else if (down_slashing)
                {
                    gfx.drawImage(Assets.downslash_char_left, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 8), null);
                }

                else if (alternative_slash_sprite)
                {
                    gfx.drawImage(Assets.slash1_char_left, (int) (x - handler.getCamera().getxOffset() - 11), (int) (y - handler.getCamera().getyOffset()), null);
                }
                else
                {
                    gfx.drawImage(Assets.slash2_char_left, (int) (x - handler.getCamera().getxOffset() - 8), (int) (y - handler.getCamera().getyOffset()), null);
                }
            }

            else if (grounded)
            {
                if (xMove == 0)
                    gfx.drawImage(Assets.player_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);

                else
                    gfx.drawImage(Assets.walk_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else
            {
                if (jumping)
                    gfx.drawImage(Assets.jump_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()),null);
                else
                    gfx.drawImage(Assets.fall_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);
            }
        }

        //Render player hitboxes (for testing purposes)
        //gfx.setColor(Color.blue);
        //gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);

        //Render HUD
        gfx.drawImage(Assets.soul_vessel_hud, 90, 80, null);

        for (int i = 0; i < DEFAULT_HEALTH / 20; i++)
            gfx.drawImage(Assets.mask_empty, 50 * i + 200, 100, null);

        for (int i = 0; i < health / 20; i++)
            gfx.drawImage(Assets.mask_full, 50 * i + 200, 98, null);
    }

    public boolean isSlashing()
    {
        return slashing;
    }

    public boolean isFacingRight()
    {
        return facing_right;
    }

    public boolean isFacingLeft()
    {
        return !facing_right;
    }
}
