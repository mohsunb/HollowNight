package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Crawlid;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.Tiktik;
import dev.pogodemon.entities.creatures.Vengefly;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr23 extends EntityData
{
    public static final int RIGHT_1 = 0;
    public static final int RIGHT_2 = 1;
    public static final int LEFT_1 = 2;
    public static final int LEFT_2 = 3;
    public static final int TOP = 4;
    public static final int BOTTOM = 5;

    public Cr23(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 2880, 80, 440, new World(handler, MapHelper.CR21, Cr21.RIGHT), new Cr21(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 960, 0, 280, 120, new World(handler, MapHelper.CR16, Cr16.BOTTOM), new Cr16(handler), 0));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 2120, 840, 80, 280, new World(handler, MapHelper.CR25, Cr25.LEFT), new Cr25(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 40, 4800, 80, 400, new World(handler, MapHelper.CR24, Cr24.RIGHT), new Cr24(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 2120, 2720, 80, 360, new World(handler, MapHelper.CR26, Cr26.LEFT), new Cr26(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 1040, 5680, 280, 160, new World(handler, MapHelper.CR31, Cr31.TOP), new Cr31(handler), 2));

        handler.getWorld().spawnEntity(new Tiktik(handler, 796, 3559));
        handler.getWorld().spawnEntity(new Tiktik(handler, 1000, 4799));

        handler.getWorld().spawnEntity(new Vengefly(handler, 825, 1152));

        handler.getWorld().spawnEntity(new Crawlid(handler, 845, 1912));

        handler.getWorld().spawnEntity(new Vengefly(handler, 893, 4072));
    }
}