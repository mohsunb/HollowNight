package dev.pogodemon.states;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
<<<<<<< HEAD
import dev.pogodemon.display.Camera;
import dev.pogodemon.display.SpriteSheet;
=======
>>>>>>> 6aee207 (v0.3.6)
import dev.pogodemon.entities.CameraFocusPoint;
import dev.pogodemon.entities.Player;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.util.Random;

public class GameState extends State
{
<<<<<<< HEAD
=======
    // Soul fluid animations
    private final Animation anim_soul1 = new Animation(100, Assets.soul1);
    private final Animation anim_soul2 = new Animation(100, Assets.soul2);
    private final Animation anim_soul3 = new Animation(100, Assets.soul3);
    private final Animation anim_soul4 = new Animation(100, Assets.soul4);
    private final Animation anim_soul5 = new Animation(100, Assets.soul5);
    private final Animation anim_soul6 = new Animation(100, Assets.soul6);
    private final Animation anim_soul7 = new Animation(100, Assets.soul7);
    private final Animation anim_soul8 = new Animation(100, Assets.soul8);

    //Low hp effect
    private final int vignette_limit = Launcher.framerate_limit * 10;
    private int vignette_counter = 0;
    private boolean vignette_ended = false;
    private boolean flag1 = false;
    private float vignette_opacity = 0.0F;
    private int flag0 = 1;

    //Room transition logs
    private int rt_health;
    private float rt_soul;
    private int rt_lifeblood;
    private int rt_geo;
    private boolean rt_facing_right;
    private boolean rt_superdash;

>>>>>>> 6aee207 (v0.3.6)
    public static boolean screen_shake = false;
    private int screen_shake_timer = 0;
    private boolean damage_shock = false;
    private int damage_shock_timer = 0;

    private boolean hazard_respawn_transition = false;
    private int hazard_respawn_transition_counter = 0;

    private boolean heal_effect = false;
    private float heal_effect_timer = 0;

    /*
    0 >> fade to black
    1 >> black
    2 >> fade back to the game
     */
    private int hazard_respawn_transition_state = 0;

    public GameState(Handler handler)
    {
        super(handler);
<<<<<<< HEAD
        handler.setWorld(handler.loadWorld().KingsPass());
=======
        handler.setWorld(new World(handler, GameFlags.load_map_id, GameFlags.load_coordinates, GameFlags.load_state));
        MapHelper.getEntityData(handler, GameFlags.load_map_id).spawnEntities();
    }

    /*
    For room transitions
     */
    public void bufferPlayerState()
    {
        rt_health = handler.getWorld().getEntityManager().getPlayer().health;
        rt_lifeblood = handler.getWorld().getEntityManager().getPlayer().lifeblood;
        rt_geo = handler.getWorld().getEntityManager().getPlayer().getGeo();
        rt_soul = handler.getWorld().getEntityManager().getPlayer().soul;
        rt_facing_right = handler.getWorld().getEntityManager().getPlayer().isFacingRight();
        rt_superdash = handler.getWorld().getEntityManager().getPlayer().superdash;
    }

    /*
    For room transitions
     */
    public void updatePlayerState()
    {
        handler.getWorld().getEntityManager().getPlayer().health = rt_health;
        handler.getWorld().getEntityManager().getPlayer().lifeblood = rt_lifeblood;
        handler.getWorld().getEntityManager().getPlayer().setGeo(rt_geo);
        handler.getWorld().getEntityManager().getPlayer().soul = rt_soul;
        handler.getWorld().getEntityManager().getPlayer().setDirection(rt_facing_right);
        handler.getWorld().getEntityManager().getPlayer().superdash = rt_superdash;
        if (!rt_superdash)
            handler.getWorld().getEntityManager().getPlayer().setxMove(handler.getWorld().getEntityManager().getPlayer().getxSpeed() * (rt_facing_right ? 1 : -1));
>>>>>>> 6aee207 (v0.3.6)
    }

    @Override
    public void update()
    {
        CameraFocusPoint cam = handler.getWorld().getEntityManager().getPlayerCamera();
        Player player = handler.getWorld().getEntityManager().getPlayer();
        cam.update();
        handler.getCamera().centerOnEntity(cam);
        if (!damage_shock)
            handler.getWorld().update();

        Random rand = new Random();

        if (!hazard_respawn_transition && player.hazard_triggered)
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
            cam.setXOffset((float) (cam.getXOffset() + Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(1, 101) * 0.01 * player.getScreenShakeLevel()));
            cam.setYOffset((float) (cam.getYOffset() + Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(1, 101) * 0.01 * player.getScreenShakeLevel()));
            if (Math.abs(cam.getXOffset()) >= 20)
                cam.setXOffset(0);
            if (Math.abs(cam.getYOffset()) >= 20)
                cam.setYOffset(0);

            screen_shake_timer++;
            if (screen_shake_timer >= player.getScreenShakeLength())
            {
                screen_shake_timer = 0;
                screen_shake = false;
                player.screen_shake_triggered = false;
            }
        }

        if (!damage_shock && player.damageFreezeTriggered())
            damage_shock = true;

        if (damage_shock)
        {
            damage_shock_timer++;
            if (damage_shock_timer >= player.getDamageShockFreezeLength())
            {
                damage_shock_timer = 0;
                damage_shock = false;
                player.damage_shock_freeze_triggered = false;
            }
        }

        if (heal_effect && ++heal_effect_timer >= Launcher.framerate_limit)
        {
            heal_effect_timer = 0;
            heal_effect = false;
        }
    }

    @Override
    public void render(Graphics2D gfx)
    {
        handler.getWorld().render(gfx);
        Player player = handler.getWorld().getEntityManager().getPlayer();

<<<<<<< HEAD
=======
        // Death shock vfx >> above everything else
        if (player.damageFreezeTriggered() && player.dead)
            gfx.drawImage(Assets.death_shock, (int) (player.getX() - handler.getCamera().getxOffset() - 300), (int) (player.getY() - handler.getCamera().getyOffset() - 240), null);

        //Low HP vignette
        if (player.health <= 20 && !vignette_ended)
        {
            Utils.setOpacity(gfx, vignette_opacity);
            gfx.drawImage(Assets.vignette_low_hp, 0, 0, null);
            Utils.setOpacity(gfx, 1.0F);
        }

        // Healing vfx
>>>>>>> 6aee207 (v0.3.6)
        if (heal_effect)
        {
            float r = heal_effect_timer / Launcher.framerate_limit;
            float rr = 4 * r > 1.0F ? 1.0F : 4 * r;
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75F * (1.0F - (float) Math.pow(r, 2))));
            gfx.setColor(Color.white);
            gfx.fillRect(0, 0, Launcher.game_width, Launcher.game_height);
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - r));
            if (rr < 1.0F)
                gfx.fillOval((int) (player.getCenterX() - 100 - 100 * rr - handler.getCamera().getxOffset()), (int) (player.getCenterY() - 100 - 200 * rr - handler.getCamera().getyOffset()), (int) (200 + 2 * 100 * rr), (int) (200 + 200 * rr));
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
        }

        //Start -> Render HUD

        //Soul
        gfx.drawImage(Assets.soul_vessel_hud_up, 90, 80, null);
        if (Math.ceil(player.soul) >= 33)
            gfx.setColor(Color.white);
        else
            gfx.setColor(Color.lightGray);
        gfx.drawImage(Assets.soul_vessel_hud_down, 90, 80, null);
        gfx.fillOval(105, 110, 103, 103);
        if (player.soul < player.max_soul)
            gfx.drawImage(new SpriteSheet(Assets.soul_vessel_hud_down).crop(14, 30, 106, (int) (105 - Math.floor(105D / player.max_soul * player.soul))), 104, 110, null);
        if (player.soul >= 55)
            gfx.drawImage(Assets.soul_vessel_hud_middle, 115, 165, null);

        //Geo
        gfx.drawImage(Assets.geo_hud, 223, 165, null);
        gfx.setColor(Color.white);
        gfx.setFont(new Font("Arial", Font.PLAIN, 45));
        gfx.drawString(Integer.toString(player.getGeo()), 290, 220);
        if (player.has_buffered_geo)
            gfx.drawString((player.geo_buffer < 0 ? "-" : "+") + Math.abs(player.geo_buffer), 290, 270);

        //Masks
        for (int i = 0; i < player.max_health / 20; i++)
            gfx.drawImage(Assets.mask_empty, 60 * i + 235, 105, null);

        for (int i = 0; i < player.health / 20; i++)
            gfx.drawImage(Assets.mask_full, 60 * i + 235, 103, null);

        for (int i = 1; i < player.lifeblood / 20 + 1; i++)
            gfx.drawImage(Assets.lifeblood_mask,60 * i + 60 * player.max_health / 20 - 60 + 235, 102, null);

        //Acquired Items
        if (player.itemDisplayed)
        {
            if (player.itemDisplayState == 0)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, player.itemDisplayCounter / (Launcher.framerate_limit / 3F)));
            else if (player.itemDisplayState == 1)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            else if (player.itemDisplayState == 2)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - player.itemDisplayCounter / (Launcher.framerate_limit / 6F)));

            gfx.drawImage(player.latestItemDisplayed.icon(), 150 - (int) (player.latestItemDisplayed.icon().getWidth() * 0.5F), Launcher.game_height - 150 - (int) (player.latestItemDisplayed.icon().getHeight() * 0.5F), null);
            gfx.setFont(new Font("Arial", Font.PLAIN, 45));
            gfx.drawString(player.latestItemDisplayed.name(), 170 + (int) (player.latestItemDisplayed.icon().getWidth() * 0.5F), Launcher.game_height - 135);
            gfx.setFont(new Font("Arial", Font.PLAIN, 12));
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
        }

        //End -> Render HUD

        //Fade to black at hazard respawn
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

    public void triggerHealEffect()
    {
        if (!heal_effect)
            heal_effect = true;
        heal_effect_timer = 0;
    }
}
