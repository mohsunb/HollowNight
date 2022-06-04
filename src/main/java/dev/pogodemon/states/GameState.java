package dev.pogodemon.states;

import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.CameraFocusPoint;
import dev.pogodemon.entities.Creature;
import dev.pogodemon.entities.Player;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.utils.Utils;
import dev.pogodemon.world.World;
import dev.pogodemon.world.entity_data.Dirtmouth;

import java.awt.*;
import java.util.Random;

public class GameState extends State
{
    //Kings Pass first exit effect
    private boolean kings_pass_exit = false;
    private int counter_kpe = 0;

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
    private boolean rt_downspell;
    private float rt_xMove;
    private float rt_yMove = 0;

    public static boolean screen_shake = false;
    private int screen_shake_timer = 0;
    private boolean damage_shock = false;
    private int damage_shock_timer = 0;
    private boolean parry_shock = false;
    private int parry_shock_timer = 0;

    private boolean heal_effect = false;
    private float heal_effect_timer = 0;
    
    /*
    0 >> fade to black
    1 >> black
    2 >> fade back to the game
     */
    private int hazard_respawn_transition_state = 0;
    private boolean hazard_respawn_transition = false;
    private int hazard_respawn_transition_counter = 0;

    /*
    0 >> fade to black
    1 >> black
    2 >> fade back to the game
     */
    private int room_transition_state = 0;
    private boolean room_transition = false;
    private int room_transition_counter;
    
    /*
    0 >> fade to black
    1 >> black
    2 >> fade back to the game
     */
    private int death_transition_state = 0;
    private boolean death_transition = false;
    private int death_transition_counter;

    private int fps = 0;

    public GameState(Handler handler)
    {
        super(handler);
        handler.setWorld(new World(handler, GameFlags.load_map_id, GameFlags.load_coordinates, GameFlags.load_state));
        MapHelper.getEntityData(handler, GameFlags.load_map_id).spawnEntities();
    }

    /*
    For room transitions
     */
    public void bufferPlayerState(int direction)
    {
        /*
        direction:
        -3 >> up (force left)
        -1 >> up (force right)
        0 >> up
        1 >> right
        2 >> down
        3 >> left
         */
        rt_health = handler.getWorld().getEntityManager().getPlayer().health;
        rt_lifeblood = handler.getWorld().getEntityManager().getPlayer().lifeblood;
        rt_geo = handler.getWorld().getEntityManager().getPlayer().getGeo();
        rt_soul = handler.getWorld().getEntityManager().getPlayer().soul;

        if (direction == 1 || direction == -1 || direction == 3 || direction == -3)
            rt_facing_right = direction == 1 || direction == -1;
        else
            rt_facing_right = handler.getWorld().getEntityManager().getPlayer().isFacingRight();

        rt_superdash = handler.getWorld().getEntityManager().getPlayer().superdash;
        if (direction == 1 || direction == -1 || direction == 3 || direction == -3)
        {
            if (direction == 1 || direction == -1)
                rt_xMove = handler.getWorld().getEntityManager().getPlayer().getxSpeed();
            else
                rt_xMove = -handler.getWorld().getEntityManager().getPlayer().getxSpeed();
        }

        else if (direction == 2)
            rt_xMove = 0;

        if (direction == 0 || direction == -1 || direction == -3)
        {
            rt_yMove = -Creature.DEFAULT_SPEED * 2.5F;
            rt_xMove = handler.getWorld().getEntityManager().getPlayer().getxSpeed() * 0.5F * (rt_facing_right ? 1 : -1);
        }

        else
            rt_yMove = 0;
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
            handler.getWorld().getEntityManager().getPlayer().setxMove(rt_xMove);

        if (rt_yMove != 0)
        {
            handler.getWorld().getEntityManager().getPlayer().setyMove(rt_yMove);
            handler.getWorld().getEntityManager().getPlayer().setSpeedY(rt_yMove);
            handler.getWorld().getEntityManager().getPlayer().jumping = true;
            handler.getWorld().getEntityManager().getPlayer().post_upward_room_transitioning = true;
        }
    }

    @Override
    public void update()
    {
        CameraFocusPoint cam = handler.getWorld().getEntityManager().getPlayerCamera();
        Player player = handler.getWorld().getEntityManager().getPlayer();
        cam.update();
        handler.getCamera().centerOnEntity(cam);
        if (!damage_shock && !parry_shock && !kings_pass_exit)
            handler.getWorld().update();

        //Low HP vignette
        if (player.health <= 20)
        {
            if (!vignette_ended)
            {
                if (!flag1 && vignette_counter++ >= vignette_limit)
                {
                    vignette_counter = 0;
                    flag1 = true;
                }

                else
                {
                    vignette_opacity += 0.25 * flag0 / Launcher.framerate_limit;

                    if (flag0 < 0)
                    {
                        if (!flag1)
                        {
                            if (vignette_opacity <= 0.4F)
                            {
                                flag0 *= -1;
                                vignette_opacity = 0.4F;
                            }
                        }

                        else
                        {
                            if (vignette_opacity <= 0)
                            {
                                vignette_ended = true;
                                vignette_opacity = 0;
                            }
                        }
                    }

                    if (flag0 > 0 && vignette_opacity >= 0.75F)
                    {
                        flag0 *= -1;
                        vignette_opacity = 0.75F;
                    }
                }
            }
        }

        else
        {
            if (vignette_ended)
                vignette_ended = false;
            if (flag1)
                flag1 = false;
            if (flag0 != 1)
                flag0 = 1;
            if (vignette_opacity != 0)
                vignette_opacity = 0;
        }

        Random rand = new Random();

        if (!hazard_respawn_transition && player.hazard_triggered && player.getHealth() > 0)
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
        
        if (death_transition)
        {
            if (death_transition_state == 0)
            {
                death_transition_counter++;
                if (death_transition_counter >= Launcher.framerate_limit * (44F / 60F))
                {
                    death_transition_counter = 0;
                    death_transition_state++;
                }
            }

            else if (death_transition_state == 1)
            {
                death_transition_counter++;
                if (death_transition_counter >= Launcher.framerate_limit * (47F / 60F))
                {
                    death_transition_counter = 0;
                    death_transition_state++;
                }
            }

            else if (death_transition_state == 2)
            {
                death_transition_counter++;
                if (death_transition_counter >= Launcher.framerate_limit * (29F / 60F))
                {
                    death_transition_counter = 0;
                    death_transition = false;
                    death_transition_state = 0;
                }
            }
        }

        // Exiting King's Pass / Entering Dirtmouth the first time
        if (kings_pass_exit && counter_kpe++ >= Launcher.framerate_limit * 3)
        {
            kings_pass_exit = false;
            counter_kpe = 0;
            handler.setWorld(new World(handler, MapHelper.DIRTMOUTH, Dirtmouth.LEFT));
            updatePlayerState();
            MapHelper.getEntityData(handler, MapHelper.DIRTMOUTH).spawnEntities();
            handler.getWorld().getEntityManager().getPlayer().room_transitioning = true;
            room_transition = true;
            room_transition_state = 2;
            room_transition_counter = 0;
        }

        if (room_transition)
        {
            if (room_transition_state == 0)
            {
                room_transition_counter++;
                if (room_transition_counter >= Launcher.framerate_limit / 3)
                {
                    room_transition_counter = 0;
                    room_transition_state++;
                }
            }

            else if (room_transition_state == 1)
            {
                room_transition_counter++;
                if (room_transition_counter >= Launcher.framerate_limit * 1.5)
                {
                    room_transition_counter = 0;
                    room_transition_state++;
                }
            }

            else if (room_transition_state == 2)
            {
                room_transition_counter++;
                if (room_transition_counter >= Launcher.framerate_limit * 0.5)
                {
                    room_transition_counter = 0;
                    room_transition = false;
                    player.room_transitioning = false;
                    player.post_upward_room_transitioning = false;
                    room_transition_state = 0;
                }
            }
        }

        if (!screen_shake && handler.getWorld().getEntityManager().getPlayer().screenShakeTriggered())
            screen_shake = true;

        if (screen_shake)
        {
            if (screen_shake_timer % (Launcher.framerate_limit / 30) == (Launcher.framerate_limit / 30 - 1))
            {
                cam.setXOffset((float) (cam.getXOffset() + Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(1, 101) * 0.01 * player.getScreenShakeLevel()));
                cam.setYOffset((float) (cam.getYOffset() + Math.pow(-1, rand.nextInt(0, 2)) * rand.nextInt(1, 101) * 0.01 * player.getScreenShakeLevel()));
                if (Math.abs(cam.getXOffset()) >= 40)
                    cam.setXOffset(0);
                if (Math.abs(cam.getYOffset()) >= 40)
                    cam.setYOffset(0);
            }

            if (screen_shake_timer++ >= player.getScreenShakeLength())
            {
                screen_shake_timer = 0;
                screen_shake = false;
                player.screen_shake_triggered = false;
            }
        }

        if (!parry_shock  && player.parryFreezeTriggered())
            parry_shock = true;

        if (parry_shock)
        {
            parry_shock_timer++;
            if (parry_shock_timer >= player.getParryFreezeLength())
            {
                parry_shock_timer = 0;
                parry_shock = false;
                player.parry_freeze_triggered = false;
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
        gfx.setColor(MapHelper.worldColor(handler.getWorld().getID()));
        gfx.fillRect(0, 0, Launcher.game_width, Launcher.game_height);
        if (!kings_pass_exit)
            handler.getWorld().render(gfx);
        Player player = handler.getWorld().getEntityManager().getPlayer();

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
        if (heal_effect)
        {
            float r = heal_effect_timer / Launcher.framerate_limit;
            float rr = Math.min(4 * r, 1.0F);
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
        gfx.drawImage(player.max_soul == 66 ? Assets.soul_vessel_broken : Assets.soul_vessel_hud_down, 90, 80, null);

        double soul = Math.ceil(player.soul);
        int x = 102;
        int y = 105;

        if (soul >= 99)
            gfx.drawImage(Assets.soul9, x, y, null);

        else if (soul >= 88)
        {
            gfx.drawImage(anim_soul8.getCurrentFrame(), x, y, null);
            anim_soul8.update();
        }

        else if (soul >= 77)
        {
            gfx.drawImage(anim_soul7.getCurrentFrame(), x, y, null);
            anim_soul7.update();
        }

        else if (soul >= 66)
        {
            gfx.drawImage(anim_soul6.getCurrentFrame(), x, y, null);
            anim_soul6.update();
        }

        else if (soul >= 55)
        {
            gfx.drawImage(anim_soul5.getCurrentFrame(), x, y, null);
            anim_soul5.update();
        }

        else if (soul >= 44)
        {
            gfx.drawImage(anim_soul4.getCurrentFrame(), x, y, null);
            anim_soul4.update();
        }

        else if (soul >= 33)
        {
            gfx.drawImage(anim_soul3.getCurrentFrame(), x, y, null);
            anim_soul3.update();
        }

        else if (soul >= 22)
        {
            gfx.drawImage(anim_soul2.getCurrentFrame(), x, y, null);
            anim_soul2.update();
        }

        else if (soul >= 11)
        {
            gfx.drawImage(anim_soul1.getCurrentFrame(), x, y, null);
            anim_soul1.update();
        }

        if (soul >= 55)
            gfx.drawImage(Assets.soul_vessel_hud_middle, 115, 165, null);

        //Geo
        gfx.drawImage(Assets.geo_hud, 223, 165, null);
        gfx.setColor(Color.white);
        gfx.setFont(Assets.trajan);
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
            gfx.setFont(Assets.perpetua);
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

        //Fade to black at room transitions
        if (room_transition)
        {
            if (room_transition_state == 0)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ((float) room_transition_counter) / (Launcher.framerate_limit / 3F)));
            else if (room_transition_state == 1)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            else if (room_transition_state == 2)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - ((float) room_transition_counter) / (Launcher.framerate_limit * 0.5F)));
            gfx.setColor(Color.black);
            gfx.fillRect(0, 0, Launcher.game_width, Launcher.game_height);
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            gfx.setColor(Color.white);
        }

        //Fade to black at death
        if (death_transition)
        {
            if (death_transition_state == 0)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ((float) death_transition_counter) / (Launcher.framerate_limit * (44F / 60F))));
            else if (death_transition_state == 1)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            else if (death_transition_state == 2)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - ((float) death_transition_counter) / (Launcher.framerate_limit * (29F / 60F))));
            gfx.setColor(Color.black);
            gfx.fillRect(0, 0, Launcher.game_width, Launcher.game_height);
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            gfx.setColor(Color.white);
        }

        if (kings_pass_exit)
        {
            gfx.setColor(Color.black);
            gfx.fillRect(0, 0, Launcher.game_width, Launcher.game_height);
        }

        //Debug stuff

        //Coordinates
        gfx.setColor(Color.white);
        gfx.setFont(new Font("Arial", Font.PLAIN, 12));
        if (Launcher.show_coordinates)
            gfx.drawString("X: " + (int) player.getX() + "  Y: " + (int) player.getY(), 5, 30);
        if (Launcher.show_world_name)
            gfx.drawString(MapHelper.path(handler.getWorld().getID()).substring(37, MapHelper.path(handler.getWorld().getID()).length() - 4) + " | " + handler.getWorld().getID(), 5, 45);

        //Framerate
        if (Launcher.log_fps)
            gfx.drawString("FPS: " + fps, 5, 15);

        gfx.drawImage(Assets.cursor, MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, null);
    }

    public void triggerHealEffect()
    {
        if (!heal_effect)
            heal_effect = true;
        heal_effect_timer = 0;
    }

    public void triggerDeathTransitionEffect()
    {
        if (!death_transition)
            death_transition = true;
        death_transition_state = 0;
        death_transition_counter = 0;
    }

    public void triggerRoomTransitionEffect()
    {
        if (!room_transition)
            room_transition = true;
        room_transition_counter = 0;
        room_transition_state = 0;
    }

    public void triggerKingsPassExit()
    {
        if (!kings_pass_exit)
            kings_pass_exit = true;
        counter_kpe = 0;
    }

    public void setFps(int fps)
    {
        this.fps = fps;
    }
}
