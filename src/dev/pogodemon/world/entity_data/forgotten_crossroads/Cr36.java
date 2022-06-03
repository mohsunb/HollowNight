package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Crawlid;
import dev.pogodemon.entities.creatures.HuskBully;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.Vengefly;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr36 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr36(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 5240, 2360, 80, 280, new World(handler, MapHelper.CR35, Cr35.LEFT), new Cr35(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 2320, 80, 400, new World(handler, MapHelper.CR37, Cr37.RIGHT_2), new Cr37(handler), 3));

        handler.getWorld().spawnEntity(new Vengefly(handler, 3778, 2675));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 1340, 3320, 0, "rock_18"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 4924, 3280, 0, "rock_19"));

        handler.getWorld().spawnEntity(new HuskBully(handler, 1022, 3272));
    }
}