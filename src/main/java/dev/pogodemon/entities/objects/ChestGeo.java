package dev.pogodemon.entities.objects;

import dev.pogodemon.entities.Geo;
import dev.pogodemon.utils.Handler;

public class ChestGeo extends Chest
{
    private final int count_0, count_1, count_2;

    public ChestGeo(Handler handler, float x, float y, String entry, int count_0)
    {
        super(handler, x, y, entry);
        this.count_0 = count_0;
        this.count_1 = 0;
        this.count_2 = 0;
    }

    public ChestGeo(Handler handler, float x, float y, String entry, int count_0, int count_1)
    {
        super(handler, x, y, entry);
        this.count_0 = count_0;
        this.count_1 = count_1;
        this.count_2 = 0;
    }

    public ChestGeo(Handler handler, float x, float y, String entry, int count_0, int count_1, int count_2)
    {
        super(handler, x, y, entry);
        this.count_0 = count_0;
        this.count_1 = count_1;
        this.count_2 = count_2;
    }

    @Override
    protected void open()
    {
        for (int i = 0; i < count_0; i++)
            handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY() - bounds.height, 0));
        for (int i = 0; i < count_1; i++)
            handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY() - bounds.height, 1));
        for (int i = 0; i < count_2; i++)
            handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY() - bounds.height, 2));
    }
}
