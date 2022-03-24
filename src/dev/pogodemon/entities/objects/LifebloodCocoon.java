package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.creatures.Lifeseed;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class LifebloodCocoon extends Creature
{
    private int lifeseed_count;
    public LifebloodCocoon(Handler handler, float x, float y, int lifeseed_count)
    {
        super(handler, x, y, Assets.lifeblood_cocoon.getWidth(), Assets.lifeblood_cocoon.getHeight());
        setSolid(true);
        health = 1;
        this.lifeseed_count = lifeseed_count;
    }

    @Override
    public void update()
    {
        if (health == 0)
        {
            for (int i = 0; i < lifeseed_count; i++)
                handler.getWorld().spawnEntity(new Lifeseed(handler, (float) (getX() + bounds.width * 0.5), (float) (getY() + bounds.height * 0.5)));
            handler.getWorld().removeEntity(this);
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
        gfx.drawImage(Assets.lifeblood_cocoon, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {
        health = 0;
    }

    @Override
    public void fireballHit()
    {
        health = 0;
    }

    @Override
    public void playerContact()
    {

    }
}
