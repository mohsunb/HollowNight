package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class Bench extends StaticEntity
{
    private final float range;
    private boolean illegal_interaction = true;
    private boolean show_tooltip = false;

    private boolean occupied = false;
    private boolean can_get_off = false;
    private boolean can_get_on = true;
    private int timer1 = 0;
    private int timer2 = 0;

    public Bench(Handler handler, float x, float y)
    {
        super(handler, x, y, Assets.bench_generic.getWidth(), Assets.bench_generic.getHeight());
        range = bounds.width * 0.5F;
        has_knockback = false;
    }

    @Override
    public void update()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();

        if (!occupied && player.benched)
        {
            occupied = true;
            can_get_off = false;
        }


        if (occupied && !can_get_off)
        {
            if (timer1++ >= Launcher.framerate_limit * 0.5)
            {
                timer1 = 0;
                can_get_off = true;
            }
        }

        if (!can_get_on)
        {
            if (timer2++ >= Launcher.framerate_limit)
            {
                timer2 = 0;
                can_get_on = true;
            }
        }

        if (!illegal_interaction && (handler.getKeyManager().up || handler.getKeyManager().down) && !(player.getCenterX() > getCenterX() - range && player.getCenterX() < getCenterX() + range && /*player.flatSurface() && */player.getCenterY() < getCenterY() + range && player.getCenterY() > getCenterY() - range))
            illegal_interaction = true;

        if (illegal_interaction && !handler.getKeyManager().up && !handler.getKeyManager().down && player.getCenterX() > getCenterX() - range && player.getCenterX() < getCenterX() + range && /*player.flatSurface() && */player.getCenterY() < getCenterY() + range && player.getCenterY() > getCenterY() - range)
            illegal_interaction = false;

        if (!illegal_interaction && !player.fall_shocked && !player.damage_shocked && !player.dashing && !player.slashing && !player.superdash && (handler.getKeyManager().up || handler.getKeyManager().down) && player.getCenterX() > getCenterX() - range && player.getCenterX() < getCenterX() + range && player.getCenterY() < getCenterY() + range && player.getCenterY() > getCenterY() - range)
        {
            if (can_get_on && !player.benched && player.flatSurface())
            {
                player.bench(getCenterX() - 30, getCenterY() - 110);
                occupied = true;
            }

            else if (can_get_off)
            {
                player.bench_off();
                occupied = false;
                can_get_off = false;
                can_get_on = false;
            }

            illegal_interaction = true;
        }

        show_tooltip = !player.benched && player.getCenterX() > getCenterX() - range && player.getCenterX() < getCenterX() + range && /*player.flatSurface() && */player.getCenterY() < getCenterY() + range && player.getCenterY() > getCenterY() - range;
    }

    @Override
    public int renderRank()
    {
        return -2;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.drawImage(Assets.bench_generic, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        if (show_tooltip)
        {
            gfx.drawImage(Assets.interact, (int) (getCenterX() - handler.getCamera().getxOffset() - Assets.interact.getWidth() * 0.5), (int) (getCenterY() - handler.getCamera().getyOffset() - 200 - handler.getWorld().getEntityManager().getPlayer().bounds.height), null);
            gfx.setColor(Color.white);
            gfx.setFont(Assets.trajan);
            gfx.drawString("REST", getCenterX() - handler.getCamera().getxOffset() - 100 + 38, getCenterY() - handler.getCamera().getyOffset() - 227.5F);
            gfx.setFont(new Font("Arial", Font.PLAIN, 12));
        }

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.green);
            gfx.drawRect((int) (getCenterX() - handler.getCamera().getxOffset() - range), (int) (getCenterY() - handler.getCamera().getyOffset() - range), (int) (2 * range), (int) (2 * range));
        }
    }

    @Override
    public void hasBeenHit()
    {

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
