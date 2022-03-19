package dev.pogodemon.entities.objects;

import dev.pogodemon.entities.Geo;
import dev.pogodemon.utils.Handler;

import java.util.Random;

public class ChestGeo extends Chest
{
    private int geo;

    public ChestGeo(Handler handler, float x, float y, int geo)
    {
        super(handler, x, y);
        this.geo = geo;
    }

    @Override
    protected void open()
    {
        Random rand = new Random();

        int i = rand.nextInt(0, 4);
        for (int l = 0; l < i; l++)
            handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY() - bounds.height, 2));
        geo -= i * 25;

        i = rand.nextInt(0, 5);
        for (int l = 0; l < i; l++)
            handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY() - bounds.height, 1));
        geo -= i * 5;

        for (int l = 0; l < geo; l++)
            handler.getWorld().spawnEntity(new Geo(handler, getCenterX(), getCenterY() - bounds.height, 0));
    }

    @Override
    public void fireballHit() {

    }
}
