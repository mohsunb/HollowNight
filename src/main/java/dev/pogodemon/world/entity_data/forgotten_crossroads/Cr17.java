package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.HornheadHusk;
import dev.pogodemon.entities.creatures.LeapingHusk;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.WanderingHusk;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr17 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr17(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 6440, 1040, 80, 280, new World(handler, MapHelper.CR16, Cr16.LEFT), new Cr16(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 1040, 80, 280, new World(handler, MapHelper.CR18, Cr18.RIGHT), new Cr18(handler), 3));

        handler.getWorld().spawnEntity(new LeapingHusk(handler, 2514, 800));
        handler.getWorld().spawnEntity(new LeapingHusk(handler, 5434, 1100));
        handler.getWorld().spawnEntity(new HornheadHusk(handler, 1994, 1192));
        handler.getWorld().spawnEntity(new WanderingHusk(handler, 1514, 1192));
    }
}