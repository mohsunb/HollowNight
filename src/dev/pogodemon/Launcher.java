package dev.pogodemon;

public class Launcher
{
    //Mainly for debugging purposes
    public static boolean show_hitboxes = false;
    public static boolean log_fps = false;

    public static final int game_width = 1920;
    public static final int game_height = 1080;
<<<<<<< HEAD
    public static final int framerate_limit = 144; // Game breaks if the framerate doesn't match the limit. So, log the fps if something feels off.
    public static final boolean exclusive_fullscreen = true; // Needs more testing (works properly in Windows, but shows the taskbar in Linux (GNOME))
=======
    public static final int framerate_limit = 60;
    public static final boolean exclusive_fullscreen = true;
    public static final String platform = System.getProperty("os.name").toLowerCase();
>>>>>>> 6aee207 (v0.3.6)

    public static void main(String[] args)
    {
        Game game = new Game("Hollow Night", game_width, game_height, exclusive_fullscreen);
        game.start();
    }

    //Note for future: being able to shadow dash through solid entities (e.g: shade gate, great husk sentry)
    /*
    if (isSolid() && handler.getWorld().getEntityManager().getPlayer().shadow_dashing && handler.getWorld().getEntityManager().getPlayer().dashing)
        setSolid(false);
    else if (!isSolid() && !handler.getWorld().getEntityManager().getPlayer().dashing)
        setSolid(true);
     */
}
