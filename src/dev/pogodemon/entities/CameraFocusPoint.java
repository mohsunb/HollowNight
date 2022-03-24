package dev.pogodemon.entities;

import dev.pogodemon.Game;
import dev.pogodemon.Launcher;
import dev.pogodemon.states.GameState;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.ArrayList;

public class CameraFocusPoint extends StaticEntity
{
    private float xOffset = 0;
    private float yOffset = 0;
    private boolean focused = true;
    private boolean vertical_lock = false;
    private boolean far = false;
    private boolean looking_up = false;
    private boolean looking_down = false;
    private int looking_timer = 0;
    private int look_counter = 0;

    private final ArrayList<Float> pX = new ArrayList<Float>();
    private final ArrayList<Float> pY = new ArrayList<Float>();
    private float ttY = 0;
    private boolean y_chosen = false;

    int counter = 0;

    public CameraFocusPoint(Handler handler)
    {
        super(handler, 0, 0, 0, 0);
    }

    public void toggleVerticalLock()
    {
        vertical_lock = !vertical_lock;
    }

    public boolean isLocked()
    {
        return vertical_lock;
    }

    public void lock()
    {
        if (!vertical_lock)
            vertical_lock = true;
    }

    public void unlock()
    {
        if (vertical_lock)
            vertical_lock = false;
    }

    public boolean isFocusedOnPlayer()
    {
        return focused;
    }

    public void unFocus()
    {
        if (focused)
            focused = false;
    }

    public void focus()
    {
        if (!focused)
            focused = true;
    }

    @Override
    public void update()
    {
        if (xOffset != 0 && !GameState.screen_shake)
            xOffset = 0;

        if (yOffset != 0 && !GameState.screen_shake)
            yOffset = 0;

        Player player = handler.getWorld().getEntityManager().getPlayer();

        if (player.looking_up && !looking_up)
        {
            looking_timer++;
            if (looking_timer >= Launcher.framerate_limit * 0.5)
            {
                looking_timer = 0;
                looking_up = true;
            }
        }

        if (looking_up && !player.looking_up)
            looking_up = false;

        if (player.looking_down && !looking_down)
        {
            looking_timer++;
            if (looking_timer >= Launcher.framerate_limit * 0.5)
            {
                looking_timer = 0;
                looking_down = true;
            }
        }

        if (looking_timer > 0 && !player.looking_down && !player.looking_up)
            looking_timer = 0;

        if (looking_down && !player.looking_down)
            looking_down = false;

        if (counter < Launcher.framerate_limit * 0.0625)
        {
            counter++;
            pX.add(player.getX() + player.bounds.x + player.bounds.width * 0.5F);
            pY.add(player.getY() + player.bounds.y + player.bounds.height * 0.5F);
        }

        else if (isFocusedOnPlayer())
        {
            if (!far && Math.sqrt(Math.pow(player.getCenterX() - pX.get(pX.size() - 1), 2) + Math.pow(player.getCenterY() - pY.get(pY.size() - 1), 2)) >= 199)
                far = true;

            else if (far && Math.sqrt(Math.pow(player.getCenterX() - pX.get(pX.size() - 1), 2) + Math.pow(player.getCenterY() - pY.get(pY.size() - 1), 2)) <= 40)
                far = false;

            float tX = pX.get(0);
            float tY = pY.get(0);
            setX(tX + xOffset);
            setY(tY + yOffset);
            pX.remove(0);
            pX.add(player.getCenterX());
            pY.remove(0);

            if (look_counter > 0 && !looking_up && !looking_down)
                look_counter = 0;

            if (looking_up)
            {
                pY.add((float) (player.getCenterY() - 200 * look_counter / (Launcher.framerate_limit * 0.0625)));
                y_chosen = false;
                if (look_counter < Launcher.framerate_limit * 0.0625)
                    look_counter++;
            }

            else if (looking_down)
            {
                pY.add((float) (player.getCenterY() + 200 * look_counter / (Launcher.framerate_limit * 0.0625)));
                y_chosen = false;
                if (look_counter < Launcher.framerate_limit * 0.0625)
                    look_counter++;
            }

            else if (vertical_lock)
            {
                if (ttY == 0 || ttY != player.getCenterY())
                    pY.add(pY.get(pY.size() - 1));
                else
                    pY.add(ttY);

                if (!y_chosen && player.grounded)
                {
                    ttY = player.getCenterY();
                    y_chosen = true;
                }
            }

            else if (far)
            {
                if (player.getCenterY() > getCenterY() + 50)
                    pY.add(pY.get(pY.size() - 1) + 10 * 144 / Launcher.framerate_limit);

                else if (player.getCenterY() < getCenterY() - 50)
                    pY.add(pY.get(pY.size() - 1) - 10 * 144 / Launcher.framerate_limit);

                else
                    pY.add(pY.get(pY.size() - 1));
            }

            else
                pY.add(player.getCenterY());

            if (!vertical_lock && y_chosen)
                y_chosen = false;
        }
    }

    public void clearCameraQueue()
    {
        int s = pX.size();
        pX.clear();
        pY.clear();
        for (int i = 0; i < s; i++)
        {
            pX.add(handler.getWorld().getEntityManager().getPlayer().getCenterX());
            pY.add(handler.getWorld().getEntityManager().getPlayer().getCenterY());
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
        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.RED);
            gfx.fillOval((int) (getX() - 5 - handler.getCamera().getxOffset()), (int) (getY() - 5 - handler.getCamera().getyOffset()), 10, 10);
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

    public float getXOffset()
    {
        return xOffset;
    }

    public float getYOffset()
    {
        return yOffset;
    }

    public void setXOffset(float xO)
    {
        xOffset = xO;
    }

    public void setYOffset(float yO)
    {
        yOffset = yO;
    }
}
