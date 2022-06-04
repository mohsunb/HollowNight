package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr8 extends EntityData
{
    public static final int TOP = 0;
    public static final int RIGHT_1 = 1;
    public static final int RIGHT_2 = 2;
    public static final int LEFT_1 = 3;
    public static final int LEFT_2 = 4;

    public Cr8(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 800, 80, 240, new World(handler, MapHelper.CR7, Cr7.RIGHT), new Cr7(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 1760, 0, 240, 120, new World(handler, MapHelper.CR2, Cr2.BOTTOM), new Cr2(handler), 0));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 80, 2640, 80, 320, new World(handler, MapHelper.CR9, Cr9.RIGHT), new Cr9(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3640, 920, 80, 280, new World(handler, MapHelper.CR19, Cr19.LEFT), new Cr19(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3640, 2720, 80, 240, new World(handler, MapHelper.CR37, Cr37.LEFT_1), new Cr37(handler), 1));
    }
}