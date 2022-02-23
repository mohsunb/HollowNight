package dev.pogodemon;

public class Launcher
{
    public static boolean show_hitboxes = false;
    public static final double resolution_scaling = 1.00; //for future usage
    public static final int game_width = (int) Math.floor(1920 * resolution_scaling);
    public static final int game_height = (int) Math.floor(1080 * resolution_scaling);
    public static final int framerate_limit = 144; // Game breaks if the framerate doesn't match the limit. So, log the fps if something feels off.
    public static final boolean exclusive_fullscreen = false; // Needs more testing (works properly in Windows, but shows the taskbar in Linux (GNOME))

    public static void main(String[] args)
    {
        Game game = new Game("Hollow Night", game_width, game_height, exclusive_fullscreen);
        game.start();
    }
}
