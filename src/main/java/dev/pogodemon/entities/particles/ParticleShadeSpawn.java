package dev.pogodemon.entities.particles;

import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class ParticleShadeSpawn extends StaticEntity
{
    private final boolean facing_right;
    private final Animation anim_right = new Animation(50, Assets.shade_spawn_right);
    private final Animation anim_left = new Animation(50, Assets.shade_spawn_left);

    public ParticleShadeSpawn(Handler handler, boolean facing_right)
    {
        super(handler, handler.getWorld().getEntityManager().getPlayer().getX(),
                handler.getWorld().getEntityManager().getPlayer().getY(), 0, 0);
        this.facing_right = facing_right;
    }

    @Override
    public void update()
    {
        anim_left.update();
        anim_right.update();
    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (facing_right)
            gfx.drawImage(anim_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset() - 40), null);
        else
            gfx.drawImage(anim_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 60), (int) (y - handler.getCamera().getyOffset() - 40), null);
    }

    @Override
    public void hasBeenHit() {

    }

    @Override
    public void fireballHit() {

    }

    @Override
    public void playerContact() {

    }
}
