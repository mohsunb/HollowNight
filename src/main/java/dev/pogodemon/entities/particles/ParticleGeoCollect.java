package dev.pogodemon.entities.particles;

import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class ParticleGeoCollect extends StaticEntity
{
    private final Animation anim = new Animation(Math.round(50F/1.5F), Assets.geo_collect);

    public ParticleGeoCollect(Handler handler)
    {
        super(handler, 0, 0, 0, 0);
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
        gfx.drawImage(anim.getCurrentFrame(), (int) (handler.getWorld().getEntityManager().getPlayer().getCenterX() - handler.getCamera().getxOffset() - 50), (int) (handler.getWorld().getEntityManager().getPlayer().getCenterY() - handler.getCamera().getyOffset() - 170), null);
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
