package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.Utils;

import java.awt.*;
import java.util.Random;

public class DeathPropNail extends Creature
{
    private float angle = 0;
    private final int i;

    public DeathPropNail(Handler handler)
    {
        super(handler, handler.getWorld().getEntityManager().getPlayer().getCenterX(),
                handler.getWorld().getEntityManager().getPlayer().getCenterY(),
                Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        Random rand = new Random();
        speedX = DEFAULT_SPEED;
        xMove = (float) Math.floor(Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(0, 101) * 0.01 * speedX);
        speedY = -rand.nextInt(1, 11) * DEFAULT_SPEED / 4.0F;
        bounds.x = 28;
        bounds.y = 28;
        bounds.width = 17;
        bounds.height = 17;
        i = (int) Math.pow(-1, rand.nextInt(0, 2));
    }

    @Override
    public void update()
    {
        if (!grounded)
            angle += 360F * i / Launcher.framerate_limit / 2F;

        if (xMove != 0 && grounded)
            xMove = 0;
        move();
    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.drawImage(Utils.rotateImageByDegrees(Assets.prop_nail, angle), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
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
