package dev.pogodemon.world.entity_data;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.InteractableItem;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.objects.Bench;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;
import dev.pogodemon.world.entity_data.forgotten_crossroads.Cr1;

public class Dirtmouth extends EntityData
{
    public static final int LEFT = 0;
    public static final int BOTTOM = 1;

    public Dirtmouth(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 0, 1000, 80, 760, new World(handler, MapHelper.KINGS_PASS, KingsPass.RIGHT), new KingsPass(handler), 3));
        handler.getWorld().spawnEntity(new Bench(handler, 9456, 4065));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 12600, 4700, 200, 500, new World(handler, MapHelper.CR1, Cr1.TOP), new Cr1(handler), 2));
    }
}
