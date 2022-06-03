package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.Utils;

import java.awt.*;
import java.util.Random;

public class DeathPropShell extends Creature
{
    private float angle = 0;
    private final int i;

    public DeathPropShell(Handler handler)
    {
        super(handler, handler.getWorld().getEntityManager().getPlayer().getCenterX() - 40,
                handler.getWorld().getEntityManager().getPlayer().getCenterY() - 80,
                Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        Random rand = new Random();
        speedX = DEFAULT_SPEED;
        xMove = (float) Math.floor(Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(0, 11) * 0.01 * speedX);
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
        return 2;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.drawImage(Utils.rotateImageByDegrees(Assets.prop_shell, angle), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

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
