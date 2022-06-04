package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr33 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr33(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 1920, 680, 80, 280, new World(handler, MapHelper.CR32, Cr32.LEFT), new Cr32(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 680, 80, 280, new World(handler, MapHelper.CR34, Cr34.RIGHT), new Cr34(handler), 3));
    }
}