package dev.pogodemon.entities.creatures;

import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.Entity;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class PlayerSlash extends Creature
{
    private Player player;

    public PlayerSlash(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        player = handler.getWorld().getEntityManager().getPlayer();
        CREATURE_TYPE = -1;
        is_harmful = false;
    }

    @Override
    public void update()
    {
        if (player.isFacingRight())
        {
            if (!player.down_slashing && !player.up_slashing)
            {
                bounds.x = 51;
                bounds.y = 0;
                bounds.width = 150;
                bounds.height = 127;
            }

            else if (player.up_slashing)
            {
                bounds.x = -55;
                bounds.y = -95;
                bounds.width = 180;
                bounds.height = 220;
            }

            else
            {
                bounds.x = -55;
                bounds.y = 0;
                bounds.width = 170;
                bounds.height = 230;
            }
        }

        else
        {
            if (!player.down_slashing && !player.up_slashing)
            {
                bounds.x = -147;
                bounds.y = 0;
                bounds.width = 150;
                bounds.height = 127;
            }

            else if (player.up_slashing)
            {
                bounds.x = -75;
                bounds.y = -95;
                bounds.width = 180;
                bounds.height = 220;
            }

            else
            {
                bounds.x = -55;
                bounds.y = 0;
                bounds.width = 170;
                bounds.height = 230;
            }
        }

        exists = player.isSlashing();

        if (exists)
        {
            x = player.getX();
            y = player.getY();
        }

        else if (x != 0 && y != 0)
        {
            x = 0;
            y = 0;
        }

        move();
    }

    @Override
    public void render(Graphics gfx)
    {
        if (exists)
        {
            if (player.isFacingRight())
            {
                if (!player.down_slashing && !player.up_slashing)
                {
                    if (player.alternative_slash_sprite)
                        gfx.drawImage(Assets.slash1_right, (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset()), null);
                    else
                        gfx.drawImage(Assets.slash2_right, (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset() - 15), null);
                }

                else if (player.up_slashing)
                {
                    gfx.drawImage(Assets.upslash_right, (int) (x - handler.getCamera().getxOffset() - 50), (int) (y - handler.getCamera().getyOffset() - 100), null);
                }

                else
                {
                    gfx.drawImage(Assets.downslash_right, (int) (x - handler.getCamera().getxOffset() - 60), (int) (y - handler.getCamera().getyOffset() - 20), null);
                }
            }

            else
            {
                if (!player.down_slashing && !player.up_slashing)
                {
                    if (player.alternative_slash_sprite)
                        gfx.drawImage(Assets.slash1_left, (int) (x - handler.getCamera().getxOffset() - 157), (int) (y - handler.getCamera().getyOffset()), null);
                    else
                        gfx.drawImage(Assets.slash2_left, (int) (x - handler.getCamera().getxOffset() - 140), (int) (y - handler.getCamera().getyOffset() - 15), null);
                }

                else if (player.up_slashing)
                {
                    gfx.drawImage(Assets.upslash_left, (int) (x - handler.getCamera().getxOffset() - 75), (int) (y - handler.getCamera().getyOffset() - 100), null);
                }

                else
                {
                    gfx.drawImage(Assets.downslash_left, (int) (x - handler.getCamera().getxOffset() - 62), (int) (y - handler.getCamera().getyOffset() - 20), null);
                }
            }

            //render slash hitboxes
            //gfx.setColor(Color.blue);
            //gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }
}
