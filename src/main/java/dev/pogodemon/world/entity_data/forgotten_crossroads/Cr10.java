package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr10 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int BOTTOM = 1;

    public Cr10(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 5280, 440, 80, 280, new World(handler, MapHelper.CR9, Cr9.LEFT), new Cr9(handler), 1));
    }
}