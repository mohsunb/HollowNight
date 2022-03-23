package dev.pogodemon.states;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.display.Camera;
import dev.pogodemon.entities.CameraFocusPoint;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.Random;

public class GameState extends State
{
    public static boolean screen_shake = false;
    private int screen_shake_timer = 0;
    private boolean damage_shock = false;
    private int damage_shock_timer = 0;

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
        CameraFocusPoint cam = handler.getWorld().getEntityManager().getPlayerCamera();
        cam.update();
        handler.getCamera().centerOnEntity(cam);
        if (!damage_shock)
            handler.getWorld().update();

        Random rand = new Random();

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

        if (!screen_shake && handler.getWorld().getEntityManager().getPlayer().screenShakeTriggered())
            screen_shake = true;

        if (screen_shake)
        {
            if (screen_shake_timer % (Launcher.framerate_limit * 0.0625) == (Launcher.framerate_limit * 0.0625 - 1))
            {
                cam.setXOffset((float) (cam.getXOffset() + Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(1, 101) * 0.01 * 20));
                cam.setYOffset((float) (cam.getYOffset() + Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(1, 101) * 0.01 * 20));
                if (Math.abs(cam.getXOffset()) >= 20)
                    cam.setXOffset(0);
                if (Math.abs(cam.getYOffset()) >= 20)
                    cam.setYOffset(0);
            }

            screen_shake_timer++;
            if (screen_shake_timer >= Launcher.framerate_limit)
            {
                screen_shake_timer = 0;
                screen_shake = false;
            }
        }

        if (!damage_shock && handler.getWorld().getEntityManager().getPlayer().damageFreezeTriggered())
            damage_shock = true;

        if (damage_shock)
        {
            damage_shock_timer++;
            if (damage_shock_timer >= Launcher.framerate_limit / 3)
            {
                damage_shock_timer = 0;
                damage_shock = false;
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
