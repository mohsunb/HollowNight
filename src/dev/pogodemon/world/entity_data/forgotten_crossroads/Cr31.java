package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.LeapingHusk;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr31 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT_1 = 1;
    public static final int LEFT_2 = 2;
    public static final int TOP = 3;

    public Cr31(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 1200, 0, 240, 160, new World(handler, MapHelper.CR23, Cr23.BOTTOM), new Cr23(handler), 0));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3520, 3000, 80, 200, new World(handler, MapHelper.CR30, Cr30.LEFT), new Cr30(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 2960, 80, 240, new World(handler, MapHelper.CR32, Cr32.RIGHT), new Cr32(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 840, 80, 240, new World(handler, MapHelper.CR35, Cr35.RIGHT), new Cr35(handler), 3));

        handler.getWorld().spawnEntity(new LeapingHusk(handler, 2790, 3180));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 2906, 560, 0, "rock_22"));
    }
}