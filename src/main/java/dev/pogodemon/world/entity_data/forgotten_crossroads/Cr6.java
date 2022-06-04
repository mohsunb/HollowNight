package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr6 extends EntityData
{
    public static final int RIGHT_1 = 0;
    public static final int RIGHT_2 = 1;

    public Cr6(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 4680, 760, 80, 240, new World(handler, MapHelper.CR5, Cr5.LEFT), new Cr5(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 4680, 3840, 80, 240, new World(handler, MapHelper.CR7, Cr7.LEFT), new Cr7(handler), 1));
    }
}