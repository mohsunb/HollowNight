package dev.pogodemon.entities.particles;

import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.Utils;

import java.awt.*;

public class ParticleEnemyHit extends StaticEntity
{
    private final Animation anim = new Animation(Math.round(50F/1.5F), Assets.enemy_hit);
    private final boolean facing_right;

    public ParticleEnemyHit(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);
        facing_right = handler.getWorld().getEntityManager().getPlayer().isFacingRight();
    }

    @Override
    public void update()
    {
        if (anim.lastFrame())
            handler.getWorld().removeEntity(this);

        anim.update();
    }

    @Override
    public int renderRank()
    {
        return 5;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (facing_right)
            gfx.drawImage(anim.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 529), (int) (y - handler.getCamera().getyOffset() - 278), null);
        else
            gfx.drawImage(Utils.mirrorImage(anim.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 533), (int) (y - handler.getCamera().getyOffset() - 278), null);
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
