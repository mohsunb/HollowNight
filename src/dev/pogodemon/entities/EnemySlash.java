package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class EnemySlash extends Entity
{
    private final float life_limit;
    private int life = 0;
    private final float xMove;

    public EnemySlash(Handler handler, float x, float y, float width, float height, float life, float xMove)
    {
        super(handler, x, y, width, height);
        this.life_limit = life;
        this.xMove = xMove;
        is_pogoable = true;
        has_knockback = false;
    }

    @Override
    public void update()
    {
        if (life++ >= life_limit)
            handler.getWorld().removeEntity(this);

        if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().isSlashing())
            was_just_attacked = true;

        x += xMove;
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
        if (!was_just_attacked)
        {
            was_just_attacked = true;
            handler.getWorld().getEntityManager().getPlayer().triggerParryFreeze();
            handler.getWorld().getEntityManager().getPlayer().setParryFreezeLength(Launcher.framerate_limit / 3F);
        }
    }

    @Override
    public void fireballHit()
    {

    }

    @Override
    public void playerContact()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (!player.dead && !player.invulnerable && !player.shadow_dashing)
        {
            player.dealDamageGeneric();

            if (xMove < 0)
                player.damage_shocked_right = false;

            else
                player.damage_shocked_right = true;
        }
    }
}
