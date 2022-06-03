package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Crawlid;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.Tiktik;
import dev.pogodemon.entities.creatures.WanderingHusk;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;
import dev.pogodemon.world.entity_data.Dirtmouth;

public class Cr1 extends EntityData
{
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;

    public Cr1(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 0, 2240, 80, 200, new World(handler, MapHelper.CR2, Cr2.RIGHT_1), new Cr2(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 7100, 1600, 80, 520, new World(handler, MapHelper.CR11, Cr11.LEFT), new Cr11(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3720, 0, 280, 80, new World(handler, MapHelper.DIRTMOUTH, Dirtmouth.BOTTOM), new Dirtmouth(handler), 0));

        handler.getWorld().spawnEntity(new Tiktik(handler, 5653, 2159));
        handler.getWorld().spawnEntity(new Tiktik(handler, 631, 2239));

        handler.getWorld().spawnEntity(new Crawlid(handler, 593, 2632));

        handler.getWorld().spawnEntity(new WanderingHusk(handler, 3096, 2752));
        handler.getWorld().spawnEntity(new WanderingHusk(handler, 5983, 2552));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 3395, 2800, 0, "rock_6"));
    }
}