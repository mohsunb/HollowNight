package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class Player extends Creature
{
    private boolean jumping = false;
    private boolean constant_jumping = false;
    private boolean can_dash = true;
    private boolean dashing = false;
    private boolean just_dashed = false;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_WIDTH * 0.75f, Creature.DEFAULT_HEIGHT * 2);

        bounds.x = 10;
        bounds.y = 40;
        bounds.width = 40;
        bounds.height = 115;

        CREATURE_TYPE = 0; //Player
    }

    @Override
    public void update()
    {
        getInput();
        move();
        handler.getCamera().centerOnEntity(this);
    }

    long dash_length_timer = 0;
    long dash_cooldown_timer = 0;

    private void getInput()
    {
        //So that player doesn't stop during dash
        if(!dashing)
            xMove = 0;
        yMove = 0;

        //Limit dash to 0.12 seconds
        if (dashing)
        {
            dash_length_timer++;
            if (dash_length_timer >= Launcher.framerate_limit * 0.12)
            {
                dashing = false;
                can_dash = false;
                dash_length_timer = 0;
                just_dashed = true;
            }
        }

        //Add dash cooldown of 0.6 seconds
        if (just_dashed)
        {
            dash_cooldown_timer++;
            if (dash_cooldown_timer >= Launcher.framerate_limit * 0.2)
            {
                just_dashed = false;
                dash_cooldown_timer = 0;
            }
        }

        if (!dashing && !can_dash && grounded && !handler.getKeyManager().c)
        {
            can_dash = true;
        }

        if (!just_dashed && !dashing && can_dash && handler.getKeyManager().c)
        {
            dashing = true;

            if (facing_right)
                xMove += DEFAULT_SPEED * 5;
            else
                xMove += -DEFAULT_SPEED * 5;

            speedY = 0;
        }

        //Jumping and gravity START

        if (!constant_jumping && grounded && !jumping && handler.getKeyManager().z)
        {
            constant_jumping = true;
            jumping = true;
            speedY = 9.0f; //Jumping initial speed
            if (!dashing || !just_dashed)
                yMove += -speedY;
        }

        else if (jumping && handler.getKeyManager().z && (speedY - 0.15) > 0)
        {
            yMove += -speedY;
            speedY -= 0.15;

            if (ceiling_collide)
                jumping = false;
        }

        else if (jumping && !handler.getKeyManager().z && speedY != 0)
        {
            speedY = 0;
        }

        else
        {
            jumping = false;
            if (!grounded && speedY <= DEFAULT_SPEED * 2)
                speedY += 0.2;

            if (grounded || dashing)
                speedY = 0;

            if (!dashing)
                yMove += speedY;
        }

        if (grounded && !handler.getKeyManager().z)
            constant_jumping = false;

        //Jumping and gravity END

        if (handler.getKeyManager().right)
        {
            if(!facing_right)
                facing_right = true;
            if (!dashing)
                xMove += speedX;
        }

        if (handler.getKeyManager().left)
        {
            if(facing_right)
                facing_right = false;
            if (!dashing)
                xMove += -speedX;
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
        gfx.setColor(Color.white);
        gfx.drawString("X: " + (int) x / Tile.TILE_WIDTH, 5, 15);
        gfx.drawString("Y: " + (handler.getWorld().getHeight() - (int)  y / Tile.TILE_HEIGHT), 5, 30);

        if (facing_right)
            gfx.drawImage(Assets.player_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), (int) width, (int) height, null);
        else
            gfx.drawImage(Assets.player_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), (int) width, (int) height, null);

        //Render player hitboxes (for testing purposes)
        gfx.setColor(Color.blue);
        gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }
}
