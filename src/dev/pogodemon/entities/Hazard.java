package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class Hazard extends StaticEntity
{
    private Player player = handler.getWorld().getEntityManager().getPlayer();
    private boolean just_respawned = false;
    public Hazard(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height);
        has_knockback = false;
    }

    @Override
    public void update()
    {
        if (just_respawned && player.flatSurface())
            just_respawned = false;

        for (Entity e : getCollidingEntities(0, 0))
            e.kill();
    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.red);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {

    }

    @Override
    public void fireballHit() {

    }

    @Override
    public void playerContact()
    {
        if (player.lifeblood > 0 || player.health > 20)
        {
            player.hazardRespawn();
            just_respawned = true;
        }

        else if (!just_respawned)
            player.die();
    }
}
