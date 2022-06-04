package dev.pogodemon;

import dev.pogodemon.utils.Coordinate;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.utils.SaveData;
import dev.pogodemon.world.World;

public class GameFlags
{
    /*
    Windows >> C:/Users/USER_NAME
    Linux >> /home/USER_NAME
     */
    public static SaveData data = new SaveData(System.getProperty("user.home") + "/hollow_night_savefile.dat");

    //Shade flags
    public static boolean hasShade = data.getBoolean("has_shade");
    public static int shadeRoomID = (int) data.getValue("shade_room_id");
    public static int shadeGeo = (int) data.getValue("shade_geo");
    public static float shadeSpawnX = data.getValue("shade_spawn_x");
    public static float shadeSpawnY = data.getValue("shade_spawn_y");

    //Loading
    public static int load_state = (int) data.getValue("load_state");
    public static int load_map_id = (int) data.getValue("load_map_id");
    public static Coordinate load_coordinates = new Coordinate((int) data.getValue("load_coordinates_x"), (int) data.getValue("load_coordinates_y"));

    //Respawning
    public static int respawn_state = (int) data.getValue("respawn_state");
    public static int respawn_map_id = (int) data.getValue("respawn_map_id");
    public static Coordinate respawn_coordinates = new Coordinate((int) data.getValue("respawn_coordinates_x"), (int) data.getValue("respawn_coordinates_y"));

    //Misc
    public static boolean bench_vfx = true; // Not to apply healing vfx when loading the game
    public static boolean dirtmouth_entrance_opened = data.getBoolean("dirtmouth_entrance_opened");

    public static void setShadeState(int map_id, int geo, float x, float y)
    {
        hasShade = true;
        data.updateValue("has_shade", true);
        shadeRoomID = map_id;
        data.updateValue("shade_room_id", shadeRoomID);
        shadeGeo = geo;
        data.updateValue("shade_geo", shadeGeo);
        shadeSpawnX = x;
        data.updateValue("shade_spawn_x", shadeSpawnX);
        shadeSpawnY = y;
        data.updateValue("shade_spawn_y", shadeSpawnY);
        data.updateLocalFile();
    }

    public static void setRespawnState(int map_id, float x, float y)
    {
        respawn_map_id = map_id;
        respawn_coordinates = new Coordinate(x, y);
        data.updateValue("respawn_map_id", respawn_map_id);
        data.updateValue("respawn_coordinates_x", respawn_coordinates.getX());
        data.updateValue("respawn_coordinates_y", respawn_coordinates.getY());
        data.updateLocalFile();
    }
}
