package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr9 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr9(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 5240, 800, 80, 360, new World(handler, MapHelper.CR8, Cr8.LEFT_2), new Cr8(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 80, 680, 80, 360, new World(handler, MapHelper.CR10, Cr10.RIGHT), new Cr10(handler), 3));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 3222, 720, 0, "rock_10"));
    }
}