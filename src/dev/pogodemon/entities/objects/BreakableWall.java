package dev.pogodemon.entities.objects;

import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

public abstract class BreakableWall extends StaticEntity
{
    public BreakableWall(Handler handler, float x, float y, float width, float height, int health)
    {
        super(handler, x, y, width, height);
        this.health = health;
        setSolid(true);
    }

    @Override
    public void update()
    {
        if (exists)
        {
            if (health <= 0)
            {
                exists = false;
                setX(0);
                setY(0);
                bounds.width = 0;
                bounds.height = 0;
            }
        }
    }

    @Override
    public void hasBeenHit()
    {
        health--;
    }

    @Override
    public void playerContact()
    {

    }
}
