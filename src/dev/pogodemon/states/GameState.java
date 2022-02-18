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
        kings_pass = new World(handler,"kings_pass.png", 3200, 4751);
        handler.setWorld(kings_pass);

        kings_pass.addEntity(new Husk(handler, 3300, 4680));

        kings_pass.addEntity(new SpikesLarge(handler, 960, 3300, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 640, 2250, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 700, 2250, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 1000, 1800, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 1139, 1800, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 1278, 1800, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 700, 950, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 620, 950, 0));

        kings_pass.addEntity(new SpikesMedium(handler, 12560, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12640, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12720, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12800, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12880, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 12960, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13040, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13120, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13200, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13280, 3220, 0));

        kings_pass.addEntity(new SpikesMedium(handler, 13520, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13600, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13680, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13760, 3220, 0));
        kings_pass.addEntity(new SpikesMedium(handler, 13840, 3220, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 10520, 3140, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 10650, 3140, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 10780, 3140, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 10910, 3140, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 11040, 3140, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 11170, 3140, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 11300, 3140, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 11430, 3140, 0));

        kings_pass.addEntity(new SpikesLarge(handler, 4640, 3840, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 4770, 3840, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 4900, 3840, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 5030, 3840, 0));
        kings_pass.addEntity(new SpikesLarge(handler, 5080, 3840, 0));

        kings_pass.addEntity(new HazardRespawnPoint(handler, 800, 4480, 500, 400, 1000, 4751));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 3000, 4480, 500, 400, 3200, 4751));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 5420, 3560, 500, 240, 5517, 3671));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 9760, 2080, 320, 600, 9886, 2551));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 11600, 2880, 900, 320, 12069, 3071));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 13360, 2880, 160, 320, 13420, 3071));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 13920, 2880, 320, 320, 14063, 3071));
        kings_pass.addEntity(new HazardRespawnPoint(handler, 11720, 1880, 440, 320, 12025, 2071));

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
