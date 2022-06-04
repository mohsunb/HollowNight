package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.BlackArea;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.HornheadHusk;
import dev.pogodemon.entities.creatures.HuskBully;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.WanderingHusk;
import dev.pogodemon.entities.objects.Bench;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr30 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int TOP = 2;

    public Cr30(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 6320, 0, 280, 80, new World(handler, MapHelper.CR27, Cr27.BOTTOM), new Cr27(handler), 0));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 880, 80, 240, new World(handler, MapHelper.CR31, Cr31.RIGHT), new Cr31(handler), 3));

        handler.getWorld().spawnEntity(new HornheadHusk(handler, 6624, 2112));
        handler.getWorld().spawnEntity(new HuskBully(handler, 7906, 2112));
        handler.getWorld().spawnEntity(new WanderingHusk(handler, 9613, 2112));

        handler.getWorld().spawnEntity(new Bench(handler, 12772, 1470));
    }
}