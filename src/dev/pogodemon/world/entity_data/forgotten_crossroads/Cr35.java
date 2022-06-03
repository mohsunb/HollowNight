package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Crawlid;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.Vengefly;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr35 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr35(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 7880, 1200, 80, 280, new World(handler, MapHelper.CR31, Cr31.LEFT_1), new Cr31(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 1280, 80, 280, new World(handler, MapHelper.CR36, Cr36.RIGHT), new Cr36(handler), 3));

        handler.getWorld().spawnEntity(new Vengefly(handler, 408, 576));
        handler.getWorld().spawnEntity(new Vengefly(handler, 720, 402));

        handler.getWorld().spawnEntity(new Vengefly(handler, 2339, 1022));
        handler.getWorld().spawnEntity(new Vengefly(handler, 2993, 1188));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 3918, 880, 0, "rock_20"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 7410, 280, 2, "rock_21"));
    }
}