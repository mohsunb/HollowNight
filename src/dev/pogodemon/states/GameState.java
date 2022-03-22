package dev.pogodemon.states;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.utils.Handler;

import java.awt.*;

public class GameState extends State
{
    private boolean hazard_respawn_transition = false;
    private int hazard_respawn_transition_counter = 0;

    /*
    0 >> fade to black
    1 >> black
    2 >> fade back to the game
     */
    private int hazard_respawn_transition_state = 0;

    public GameState(Handler handler)
    {
        super(handler);
        handler.setWorld(handler.loadWorld().KingsPass());
    }

    @Override
    public void update()
    {
        handler.getWorld().update();
        if (!hazard_respawn_transition && handler.getWorld().getEntityManager().getPlayer().hazard_triggered)
            hazard_respawn_transition = true;

        if (hazard_respawn_transition)
        {
            if (hazard_respawn_transition_state == 0)
            {
                hazard_respawn_transition_counter++;
                if (hazard_respawn_transition_counter >= Launcher.framerate_limit * 0.8)
                {
                    hazard_respawn_transition_counter = 0;
                    hazard_respawn_transition_state++;
                }
            }

            else if (hazard_respawn_transition_state == 1)
            {
                hazard_respawn_transition_counter++;
                if (hazard_respawn_transition_counter >= Launcher.framerate_limit * 0.2)
                {
                    hazard_respawn_transition_counter = 0;
                    hazard_respawn_transition_state++;
                }
            }

            else if (hazard_respawn_transition_state == 2)
            {
                hazard_respawn_transition_counter++;
                if (hazard_respawn_transition_counter >= Launcher.framerate_limit * 0.8)
                {
                    hazard_respawn_transition_counter = 0;
                    hazard_respawn_transition = false;
                    hazard_respawn_transition_state = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D gfx)
    {
        handler.getWorld().render(gfx);

        if (hazard_respawn_transition)
        {
            if (hazard_respawn_transition_state == 0)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ((float) hazard_respawn_transition_counter) / (Launcher.framerate_limit * 0.8F)));
            else if (hazard_respawn_transition_state == 1)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            else if (hazard_respawn_transition_state == 2)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - ((float) hazard_respawn_transition_counter) / (Launcher.framerate_limit * 0.8F)));
            gfx.setColor(Color.black);
            gfx.fillRect(0, 0, Launcher.game_width, Launcher.game_height);
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            gfx.setColor(Color.white);
        }
    }
}
