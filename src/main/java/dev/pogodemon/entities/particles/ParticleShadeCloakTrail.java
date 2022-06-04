package dev.pogodemon.entities.particles;

import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class ParticleShadeCloakTrail extends Entity
{
    private final boolean facing_right;

    private final Animation anim_right = new Animation(25, Assets.shade_cloak_trail_right);
    private final Animation anim_left = new Animation(25, Assets.shade_cloak_trail_left);

    public ParticleShadeCloakTrail(Handler handler, float x, float y, boolean facing_right)
    {
        super(handler, x, y, 0, 0);
        this.facing_right = facing_right;
    }

    @Override
    public void update()
    {
        if (facing_right)
        {
            anim_right.update();
            if (anim_right.getIndex() == 19)
                handler.getWorld().removeEntity(this);
        }

        else
        {
            anim_left.update();
            if (anim_left.getIndex() == 19)
                handler.getWorld().removeEntity(this);
        }
    }

    @Override
    public int renderRank()
    {
        return 3;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (facing_right)
            gfx.drawImage(anim_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 600), (int) (y - handler.getCamera().getyOffset() - 150), null);
        else
            gfx.drawImage(anim_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 80), (int) (y - handler.getCamera().getyOffset() - 150), null);
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
