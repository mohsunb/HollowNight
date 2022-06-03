package dev.pogodemon.world.entity_data.forgotten_crossroads;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.RoomTransition;
import dev.pogodemon.entities.creatures.Crawlid;
import dev.pogodemon.entities.creatures.Gruzzer;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.Tiktik;
import dev.pogodemon.entities.objects.GeoDeposit;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class Cr2 extends EntityData
{
    public static final int RIGHT_1 = 0;
    public static final int RIGHT_2 = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT_1 = 3;
    public static final int LEFT_2 = 4;
    public static final int LEFT_3 = 5;

    public Cr2(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == handler.getWorld().getID())
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        handler.getWorld().spawnEntity(new RoomTransition(handler, 3530, 2680, 80, 240, new World(handler, MapHelper.CR1, Cr1.LEFT), new Cr1(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 3530, 6040, 80, 280, new World(handler, MapHelper.CR18, Cr18.LEFT), new Cr18(handler), 1));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 80, 2640, 80, 280, new World(handler, MapHelper.CR3, Cr3.RIGHT), new Cr3(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 80, 6360, 80, 320, new World(handler, MapHelper.CR4, Cr4.RIGHT), new Cr4(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 80, 8120, 80, 240, new World(handler, MapHelper.CR5, Cr5.RIGHT), new Cr5(handler), 3));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 1480, 9612, 240, 240, new World(handler, MapHelper.CR8, Cr8.TOP), new Cr8(handler), 2));

        handler.getWorld().spawnEntity(new Tiktik(handler, 773, 8919));
        handler.getWorld().spawnEntity(new Tiktik(handler, 2284, 8319));
        handler.getWorld().spawnEntity(new Tiktik(handler, 2252, 7559));
        handler.getWorld().spawnEntity(new Tiktik(handler, 1274, 6759));
        handler.getWorld().spawnEntity(new Tiktik(handler, 733, 5719));
        handler.getWorld().spawnEntity(new Tiktik(handler, 2168, 5679));
        handler.getWorld().spawnEntity(new Tiktik(handler, 1601, 4319));

        handler.getWorld().spawnEntity(new Crawlid(handler, 1720, 3152));

        handler.getWorld().spawnEntity(new Gruzzer(handler, 2500, 2500));
        handler.getWorld().spawnEntity(new Gruzzer(handler, 1300, 2000));
        handler.getWorld().spawnEntity(new Gruzzer(handler, 2460, 4000));
        handler.getWorld().spawnEntity(new Gruzzer(handler, 970, 2500));
        handler.getWorld().spawnEntity(new Gruzzer(handler, 970, 5000));
        handler.getWorld().spawnEntity(new Gruzzer(handler, 2460, 6000));
        handler.getWorld().spawnEntity(new Gruzzer(handler, 970, 7000));
        handler.getWorld().spawnEntity(new Gruzzer(handler, 2460, 8000));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 1720, 327, 3, "rock_7"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 1418, 800, 0, "rock_8"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 1630, 800, 0, "rock_9"));

    }
}
