package dev.pogodemon;

public class Launcher
{
    public static final int game_width = 1920;
    public static final int game_height = 1080;
    public static final int framerate_limit = 144; // Game breaks if the framerate doesn't match the limit. So, log the fps if something feels off.
    public static final boolean exclusive_fullscreen = false; // Needs more testing

    public static void main(String[] args)
    {
        Game game = new Game("Hollow Night", game_width, game_height, exclusive_fullscreen);
        game.start();
    }
}
