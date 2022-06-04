package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr26 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr26(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 920, 80, 320, new World(handler, MapHelper.CR23, Cr23.RIGHT_2), new Cr23(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 5840, 1000, 80, 240, new World(handler, MapHelper.CR27, Cr27.LEFT_1), new Cr27(handler), 1));
    }
}