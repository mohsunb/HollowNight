package dev.pogodemon.entities.creatures;

import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class Husk extends Creature
{
    public Husk(Handler handler, float x, float y)
    {
        super(handler, x, y, DEFAULT_WIDTH , DEFAULT_HEIGHT);
        CREATURE_TYPE = 1;
        health = 15;
        bounds.x = 20;
        bounds.y = 10;
        bounds.width = 70;
        bounds.height = 105;

        is_harmful = true;
    }

    @Override
    public void update()
    {
        if (exists)
        {
            if (health <= 0)
                exists = false;
            move();
        }

        else if (x != 0 && y != 0)
        {
            x = 0;
            y = 0;
        }
    }

    @Override
    public void render(Graphics gfx)
    {
        if (exists)
        {
            if (facing_right)
                gfx.drawImage(Assets.husk_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset())/*, (int) width, (int) height*/, null);
            else
                gfx.drawImage(Assets.husk_left, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset())/*, (int) width, (int) height*/, null);

            /*g//Render hitboxes
            fx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);

            //husk hp (for attack debugging)
            gfx.setColor(Color.black);
            gfx.drawRect((int) (x  - handler.getCamera().getxOffset() + 15), (int) (y  - handler.getCamera().getyOffset() - 10), (int) (width),10);
            gfx.setColor(Color.red);
            gfx.fillRect((int) (x  - handler.getCamera().getxOffset() + 15), (int) (y  - handler.getCamera().getyOffset() - 10), (int) (width * health / 15),10);*/
        }
    }
}
