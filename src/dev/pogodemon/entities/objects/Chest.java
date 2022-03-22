package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public abstract class Chest extends StaticEntity
{
    private boolean open = false;
    private boolean opened = false;

    public Chest(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_WIDTH);
        setSolid(true);

        bounds.width = 150;
        bounds.height = 105;
        has_knockback = true;
        is_pogoable = true;
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
            opened = true;
            has_knockback = false;
            is_pogoable = false;
        }
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (open)
            gfx.drawImage(Assets.chest_open, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() - 73), null);

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
            open = true;
        }
    }

    @Override
    public void playerContact()
    {

    }
}
