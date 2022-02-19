package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class HazardRespawnPoint extends StaticEntity
{
    public HazardRespawnPoint(Handler handler, float x, float y, float width, float height, float respawnX, float respawnY)
    {
        super(handler, x, y, width, height);
        this.respawnX = respawnX;
        this.respawnY = respawnY;
        is_hazard_respawn = true;
        has_knockback = false;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics gfx)
    {
        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit() {

    }
}
