package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr37 extends EntityData
{
    public static final int RIGHT_1 = 0;
    public static final int RIGHT_2 = 1;
    public static final int LEFT_1 = 2;
    public static final int LEFT_2 = 3;

    public Cr37(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3840, 1120, 80, 520, new World(handler, MapHelper.CR38, Cr38.LEFT), new Cr38(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3840, 2520, 80, 200, new World(handler, MapHelper.CR36, Cr36.LEFT), new Cr36(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 1360, 80, 320, new World(handler, MapHelper.CR8, Cr8.RIGHT_2), new Cr8(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 2680, 80, 280, new World(handler, MapHelper.CR39, Cr39.RIGHT_1), new Cr39(handler), 3));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 680, 160, 2, "rock_11"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 840, 200, 3, "rock_12"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 1066, 640, 0, "rock_13"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 3124, 560, 0, "rock_14"));
    }
}