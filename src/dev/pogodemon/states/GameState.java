package dev.pogodemon.states;

import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.World;

import java.awt.*;

public class GameState extends State
{
    private World kings_pass;

    public GameState(Handler handler)
    {
        super(handler);
        kings_pass = new World(handler,"kings_pass.png", 3000, 4480);
        handler.setWorld(kings_pass);
    }

    @Override
    public void update()
    {
        kings_pass.update();
    }

    @Override
    public void render(Graphics gfx)
    {
        kings_pass.render(gfx);
    }
}
