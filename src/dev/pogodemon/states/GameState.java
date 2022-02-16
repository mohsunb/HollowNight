package dev.pogodemon.states;

import dev.pogodemon.entities.Entity;
import dev.pogodemon.entities.creatures.Husk;
import dev.pogodemon.entities.creatures.Player;
import dev.pogodemon.entities.creatures.PlayerSlash;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.World;

import java.awt.*;

public class GameState extends State
{
    private World kings_pass;

    public GameState(Handler handler)
    {
        super(handler);
        kings_pass = new World(handler,"kings_pass.png", 3400, 4480);
        handler.setWorld(kings_pass);

        kings_pass.addEntity(new Husk(handler, 3300, 4480));
        kings_pass.addEntity(new PlayerSlash(handler, handler.getWorld().getEntityManager().getPlayer().getX(), handler.getWorld().getEntityManager().getPlayer().getY()));
    }

    @Override
    public void update()
    {
        handler.getWorld().update();
    }

    @Override
    public void render(Graphics gfx)
    {
        handler.getWorld().render(gfx);
    }
}
