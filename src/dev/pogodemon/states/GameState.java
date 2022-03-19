package dev.pogodemon.states;

import dev.pogodemon.utils.Handler;

import java.awt.*;

public class GameState extends State
{
    public GameState(Handler handler)
    {
        super(handler);
        handler.setWorld(handler.loadWorld().KingsPass());
    }

    @Override
    public void update()
    {
        handler.getWorld().update();
    }

    @Override
    public void render(Graphics2D gfx)
    {
        handler.getWorld().render(gfx);
    }
}
