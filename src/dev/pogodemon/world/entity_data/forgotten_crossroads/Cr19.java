package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr19 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr19(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 3800, 80, 400, new World(handler, MapHelper.CR8, Cr8.RIGHT_1), new Cr8(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 4720, 3960, 80, 240, new World(handler, MapHelper.CR20, Cr20.LEFT), new Cr20(handler), 1));
    }
}