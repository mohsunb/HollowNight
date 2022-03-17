package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.ArrayList;

public class CameraFocusPoint extends StaticEntity
{
    private boolean focused = true;
    private boolean vertical_lock = false;
    private boolean far = false;

    private final ArrayList<Float> pX = new ArrayList<Float>();
    private float ttY = 0;
    private boolean y_chosen = false;
    private final ArrayList<Float> pY = new ArrayList<Float>();

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
        handler.getCamera().centerOnEntity(this);
        Player player = handler.getWorld().getEntityManager().getPlayer();

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
            setX(tX);
            setY(tY);
            pX.remove(0);
            pX.add(player.getCenterX());
            pY.remove(0);

            if (player.looking_up)
                pY.add(player.getCenterY() - 200);
            else if (player.looking_down)
                pY.add(player.getCenterY() + 200);
            else if (vertical_lock)
            {
                if (ttY == 0)
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
                    pY.add(pY.get(pY.size() - 1) + 10);

                else if (player.getCenterY() < getCenterY() - 50)
                    pY.add(pY.get(pY.size() - 1) - 10);

                else
                    pY.add(pY.get(pY.size() - 1));
            }

            else
                pY.add(player.getCenterY());

            if (!vertical_lock && y_chosen)
                y_chosen = false;
        }
    }

    @Override
    public void render(Graphics gfx)
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
    public void playerContact()
    {

    }
}
