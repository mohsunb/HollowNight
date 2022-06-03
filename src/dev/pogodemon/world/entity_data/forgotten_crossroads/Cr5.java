package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr5 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr5(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 5800, 1040, 80, 280, new World(handler, MapHelper.CR2, Cr2.LEFT_3), new Cr2(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 1080, 80, 280, new World(handler, MapHelper.CR6, Cr6.RIGHT_1), new Cr6(handler), 3));
    }
}
