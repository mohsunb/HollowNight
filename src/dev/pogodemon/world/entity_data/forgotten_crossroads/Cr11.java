package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr11 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr11(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 80, 1800, 80, 520, new World(handler, MapHelper.CR1, Cr1.RIGHT), new Cr1(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 6860, 1920, 80, 400, new World(handler, MapHelper.CR12, Cr12.LEFT), new Cr12(handler), 1));
    }
}