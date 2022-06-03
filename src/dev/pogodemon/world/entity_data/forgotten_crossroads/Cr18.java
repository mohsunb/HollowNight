package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.*;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr18 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr18(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 5640, 1240, 80, 280, new World(handler, MapHelper.CR17, Cr17.LEFT), new Cr17(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 1320, 80, 280, new World(handler, MapHelper.CR2, Cr2.RIGHT_2), new Cr2(handler), 3));

        handler.getWorld().spawnEntity(new Tiktik(handler, 2678, 1039));
        handler.getWorld().spawnEntity(new Tiktik(handler, 3374, 1119));

        handler.getWorld().spawnEntity(new Crawlid(handler, 1121, 1712));
        handler.getWorld().spawnEntity(new Crawlid(handler, 3053, 1632));

        handler.getWorld().spawnEntity(new Vengefly(handler, 3613, 712));
        handler.getWorld().spawnEntity(new Vengefly(handler, 2103, 367));

        handler.getWorld().spawnEntity(new WanderingHusk(handler, 4025, 1632));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 2097, 760, 0, "rock_28"));

    }
}