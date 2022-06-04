package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.items.Item;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.Random;

public class InteractableItem extends Creature
{
    private boolean bouncy;
    private float glow_counter = 0;
    private float range;
    private boolean illegal_interaction = false;
    private boolean picked_up = false;
    private int picked_up_counter = 0;
    private Item item;
    private boolean show_tooltip = false;

    public InteractableItem(Handler handler, float x, float y, boolean bouncy, Item item)
    {
        super(handler, x, y, 0, 0);
        this.bouncy = bouncy;
        this.item = item;
        CREATURE_TYPE = 1;
        range = bouncy ? handler.getWorld().getEntityManager().getPlayer().bounds.width : handler.getWorld().getEntityManager().getPlayer().bounds.height;

        Random rand = new Random();
        speedX = DEFAULT_SPEED * 0.5F;
        xMove = (float) Math.round(Math.pow(-1, rand.nextInt(0, 2)) * speedX);
        speedY = -3 * DEFAULT_SPEED;
    }

    @Override
    public int renderRank()
    {
        return 2;
    }

    @Override
    public void update()
    {
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (bouncy)
        {
            move();
            if (xMove != 0 && grounded)
                xMove = 0;

            if (grounded)
            {
                if (!illegal_interaction && (handler.getKeyManager().up || handler.getKeyManager().down) && !(player.getCenterX() > getX() - range && player.getCenterX() < getX() + range && player.grounded && player.getCenterY() < getY() && player.getCenterY() > getY() - 200))
                    illegal_interaction = true;

                if (illegal_interaction && !handler.getKeyManager().up && !handler.getKeyManager().down && player.getCenterX() > getX() - range && player.getCenterX() < getX() + range && player.grounded && player.getCenterY() < getY() && player.getCenterY() > getY() - 200)
                    illegal_interaction = false;

                if (!illegal_interaction && !player.item_pickup && !player.fall_shocked && !player.damage_shocked && !player.dashing && !player.slashing && !player.superdash && (handler.getKeyManager().up || handler.getKeyManager().down) && player.getCenterX() > getX() - range && player.getCenterX() < getX() + range && player.grounded && player.getCenterY() < getY() && player.getCenterY() > getY() - 200)
                {
                    if (player.isFacingRight() && player.getCenterX() > getX())
                        player.facing_right = false;
                    if (!player.isFacingRight() && player.getCenterX() < getX())
                        player.facing_right = true;
                    player.pickupItem(bouncy);
                    picked_up = true;
                    illegal_interaction = true;
                }

                show_tooltip = !picked_up && player.getCenterX() > getX() - range && player.getCenterX() < getX() + range && player.grounded && player.getCenterY() < getY() && player.getCenterY() > getY() - 200;
            }
        }

        else
        {
            if (!illegal_interaction && (handler.getKeyManager().up || handler.getKeyManager().down) && !(player.getCenterX() > getX() - range && player.getCenterX() < getX() + range && player.grounded && player.getCenterY() < getY() + range && player.getCenterY() > getY() - range))
                illegal_interaction = true;

            if (illegal_interaction && !handler.getKeyManager().up && !handler.getKeyManager().down && player.getCenterX() > getX() - range && player.getCenterX() < getX() + range && player.grounded && player.getCenterY() < getY() + range && player.getCenterY() > getY() - range)
                illegal_interaction = false;

            if (!illegal_interaction && !player.item_pickup && !player.fall_shocked && !player.damage_shocked && !player.dashing && !player.slashing && !player.superdash && (handler.getKeyManager().up || handler.getKeyManager().down) && player.getCenterX() > getX() - range && player.getCenterX() < getX() + range && player.grounded && player.getCenterY() < getY() + range && player.getCenterY() > getY() - range)
            {
                if (player.isFacingRight() && player.getCenterX() > getX())
                    player.facing_right = false;
                if (!player.isFacingRight() && player.getCenterX() < getX())
                    player.facing_right = true;
                player.pickupItem(bouncy);
                picked_up = true;
                illegal_interaction = true;
            }

            show_tooltip = !picked_up && player.getCenterX() > getX() - range && player.getCenterX() < getX() + range && player.grounded && player.getCenterY() < getY() + range && player.getCenterY() > getY() - range;
        }

        if (picked_up)
        {
            picked_up_counter++;
            if (picked_up_counter >= Launcher.framerate_limit * 0.5)
            {
                handler.getWorld().getEntityManager().getPlayer().giveItem(item);
                handler.getWorld().getEntityManager().getPlayer().displayItem(item);
                handler.getWorld().removeEntity(this);
            }
        }

        if (++glow_counter >= Launcher.framerate_limit)
            glow_counter = 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.green);
            if (bouncy && grounded)
                gfx.drawRect((int) (getX() - handler.getCamera().getxOffset() - range), (int) (getY() - handler.getCamera().getyOffset() - 200), (int) (2 * range), 200);
            else if (!bouncy)
                gfx.drawRect((int) (getX() - handler.getCamera().getxOffset() - range), (int) (getY() - handler.getCamera().getyOffset() - range), (int) (2 * range), (int) (2 * range));
        }

        if (bouncy)
        {
            if (show_tooltip)
            {
                gfx.drawImage(Assets.interact, (int) (getX() - handler.getCamera().getxOffset() - Assets.interact.getWidth() * 0.5), (int) (getY() - handler.getCamera().getyOffset() - 200 - handler.getWorld().getEntityManager().getPlayer().bounds.height), null);
                gfx.setColor(Color.white);
                gfx.setFont(Assets.trajan);
                gfx.drawString("INSPECT", getX() - handler.getCamera().getxOffset() - 100 - 10, getY() - handler.getCamera().getyOffset() - 227.5F);
                gfx.setFont(new Font("Arial", Font.PLAIN, 12));
            }
        }

        else
        {
            if (show_tooltip)
            {
                gfx.drawImage(Assets.interact, (int) (getX() - handler.getCamera().getxOffset() - Assets.interact.getWidth() * 0.5), (int) (getY() - handler.getCamera().getyOffset() - 100 - handler.getWorld().getEntityManager().getPlayer().bounds.height), null);
                gfx.setColor(Color.white);
                gfx.setFont(Assets.trajan);
                gfx.drawString("INSPECT", getX() - handler.getCamera().getxOffset() - 100 - 10, getY() - handler.getCamera().getyOffset() - 127.5F);
                gfx.setFont(new Font("Arial", Font.PLAIN, 12));
            }
        }

        gfx.setColor(Color.white);
        gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - glow_counter / Launcher.framerate_limit));
        gfx.fillOval((int) (getX() - handler.getCamera().getxOffset() - 10 - 20 * glow_counter / Launcher.framerate_limit), (int) (getY() - handler.getCamera().getyOffset() - 10 - 20 * glow_counter / Launcher.framerate_limit), (int) (20 + 2 * 20 * glow_counter / Launcher.framerate_limit), (int) (20 + 2 * 20 * glow_counter / Launcher.framerate_limit));
        gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
        gfx.fillOval((int) (getX() - handler.getCamera().getxOffset() - 10), (int) (getY() - handler.getCamera().getyOffset() - 10), 20, 20);
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
