package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.HornheadHusk;
import dev.pogodemon.entities.creatures.HuskBully;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.WanderingHusk;
import dev.pogodemon.entities.objects.ChestGeo;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr20 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr20(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 4880, 80, 280, new World(handler, MapHelper.CR19, Cr19.RIGHT), new Cr19(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 5560, 4800, 80, 360, new World(handler, MapHelper.CR21, Cr21.LEFT), new Cr21(handler), 1));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 4873, 680, 0, "rock_27"));

        handler.getWorld().spawnEntity(new WanderingHusk(handler, 3165, 3192));
        handler.getWorld().spawnEntity(new HornheadHusk(handler, 1866, 3192));
        handler.getWorld().spawnEntity(new HuskBully(handler, 1198, 3192));

        handler.getWorld().spawnEntity(new ChestGeo(handler, 362, 4330, "chest_2", 115, 7, 2));
    }
}