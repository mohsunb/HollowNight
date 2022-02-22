package dev.pogodemon.entities.creatures;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Player;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class Crawlid extends Creature
{
    private float xRangeMin, xRangeMax;
    public Crawlid(Handler handler, float x, float y, float xRangeMin, float xRangeMax)
    {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        CREATURE_TYPE = 1;
        health = 10;
        this.xRangeMin = xRangeMin;
        this.xRangeMax = xRangeMax;

        
        is_pogoable = true;
    }

    @Override
    public void update()
    {
        if (exists)
        {
            move();

            if (getX() + bounds.width * 0.5 >= xRangeMax - bounds.width * 0.5)
                facing_right = false;
            else if (getX() + bounds.width * 0.5 <= xRangeMin + bounds.width * 0.5)
                facing_right = true;

            speedX = DEFAULT_SPEED * 0.4f;
            if (facing_right)
                xMove = speedX;
            else
                xMove = -speedX;

            if (health <= 0)
            {
                exists = false;
                facing_right = !handler.getWorld().getEntityManager().getPlayer().isFacingRight();
            }
        }
    }

    @Override
    public void render(Graphics gfx)
    {
        if (exists)
        {
            if (facing_right)
                gfx.drawImage(Assets.crawlid_right, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 5), null);
            else
                gfx.drawImage(Assets.crawlid_left, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 5), null);
        }

        else
        {
            if (facing_right)
                gfx.drawImage(Assets.crawlid_dead_right, (int) (x - handler.getCamera().getxOffset() - 150), (int) (y - handler.getCamera().getyOffset() + 40), null);
            else
                gfx.drawImage(Assets.crawlid_dead_left, (int) (x - handler.getCamera().getxOffset() + 100), (int) (y - handler.getCamera().getyOffset() + 40), null);
        }

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        health -= player.nail_damage;
    }

    @Override
    public void playerContact()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (!player.invulnerable && !player.shadow_dashing)
        {
            player.health -= 20;
            player.invulnerable = true;
            player.damage_shocked = true;
            if ((player.getX() + bounds.width * 0.5) <= (getX() + bounds.width * 0.5))
                player.damage_shocked_right = false;

            else
                player.damage_shocked_right = true;
        }
    }
}
