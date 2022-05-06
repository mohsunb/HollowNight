package dev.pogodemon.entities.particles;

import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.Utils;

import java.awt.*;

public class ParticleSpikeHit extends StaticEntity
{
    private final Animation anim = new Animation(50, Assets.spike_hit);

    /*
    0 >> up
    1 >> right
    2 >> down
    3 >> left
     */
    private final int direction;

    public ParticleSpikeHit(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);
        Player p = handler.getWorld().getEntityManager().getPlayer();
        if (p.up_slashing)
            direction = 2;
        else if (p.down_slashing)
            direction = 0;
        else if (p.isFacingRight())
            direction = 3;
        else
            direction = 1;
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
        return 3;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (direction == 0)
            gfx.drawImage(anim.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 117), (int) (y - handler.getCamera().getyOffset() - 167 - 40), null);
        else if (direction == 1)
            gfx.drawImage(Utils.rotateImageByDegrees(anim.getCurrentFrame(), 90), (int) (x - handler.getCamera().getxOffset() + 40), (int) (y - handler.getCamera().getyOffset() - 117), null);
        else if (direction == 2)
            gfx.drawImage(Utils.rotateImageByDegrees(anim.getCurrentFrame(), 180), (int) (x - handler.getCamera().getxOffset() - 117), (int) (y - handler.getCamera().getyOffset() + 40), null);
        else
            gfx.drawImage(Utils.rotateImageByDegrees(anim.getCurrentFrame(), 270), (int) (x - handler.getCamera().getxOffset() - 167 - 40), (int) (y - handler.getCamera().getyOffset() - 117), null);
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