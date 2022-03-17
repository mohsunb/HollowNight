package dev.pogodemon.entities.objects;

import dev.pogodemon.Game;
import dev.pogodemon.Launcher;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class CollapsableFloor extends StaticEntity
{
    private int time, timer = 0;
    private boolean triggered = false;

    public CollapsableFloor(Handler handler, float x, float y, float width, float height, int time)
    {
        super(handler, x, y, width, height);
        this.time = time;
        setSolid(true);
    }

    private void collapse()
    {
        bounds.width = 0;
        bounds.height = 0;
        exists = false;
        setX(0);
        setY(0);
    }

    @Override
    public void update()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (exists)
        {
            if (!triggered)
            {
                if (player.isGrounded() && player.getCenterX() >= getX() && player.getCenterX() <= getX() + bounds.width && player.getCenterY() >= getY() - 200 && player.getCenterY() < getY())
                    triggered = true;
            }

            else
            {
                timer++;
                if (timer >= time)
                {
                    collapse();
                    triggered = false;
                    timer = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics gfx)
    {
        if (exists)
        {
            gfx.setColor(Color.darkGray);
            gfx.fillRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);

            if (Launcher.show_hitboxes)
            {
                gfx.setColor(Color.yellow);
                gfx.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 200), (int) (bounds.width), 200);
                gfx.setColor(Color.blue);
                gfx.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
            }
        }
    }

    @Override
    public void hasBeenHit()
    {

    }

    @Override
    public void playerContact() {

    }
}
