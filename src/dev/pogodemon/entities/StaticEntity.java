package dev.pogodemon.entities;

import dev.pogodemon.utils.Handler;

public abstract class StaticEntity extends Entity
{

    public StaticEntity(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
    }
}
