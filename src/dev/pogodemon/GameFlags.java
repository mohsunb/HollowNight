package dev.pogodemon;

import dev.pogodemon.utils.Coordinate;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.World;

public class GameFlags
{
    //Shade flags
    public static boolean hasShade = false;
    public static int shadeRoomID, shadeGeo;
    public static float shadeSpawnX, shadeSpawnY;

    //Loading
    public static int load_state = World.NEUTRAL;
    public static int load_map_id = MapHelper.KINGS_PASS;
    public static Coordinate load_coordinates = new Coordinate(3170, 0);

    //Respawning
    public static int respawn_state = World.SLEEPING;
    public static int respawn_map_id = MapHelper.KINGS_PASS;
    public static Coordinate respawn_coordinates = new Coordinate(3122, 4752);

    //Misc
    public static boolean bench_vfx = true; // Not to apply healing vfx when loading the game

    public static void setShadeState(int map_id, int geo, float x, float y)
    {
        hasShade = true;
        shadeRoomID = map_id;
        shadeGeo = geo;
        shadeSpawnX = x;
        shadeSpawnY = y;
    }

    public static void setRespawnState(int map_id, float x, float y)
    {
        respawn_map_id = map_id;
        respawn_coordinates = new Coordinate(x, y);
    }
}
