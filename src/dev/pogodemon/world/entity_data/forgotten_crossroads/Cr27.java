package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr27 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT_1 = 1;
    public static final int LEFT_2 = 2;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;

    public Cr27(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 320, 80, 240, new World(handler, MapHelper.CR26, Cr26.RIGHT), new Cr26(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 2320, 80, 280, new World(handler, MapHelper.CR28, Cr28.RIGHT), new Cr28(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 2040, 3640, 80, 280, new World(handler, MapHelper.CR29, Cr29.LEFT), new Cr29(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 960, 5280, 280, 80, new World(handler, MapHelper.CR30, Cr30.TOP), new Cr30(handler), 2));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 1672, 480, 0, "rock_25"));
    }
}