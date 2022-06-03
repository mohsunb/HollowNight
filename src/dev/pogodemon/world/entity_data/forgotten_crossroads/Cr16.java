package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.Geo;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.*;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr16 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int BOTTOM = 2;

    public Cr16(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 5840, 720, 80, 360, new World(handler, MapHelper.CR13, Cr13.LEFT_2), new Cr13(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 1160, 80, 280, new World(handler, MapHelper.CR17, Cr17.RIGHT), new Cr17(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 4360, 2000, 280, 160, new World(handler, MapHelper.CR23, Cr23.TOP), new Cr23(handler), 2));

        handler.getWorld().spawnEntity(new Vengefly(handler, 2340, 1124));

        handler.getWorld().spawnEntity(new Vengefly(handler, 3989, 549));
        handler.getWorld().spawnEntity(new Vengefly(handler, 4794, 549));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 4408, 920, 0, "rock_29"));

        handler.getWorld().spawnEntity(new HornheadHusk(handler, 1230, 1312));
    }
}