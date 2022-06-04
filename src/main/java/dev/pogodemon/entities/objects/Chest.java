package dev.pogodemon.entities.objects;

import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.entities.particles.ParticleSpikeHit;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public abstract class Chest extends StaticEntity
{
    private boolean open = false;
    private boolean opened = false;

    private final String entry;

    public Chest(Handler handler, float x, float y, String entry)
    {
        super(handler, x, y, 0, 0);
        setSolid(true);
        bounds.width = 150;
        bounds.height = 105;
        has_knockback = true;
        is_pogoable = true;
        this.entry = entry;
        if (GameFlags.data.getBoolean(entry))
        {
            setSolid(false);
            handler.getWorld().spawnEntity(new ChestExtra(handler, getX(), getY()));
            handler.getWorld().spawnEntity(new SolidArea(handler, getX(), getY() + 20, 20, bounds.height - 20));
            handler.getWorld().spawnEntity(new SolidArea(handler, getX() + bounds.width - 20, getY() + 20, 20, bounds.height - 20));
            open = true;
            opened = true;
            has_knockback = false;
            is_pogoable = false;
        }
    }

    protected abstract void open();

    @Override
    public void update()
    {
        if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
            was_just_attacked = false;

        if (open && !opened)
        {
            open();
            setSolid(false);
            handler.getWorld().spawnEntity(new ChestExtra(handler, getX(), getY()));
            handler.getWorld().spawnEntity(new SolidArea(handler, getX(), getY() + 20, 20, bounds.height - 20));
            handler.getWorld().spawnEntity(new SolidArea(handler, getX() + bounds.width - 20, getY() + 20, 20, bounds.height - 20));
            opened = true;
            has_knockback = false;
            is_pogoable = false;
        }
    }

    @Override
    public int renderRank()
    {
        return 4;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (open)
            gfx.drawImage(Assets.chest_open_front, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() - 73), null);

        else
            gfx.drawImage(Assets.chest, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() - 20), null);

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
            was_just_attacked = true;
            Player p = handler.getWorld().getEntityManager().getPlayer();
            float yy = (p.up_slashing || p.down_slashing) ? getCenterY() : p.getCenterY();
            if (!open)
                handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), yy));
            else
                handler.getWorld().spawnEntity(new ParticleSpikeHit(handler, getCenterX(), yy));
            if (!open)
            {
                open = true;
                GameFlags.data.updateValue(entry, true);
                GameFlags.data.updateLocalFile();
            }
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
