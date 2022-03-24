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
        has_knockback = false;
    }

    @Override
    public void update()
    {

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
            gfx.setColor(Color.blue);
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
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (player.getRespawnX() != getRespawnX() || player.getRespawnY() != getRespawnY())
            player.updateRespawnPoint(getRespawnX(), getRespawnY());
    }
}
