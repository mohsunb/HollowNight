package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr39 extends EntityData
{
    public static final int RIGHT_1 = 0;
    public static final int RIGHT_2 = 1;
    public static final int BOTTOM = 2;

    public Cr39(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3040, 760, 80, 240, new World(handler, MapHelper.CR37, Cr37.LEFT_2), new Cr37(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3040, 2920, 80, 200, new World(handler, MapHelper.CR40, Cr40.LEFT), new Cr40(handler), 1));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 2394, 2200, 0, "rock_15"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 2640, 2160, 3, "rock_16"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 2720, 1840, 3, "rock_17"));
    }
}