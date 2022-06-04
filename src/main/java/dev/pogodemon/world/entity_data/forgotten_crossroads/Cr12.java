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

public class Cr12 extends EntityData
{
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Cr12(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 80, 1360, 80, 320, new World(handler, MapHelper.CR11, Cr11.RIGHT), new Cr11(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 6720, 1360, 80, 320, new World(handler, MapHelper.CR13, Cr13.LEFT_1), new Cr13(handler), 1));

        handler.getWorld().spawnEntity(new WanderingHusk(handler, 2670, 1552));
        handler.getWorld().spawnEntity(new HornheadHusk(handler, 3738, 1552));
        handler.getWorld().spawnEntity(new WanderingHusk(handler, 5749, 1552));
    }
}