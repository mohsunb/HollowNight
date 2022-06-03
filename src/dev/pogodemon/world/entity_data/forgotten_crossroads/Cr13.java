package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr13 extends EntityData
{
    public static final int RIGHT_1 = 0;
    public static final int RIGHT_2 = 1;
    public static final int LEFT_1 = 2;
    public static final int LEFT_2 = 3;

    public Cr13(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 800, 80, 360, new World(handler, MapHelper.CR12, Cr12.RIGHT), new Cr12(handler), 3));


        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 3000, 80, 280, new World(handler, MapHelper.CR16, Cr16.RIGHT), new Cr16(handler), 3));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 2440, 720, 80, 360, new World(handler, MapHelper.CR14, Cr14.LEFT), new Cr14(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 2480, 3240, 80, 400, new World(handler, MapHelper.CR15, Cr15.LEFT), new Cr15(handler), 1));
    }
}