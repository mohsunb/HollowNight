package dev.pogodemon.states;

import dev.pogodemon.entities.Grass;
import dev.pogodemon.entities.creatures.Husk;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;
import dev.pogodemon.world.World;

import java.awt.*;

public class GameState extends State
{
    private World world;

    public GameState(Handler handler)
    {
        super(handler);
        world = new World(handler,"map_test_100x100.png", 100, 100, 1100, 400); // Image has to be grayscale
        handler.setWorld(world);
    }

    @Override
    public void update()
    {
        world.update();
    }

    @Override
    public void render(Graphics gfx)
    {
        world.render(gfx);
    }
}
