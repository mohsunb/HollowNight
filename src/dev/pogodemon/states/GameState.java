package dev.pogodemon.states;

import dev.pogodemon.entities.HazardRespawnPoint;
import dev.pogodemon.entities.creatures.Husk;
import dev.pogodemon.entities.creatures.PlayerSlash;
import dev.pogodemon.entities.objects.SpikesLarge;
import dev.pogodemon.entities.objects.SpikesMedium;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.World;

import java.awt.*;

public class GameState extends State
{
    private World kings_pass;

    public GameState(Handler handler)
    {
        super(handler);
        kings_pass = new World(handler,"kings_pass.png", 3200, 4551);
        handler.setWorld(kings_pass);

        kings_pass.addEntity(new Husk(handler, 3300, 4480));

        kings_pass.addEntity(new SpikesLarge(handler, 960, 3100, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 640, 2050, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 700, 2050, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 1000, 1600, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 1139, 1600, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 1278, 1600, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 700, 750, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 620, 750, 0));

        kings_pass.addEntity(new SpikesMedium(handler, 12560, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12640, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12720, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12800, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12880, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12960, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13040, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13120, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13200, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13280, 3020, 0));

        kings_pass.addEntity(new SpikesMedium(handler, 13520, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13600, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13680, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13760, 3020, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13840, 3020, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 10520, 2940, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 10650, 2940, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 10780, 2940, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 10910, 2940, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 11040, 2940, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 11170, 2940, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 11300, 2940, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 11430, 2940, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 4640, 3640, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 4770, 3640, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 4900, 3640, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 5030, 3640, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 5080, 3640, 0));

        kings_pass.addEntity(new HazardRespawnPoint(handler, 800, 4280, 500, 400, 1000, 4551));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 3000, 4280, 500, 400, 3200, 4551));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 5420, 3360, 500, 240, 5517, 3471));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 9760, 1880, 320, 600, 9886, 2351));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 11600, 2680, 900, 320, 12069, 2871));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 13360, 2680, 160, 320, 13420, 2871));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 13920, 2680, 320, 320, 14063, 2871));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 11720, 1680, 440, 320, 12025, 1871));

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
