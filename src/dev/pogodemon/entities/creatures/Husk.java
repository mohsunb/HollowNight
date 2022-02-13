package dev.pogodemon.entities.creatures;

import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class Husk extends Creature
{

    public Husk(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
        CREATURE_TYPE = 1;

        bounds.x = 20;
        bounds.y = 10;
        bounds.width = (int) (width - 30);
        bounds.height = (int) (height - 16);
    }

    @Override
    public void update()
    {
        move();
    }

    @Override
    public void render(Graphics gfx)
    {
        if (facing_right)
            gfx.drawImage(Assets.husk_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), (int) width, (int) height, null);
        else
            gfx.drawImage(Assets.husk_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), (int) width, (int) height, null);

        gfx.setColor(Color.blue);
        gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
    }
}
