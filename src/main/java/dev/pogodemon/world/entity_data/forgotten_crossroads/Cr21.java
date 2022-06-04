package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.HuskBully;
import dev.pogodemon.entities.creatures.LeapingHusk;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.WanderingHusk;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr21 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int TOP = 2;

    public Cr21(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 1840, 80, 240, new World(handler, MapHelper.CR20, Cr20.RIGHT), new Cr20(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 480, 0, 360, 160, new World(handler, MapHelper.CR22, Cr22.BOTTOM), new Cr22(handler), 0));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 8400, 880, 80, 440, new World(handler, MapHelper.CR23, Cr23.LEFT_1), new Cr23(handler), 1));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 2162, 1240, 0, "rock_26"));

        handler.getWorld().spawnEntity(new LeapingHusk(handler, 1136, 2050));
        handler.getWorld().spawnEntity(new HuskBully(handler, 1491, 2112));
        handler.getWorld().spawnEntity(new HuskBully(handler, 2485, 1192));
        handler.getWorld().spawnEntity(new WanderingHusk(handler, 2183, 2112));
    }
}