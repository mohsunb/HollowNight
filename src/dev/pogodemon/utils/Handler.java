package dev.pogodemon.utils;

import dev.pogodemon.Game;
import dev.pogodemon.display.Camera;
import dev.pogodemon.input.KeyManager;
import dev.pogodemon.world.World;

public class Handler
{
    private Game game;
    private World world;

    public Handler(Game game)
    {
        this.game = game;
    }

    public int getWidth()
    {
        return game.getWidth();
    }

    public int getHeight()
    {
        return game.getHeight();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public Camera getCamera()
    {
        return game.getCamera();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
