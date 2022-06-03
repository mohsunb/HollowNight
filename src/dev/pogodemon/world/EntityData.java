package dev.pogodemon.world;

import dev.pogodemon.utils.Handler;

public abstract class EntityData
{
    protected Handler handler;
    public EntityData(Handler handler)
    {
        this.handler = handler;
    }

    public abstract void spawnEntities();
}
