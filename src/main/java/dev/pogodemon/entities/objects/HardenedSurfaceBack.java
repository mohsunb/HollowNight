package dev.pogodemon.entities.objects;

import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class HardenedSurfaceBack extends StaticEntity
{
    private final HardenedSurfaceFront front;
    private final String save_file_entry;

    public HardenedSurfaceBack(Handler handler, float x, float y, HardenedSurfaceFront front, String save_file_entry)
    {
        super(handler, x, y, 190, 260);
        this.front = front;
        handler.getWorld().spawnEntity(front);
        health = 3;
        has_knockback = false;
        this.save_file_entry = save_file_entry;
    }

    @Override
    public void update()
    {
        if (health <= 0)
        {
            handler.getWorld().removeEntity(this);
            GameFlags.data.updateValue(save_file_entry, true);
            GameFlags.data.updateLocalFile();
        }

        if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
            was_just_attacked = false;
    }

    @Override
    public int renderRank()
    {
        return -1;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.drawImage(Assets.hardened_surface_back, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {
        if (!was_just_attacked)
        {
            health--;
            front.health--;
            was_just_attacked = true;
        }
    }

    @Override
    public void fireballHit()
    {

    }

    @Override
    public void playerContact()
    {

    }
}
