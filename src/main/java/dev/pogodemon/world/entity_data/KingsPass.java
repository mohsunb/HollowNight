package dev.pogodemon.world.entity_data;

import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.entities.*;
import dev.pogodemon.entities.creatures.Crawlid;
import dev.pogodemon.entities.creatures.Shade;
import dev.pogodemon.entities.creatures.Vengefly;
import dev.pogodemon.entities.objects.*;
import dev.pogodemon.items.Items;
import dev.pogodemon.states.GameState;
import dev.pogodemon.states.State;
import dev.pogodemon.utils.Coordinate;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.World;

public class KingsPass extends EntityData
{
    public static final int LEFT = 0;
    public static final int MIDDLE = 1;
    public static final int RIGHT = 2;

    public KingsPass(Handler handler)
    {
        super(handler);
    }

    @Override
    public void spawnEntities()
    {
        if (GameFlags.hasShade && GameFlags.shadeRoomID == MapHelper.KINGS_PASS)
            handler.getWorld().spawnEntity(new Shade(handler, GameFlags.shadeSpawnX, GameFlags.shadeSpawnY));

        if (!GameFlags.data.getBoolean("secret_1"))
            handler.getWorld().spawnEntity(new BlackArea(handler, 0, 3900, 1700, 1100, false, "secret_1"));
        if (!GameFlags.data.getBoolean("secret_2"))
            handler.getWorld().spawnEntity(new BlackArea(handler, 4600, 4300, 1700, 700, true, "secret_2"));
        if (!GameFlags.data.getBoolean("secret_3"))
            handler.getWorld().spawnEntity(new BlackArea(handler, 4590, 1700, 1100, 2300, true, "secret_3"));

        handler.getWorld().spawnEntity(new LifebloodCocoon(handler, 4680, 920, 2));

        if (!GameFlags.data.getBoolean("secret_4"))
        {
            handler.getWorld().spawnEntity(new BreakableWallKingsPass(handler, 7380, 960, 100, 240, false, "secret_4"));
            handler.getWorld().spawnEntity(new BlackArea(handler, 7460, 800, 1000, 500, true, "secret_4"));
        }

        if (!GameFlags.data.getBoolean("secret_5"))
        {
            handler.getWorld().spawnEntity(new CollapsableFloorKingsPass(handler, 8960, 1200));
            handler.getWorld().spawnEntity(new BlackArea(handler, 7960, 1228, 4500, 1500, false, "secret_5"));
        }

        if (!GameFlags.data.getBoolean("secret_6"))
            handler.getWorld().spawnEntity(new HardenedSurfaceBack(handler, 10840, 1270, new HardenedSurfaceFront(handler, 10760, 1200), "secret_6"));

        if (!GameFlags.data.getBoolean("secret_7"))
            handler.getWorld().spawnEntity(new BlackArea(handler, 12000, 2250, 2000, 2500, false, "secret_7"));

        if (!GameFlags.dirtmouth_entrance_opened)
            handler.getWorld().spawnEntity(new BreakableWallDirtmouthEntrance(handler, 14640, 440, 120, 760));
        handler.getWorld().spawnEntity(new RoomTransition(handler, 14700, 440, 200, 760, new World(handler, MapHelper.DIRTMOUTH, Dirtmouth.LEFT), new Dirtmouth(handler), 1));

        if (!GameFlags.data.getBoolean("secret_2"))
            handler.getWorld().spawnEntity(new BreakableWallKingsPass(handler, 4570, 4640, 100, 240, false, "secret_2"));
        if (!GameFlags.data.getBoolean("secret_8"))
            handler.getWorld().spawnEntity(new BreakableWallKingsPass(handler, 5610, 4640, 100, 240, false, "secret_8"));
        if (!GameFlags.data.getBoolean("secret_9"))
            handler.getWorld().spawnEntity(new BreakableWallKingsPass(handler, 6690, 4640, 100, 240, false, "secret_9"));
        if (!GameFlags.data.getBoolean("secret_10"))
            handler.getWorld().spawnEntity(new BreakableWallKingsPass(handler, 7810, 4640, 100, 240, false, "secret_10"));
        if (!GameFlags.data.getBoolean("secret_11"))
            handler.getWorld().spawnEntity(new BreakableWallKingsPass(handler, 9600, 4640, 100, 240, false, "secret_11"));
        if (!GameFlags.data.getBoolean("secret_12"))
            handler.getWorld().spawnEntity(new BreakableWallKingsPass(handler, 5700, 3560, 100, 240, true, "secret_12"));

        handler.getWorld().spawnEntity(new ChestItem(handler, 13400, 4420, Items.hallownestSeal, "chest_1"));

        handler.getWorld().spawnEntity(new Stalagmite(handler, 9410, 2880, 700));
        handler.getWorld().spawnEntity(new Stalagmite(handler, 4960, 1880, 600));
        handler.getWorld().spawnEntity(new Stalagmite(handler, 6800, 1880, 700));

        handler.getWorld().spawnEntity(new Stalagmite(handler, 10600, 2120, 700));
        handler.getWorld().spawnEntity(new Stalagmite(handler, 10900, 2120, 700));
        handler.getWorld().spawnEntity(new Stalagmite(handler, 11150, 2120, 700));
        handler.getWorld().spawnEntity(new Stalagmite(handler, 11250, 2120, 700));
        handler.getWorld().spawnEntity(new Stalagmite(handler, 11550, 2080, 700));

        handler.getWorld().spawnEntity(new Vengefly(handler, 6890, 3232));
        handler.getWorld().spawnEntity(new Vengefly(handler, 4900, 2700));
        handler.getWorld().spawnEntity(new Vengefly(handler, 6500, 2100));

        handler.getWorld().spawnEntity(new GeoDeposit(handler, 960, 4800, 0, "rock_1"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 12245, 5200, 0, "rock_2"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 8230, 1720, 0, "rock_3"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 4821, 2280, 0, "rock_4"));
        handler.getWorld().spawnEntity(new GeoDeposit(handler, 12300, 3120, 0, "rock_5"));

        handler.getWorld().spawnEntity(new Crawlid(handler, 7350, 4680));
        handler.getWorld().spawnEntity(new Crawlid(handler, 8733, 4680));
        handler.getWorld().spawnEntity(new Crawlid(handler, 11645, 5100));
        handler.getWorld().spawnEntity(new Crawlid(handler, 6438, 1200));

        handler.getWorld().spawnEntity(new Hazard(handler, 980, 3300, 200, 100));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 960, 3300, 0));

        handler.getWorld().spawnEntity(new Hazard(handler, 640, 2250, 180, 100));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 640, 2250, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 700, 2250, 0));

        handler.getWorld().spawnEntity(new Hazard(handler, 1020, 1800, 340, 100));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 1000, 1800, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 1139, 1800, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 1278, 1800, 0));

        handler.getWorld().spawnEntity(new Hazard(handler, 620, 950, 200, 100));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 700, 950, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 620, 950, 0));

        handler.getWorld().spawnEntity(new Hazard(handler, 12560, 3220, 1400, 100));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 12560, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 12640, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 12720, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 12800, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 12880, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 12960, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13040, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13120, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13200, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13280, 3220, 0));

        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13520, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13600, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13680, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13760, 3220, 0));
        handler.getWorld().spawnEntity(new SpikesMedium(handler, 13840, 3220, 0));

        handler.getWorld().spawnEntity(new Hazard(handler, 10520, 3140, 1040, 100));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 10520, 3140, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 10650, 3140, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 10780, 3140, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 10910, 3140, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 11040, 3140, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 11170, 3140, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 11300, 3140, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 11430, 3140, 0));

        handler.getWorld().spawnEntity(new Hazard(handler, 4640, 3840, 600, 100));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 4640, 3840, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 4770, 3840, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 4900, 3840, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 5030, 3840, 0));
        handler.getWorld().spawnEntity(new SpikesLarge(handler, 5080, 3840, 0));

        handler.getWorld().spawnEntity(new HazardRespawnPoint(handler, 800, 4480, 500, 400, 1000, 4751));
        handler.getWorld().spawnEntity(new HazardRespawnPoint(handler, 3000, 4480, 500, 400, 3200, 4751));
        handler.getWorld().spawnEntity(new HazardRespawnPoint(handler, 7612, 3040, 500, 400, 7700, 3312));
        handler.getWorld().spawnEntity(new HazardRespawnPoint(handler, 9760, 2080, 320, 600, 9886, 2551));
        handler.getWorld().spawnEntity(new HazardRespawnPoint(handler, 11600, 2880, 900, 320, 12069, 3071));
        handler.getWorld().spawnEntity(new HazardRespawnPoint(handler, 13360, 2880, 160, 320, 13420, 3071));
        handler.getWorld().spawnEntity(new HazardRespawnPoint(handler, 13920, 2880, 320, 320, 14063, 3071));
        handler.getWorld().spawnEntity(new HazardRespawnPoint(handler, 11720, 1880, 440, 320, 12025, 2071));

        handler.getWorld().spawnEntity(new VerticalCameraLock(handler, 720, 4420, 9400, 620));
        handler.getWorld().spawnEntity(new VerticalCameraLock(handler, 12400, 2800, 1800, 600));
        handler.getWorld().spawnEntity(new VerticalCameraLock(handler, 10400, 2400, 1200, 500));
        handler.getWorld().spawnEntity(new VerticalCameraLock(handler, 7677, 3050, 1500, 450));
        handler.getWorld().spawnEntity(new VerticalCameraLock(handler, 10000, 800, 5000, 380));
        handler.getWorld().spawnEntity(new VerticalCameraLock(handler, 7300, 900, 1520, 300));
    }
}
