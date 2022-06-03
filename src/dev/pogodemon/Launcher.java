package dev.pogodemon;

import dev.pogodemon.utils.SaveData;

public class Launcher
{
    private static final SaveData config = new SaveData(System.getProperty("user.home") + "/HollowNight.cfg");

    public static boolean show_hitboxes = config.getBoolean("hitboxes");
    public static boolean show_world_name = config.getBoolean("room_indicator");
    public static boolean show_coordinates = config.getBoolean("coordinates");
    public static boolean log_fps = config.getBoolean("fps_counter");;
    public static boolean show_world_bounds = true;
    public static boolean hide_cursor = true;

    public static final int game_width = 1920;
    public static final int game_height = 1080;
    public static final int framerate_limit = (int) config.getValue("fps_cap");
    public static final boolean exclusive_fullscreen = config.getBoolean("fullscreen");;
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.uiScale", String.valueOf(config.getBoolean("scaling")));
        Game game = new Game("Hollow Night", game_width, game_height, exclusive_fullscreen);
        game.start();
    }
}