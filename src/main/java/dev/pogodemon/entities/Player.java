package dev.pogodemon.entities;

import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.display.Animation;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.objects.DeathPropNail;
import dev.pogodemon.entities.objects.DeathPropShell;
import dev.pogodemon.entities.particles.*;
import dev.pogodemon.items.Item;
import dev.pogodemon.items.Items;
import dev.pogodemon.states.GameState;
import dev.pogodemon.states.State;
import dev.pogodemon.utils.*;
import dev.pogodemon.world.Tile;
import dev.pogodemon.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.ArrayList;

public class Player extends Creature
{
    private final Animation anim_wings_left = new Animation(Math.round(1000F*29F/60F/23F), Assets.wings_left);
    private final Animation anim_wings_right = new Animation(Math.round(1000F*29F/60F/23F), Assets.wings_right);

    private final Animation anim_low_hp_left = new Animation(500, Assets.low_hp_left);
    private final Animation anim_low_hp_right = new Animation(500, Assets.low_hp_right);
    private final Animation anim_walk_right = new Animation(Math.round(1000F/12F), Assets.walk_right);
    private final Animation anim_walk_left = new Animation(Math.round(1000F/12F), Assets.walk_left);

    private final Animation anim_slash1_char_right = new Animation(Math.round(1000F/21F), Assets.slash1_char_right);
    private final Animation anim_slash2_char_right = new Animation(Math.round(1000F/21F), Assets.slash2_char_right);
    private final Animation anim_slash1_char_left = new Animation(Math.round(1000F/21F), Assets.slash1_char_left);
    private final Animation anim_slash2_char_left = new Animation(Math.round(1000F/21F), Assets.slash2_char_left);
    private final Animation anim_upslash_char_left = new Animation(Math.round(1000F/21F), Assets.upslash_char_left);
    private final Animation anim_upslash_char_right = new Animation(Math.round(1000F/21F), Assets.upslash_char_right);
    private final Animation anim_downslash_char_left = new Animation(Math.round(1000F/20F), Assets.downslash_char_left);
    private final Animation anim_downslash_char_right = new Animation(Math.round(1000F/20F), Assets.downslash_char_right);

    private final Animation anim_dash_left = new Animation(Math.round(1000F*23F/60F/17F), Assets.dash_left);
    private final Animation anim_dash_right = new Animation(Math.round(1000F*23F/60F/17F), Assets.dash_right);

    private final Animation anim_shadow_dash_right = new Animation(Math.round(1000F*19F/60F/17F), Assets.shadow_dash_right);
    private final Animation anim_shadow_dash_left = new Animation(Math.round(1000F*19F/60F/17F), Assets.shadow_dash_left);

    private final Animation anim_superdash_wind = new Animation(54, Assets.superdash_charge_wind);
    private final Animation anim_superdash = new Animation(50, Assets.superdash);
    private final Animation anim_superdash_trail = new Animation(50, Assets.superdash_trail);

    private int sleep_state = 0;
    public boolean waking_up = false;
    private int wake_up_timer = 0;
    public boolean sleeping = false;
    private boolean illegal_wakeup = true;

    private int double_jump_limiter = 0;
    private boolean double_jump_initiated = false;

    /*
    0 >> frame 1
    1 >> frame 2
    2 >> props
    3 >> respawn
     */
    public int dead_state = 0;
    public boolean dead = false;
    private int dead_counter = 0;
    private boolean respawn = false;
    private boolean prop_nail_spawned = false;
    private boolean prop_shell_spawned = false;
    private int shade_spawn_timer = 0;
    private boolean shade_spawn_initiated = false;
    private boolean death_transition_triggered = false;
    private int low_hp_particle_counter = 0;

    public boolean benched = false;
    private boolean getting_off = false;
    private int getting_off_timer = 0;
    private int bench_duration = 0;
    private boolean asleep = false;
    public boolean asleep_after_death = false;
    private int asleep_after_death_counter = 0;
    public float bench_target_x, bench_target_y;

    public boolean screen_shake_triggered = false;
    private float screen_shake_length = 0;
    private float screen_shake_level = 10;

    public boolean damage_shock_freeze_triggered = false;
    private float damage_shock_freeze_length = 0;

    public boolean parry_freeze_triggered = false;
    private float parry_freeze_length = 0;

    public int max_health;
    private int geo;
    public int geo_buffer = 0;
    public boolean has_buffered_geo;
    private int geo_buffer_timer;

    private int heal_buffer_timer = 0;
    private boolean healing = false;
    private int heal_timer = 0;
    private boolean post_heal = false;
    private int post_heal_timer = 0;
    public boolean illegal_heal = false;
    private int heal_counter = 0;
    private boolean can_heal = false;

    public int lifeblood = 0;

    public float soul = 0;
    public float max_soul = 0;

    private boolean fireball_knockback = false;
    private int fireball_knockback_timer = 0;
    private boolean fireball_blocked = false;
    private int fireball_cooldown_timer = 0;
    public boolean illegal_fireball = false;
    private boolean heal_button_fireball = false;
    private int heal_button_fireball_timer = 0;

    public boolean item_pickup = false;
    private boolean item_pickup_ground = false;
    private int item_pickup_timer = 0;

    private ArrayList<Item> items = new ArrayList<Item>();

    /*
    0 -> Fade in
    1 -> Static
    2 -> Fade out
     */
    public int itemDisplayState = 0;
    public Item latestItemDisplayed = null;
    public boolean itemDisplayed = false;
    public float itemDisplayCounter = 0;

    public boolean room_transitioning = false;
    public boolean post_upward_room_transitioning = false;
    public boolean pre_upward_room_transitioning = false;
    public boolean horizontal_room_transitioning = false;
    public float rt_temp_yMove = 0;

    private int shadow_dash_particle_counter = 0;

    private boolean shade_location_set = false;
    private ArrayList<Coordinate> shade_coordinate_buffer = new ArrayList<Coordinate>();

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, 0, 0);

        bounds.x = 6;
        bounds.y = 40;
        bounds.width = 44;
        bounds.height = 87;
        health = 20 * (int) GameFlags.data.getValue("masks");
        geo = (int) GameFlags.data.getValue("geo");
        max_health = health;
        can_be_killed = false;

        CREATURE_TYPE = 0; //Player

        giveItem(Items.mothwingCloak);
        giveItem(Items.shadeCloak);
        giveItem(Items.mantisClaw);
        giveItem(Items.crystalHeart);
        giveItem(Items.monarchWings);
    }

    public void die()
    {
        GameState state = (GameState) State.getState();
        if (!shade_location_set)
        {
            shade_location_set = true;
            GameFlags.setShadeState(handler.getWorld().getID(), getGeo(), shade_coordinate_buffer.get(0).getX(), shade_coordinate_buffer.get(0).getY());
            removeGeo(geo);
            GameFlags.data.updateValue("geo", 0);
            GameFlags.data.updateLocalFile();
        }

        if (dead_state == 3 && !death_transition_triggered)
        {
            state.triggerDeathTransitionEffect();
            death_transition_triggered = true;
        }

        if (!dead)
        {
            dead = true;

            setScreenShakeLength(Launcher.framerate_limit * 2.25F);
            setScreenShakeLevel(10);

            triggerDamageFreeze();
            setDamageShockFreezeLength(Launcher.framerate_limit);
        }

        if (respawn)
        {
            handler.setWorld(new World(handler, GameFlags.respawn_map_id, GameFlags.respawn_coordinates, GameFlags.respawn_state, true));
            MapHelper.getEntityData(handler, GameFlags.respawn_map_id).spawnEntities();
            GameFlags.bench_vfx = true;
            handler.getWorld().getEntityManager().getPlayer().asleep = true;
            handler.getWorld().getEntityManager().getPlayer().asleep_after_death = true;
        }
    }

    public void bench(float x, float y)
    {
        benched = true;
        bench_target_x = x;
        bench_target_y = y;
        GameFlags.setRespawnState(handler.getWorld().getID(), x, y);
        GameFlags.data.updateValue("geo", geo);
        GameFlags.respawn_state = World.BENCHED;
        GameFlags.data.updateValue("respawn_state", GameFlags.respawn_state);
        GameFlags.load_state = World.BENCHED;
        GameFlags.load_map_id = handler.getWorld().getID();
        GameFlags.load_coordinates = new Coordinate(x, y);
        GameFlags.data.updateValue("load_state", GameFlags.load_state);
        GameFlags.data.updateValue("load_map_id", GameFlags.load_map_id);
        GameFlags.data.updateValue("load_coordinates_x", GameFlags.load_coordinates.getX());
        GameFlags.data.updateValue("load_coordinates_y", GameFlags.load_coordinates.getY());
        GameFlags.data.updateLocalFile();
    }

    public void bench_off()
    {
        if (asleep)
        {
            getting_off = true;
            asleep = false;
        }

        else
            benched = false;
    }

    public void triggerParryFreeze()
    {
        if (!parry_freeze_triggered)
            parry_freeze_triggered = true;
    }

    public boolean parryFreezeTriggered()
    {
        return parry_freeze_triggered;
    }

    public void setParryFreezeLength(float i)
    {
        parry_freeze_length = i;
    }

    public float getParryFreezeLength()
    {
        return parry_freeze_length;
    }

    public void triggerDamageFreeze()
    {
        if (!damage_shock_freeze_triggered)
            damage_shock_freeze_triggered = true;
    }

    public boolean damageFreezeTriggered()
    {
        return damage_shock_freeze_triggered;
    }

    public void triggerScreenShake()
    {
        if (!screen_shake_triggered)
            screen_shake_triggered = true;
    }

    public boolean screenShakeTriggered()
    {
        return screen_shake_triggered;
    }

    public void displayItem(Item item)
    {
        if (itemDisplayed)
        {
            itemDisplayState = 0;
            itemDisplayCounter = 0;
        }

        else
            itemDisplayed = true;

        latestItemDisplayed = item;
    }

    public void giveItem(Item item)
    {
        ArrayList<Item> temp = new ArrayList<Item>(items);
        temp.add(item);
        items = temp;
    }

    public void takeItem(Item item)
    {
        ArrayList<Item> temp = new ArrayList<Item>(items);
        for (Item i : temp)
        {
            if (i.type() == item.type() && i.name().equals(item.name()))
            {
                temp.remove(i);
                break;
            }
        }
        items = temp;
    }

    public boolean looking_up = false;
    public boolean looking_down = false;
    private int looking_up_timer = 0;
    private int looking_down_timer = 0;

    private boolean render_black_sprite = false;
    private int black_sprite_timer = 0;

    public int nail_damage = 5;
    public int fireball_damage = 15;
    public boolean alternative_slash_sprite = true; // = !self to change
    public boolean slashing = false;
    private boolean slash_blocked = false;
    public boolean illegal_slash = false;
    public boolean down_slashing = false;
    public boolean up_slashing = false;
    public boolean attack_knockback = false;
    public boolean just_knocked_back = false;
    public boolean pogo = false;
    public boolean just_pogoed = false;
    long attack_knockback_timer = 0;
    long pogo_timer = 0;
    long invulnerable_timer = 0;
    long damage_shock_timer = 0;
    long slash_cooldown_timer = 0;
    long slash_timer = 0;

    public boolean hazard_triggered = false;
    private boolean flag1 = false;
    private int hazard_timer = 0;

    long superdash_shock_timer = 0;
    private boolean superdash_charged = false;
    long superdash_charge_timer = 0;
    public boolean illegal_superdash = true;

    private float respawnX = 0;
    private float respawnY = 0;

    public void hazardRespawn()
    {
        hazard_triggered = true;
        pogo = false;
        slashing = false;

        if (!damage_shocked)
            damage_shocked = true;

        damage_shocked_right = !isFacingRight();
        triggerScreenShake();
        setScreenShakeLength(Launcher.framerate_limit * 0.5F);
        setScreenShakeLevel(10);
    }

    public void updateRespawnPoint(float x, float y)
    {
        respawnX = x;
        respawnY = y;
    }

    public void addSoul(float s)
    {
        soul += s;
        if (soul > max_soul)
            soul = max_soul;
    }

    public void pickupItem(boolean ground)
    {
        item_pickup = true;
        item_pickup_ground = ground;
    }

    @Override
    public void update()
    {
        //Superdash illegal (double) jump
        if ((!illegal_jumping || !illegal_double_jumping) && superdash_shocked)
        {
            illegal_jumping = true;
            illegal_double_jumping = true;
        }

        //Superdash animation
        if (superdash)
        {
            anim_superdash.update();
            anim_superdash_trail.update();
        }

        //Superdash wind animation
        if (superdash_charge_timer > 0)
            anim_superdash_wind.update();
        else
            anim_superdash_wind.reset();

        //Proper setup of max soul
        if (GameFlags.hasShade && max_soul != 66)
            max_soul = 66;
        if (!GameFlags.hasShade && max_soul != 99)
            max_soul = 99;

        //Waking up system
        if (sleeping)
        {
            if (illegal_wakeup && !handler.getKeyManager().z && !handler.getKeyManager().up && !handler.getKeyManager().down)
                illegal_wakeup = false;

            if (!illegal_wakeup && (handler.getKeyManager().z || handler.getKeyManager().up || handler.getKeyManager().down))
                waking_up = true;

            if (waking_up)
            {
                if (sleep_state == 0 && wake_up_timer++ >= Launcher.framerate_limit * 1.25F)
                {
                    sleep_state++;
                    wake_up_timer = 0;
                }

                if (sleep_state == 1 && wake_up_timer++ >= Launcher.framerate_limit * 0.75F)
                {
                    sleeping = false;
                    wake_up_timer = 0;
                    waking_up = false;
                    illegal_wakeup = true;
                }
            }
        }

        //Shade spawn coordinate buffering
        shade_coordinate_buffer.add(new Coordinate(getX(), getY()));
        if (shade_coordinate_buffer.size() >= Launcher.framerate_limit) // One second before death
            shade_coordinate_buffer.remove(shade_coordinate_buffer.get(0));


        if (just_pogoed && !slashing)
            just_pogoed = false;
        
        if (shadow_dashing || (dead && (dead_state == 1 || dead_state == 2)))
        {
            if (shadow_dash_particle_counter++ >= Launcher.framerate_limit / 60F)
            {
                handler.getWorld().spawnEntity(new ParticleShadeCloakTrailCircles(handler, getCenterX(), getCenterY()));
                handler.getWorld().spawnEntity(new ParticleShadeCloakTrailCircles(handler, getCenterX(), getCenterY()));
                handler.getWorld().spawnEntity(new ParticleShadeCloakTrailCircles(handler, getCenterX(), getCenterY()));
                shadow_dash_particle_counter = 0;
            }
        }

        if (dead)
        {
            if (shade_spawn_initiated)
            {
                if (shade_spawn_timer++ >= Launcher.framerate_limit * 58F / 60F)
                {
                    shade_spawn_initiated = false;
                    shade_spawn_timer = 0;
                    handler.getWorld().spawnEntity(new ParticleShadeSpawn(handler, isFacingRight()));
                }
            }

            if (damage_shocked)
                damage_shocked = false;

            if (fall_shocked)
                fall_shocked = false;

            if (fireball_knockback)
                fireball_knockback = false;

            if (dead_state == 0)
                dead_state++;

            else if (dead_state == 1)
            {
                if (!prop_nail_spawned)
                {
                    handler.getWorld().spawnEntity(new DeathPropNail(handler));
                    prop_nail_spawned = true;
                }

                if (dead_counter++ >= Launcher.framerate_limit * 1.25)
                {
                    dead_counter = 0;
                    dead_state++;
                }
            }

            else if (dead_state == 2)
            {
                if (!prop_shell_spawned)
                {
                    handler.getWorld().spawnEntity(new DeathPropShell(handler));
                    prop_shell_spawned = true;
                    shade_spawn_initiated = true;
                }
                if (dead_counter++ >= Launcher.framerate_limit * 1.133)
                {
                    dead_counter = 0;
                    dead_state++;
                }
            }

            else if (dead_state == 3)
            {
                if (dead_counter++ >= Launcher.framerate_limit * (91F / 60F))
                {
                    dead_counter = 0;
                    respawn = true;
                }
            }
        }

        if (benched)
        {
            if (!GameFlags.bench_vfx && getX() == bench_target_x && getY() == bench_target_y)
            {
                GameFlags.bench_vfx = true;
                GameState state = (GameState) State.getState();
                state.triggerHealEffect();
                health = max_health;
                lifeblood = 0;
            }

            if (getting_off)
            {
                if (getting_off_timer++ >= Launcher.framerate_limit * 0.5)
                {
                    getting_off_timer = 0;
                    getting_off = false;
                    benched = false;
                }
            }

            if (!getting_off && !asleep)
            {
                if (bench_duration++ >= Launcher.framerate_limit * 10)
                {
                    bench_duration = 0;
                    asleep = true;
                    facing_right = false;
                }
            }

            if (asleep_after_death && asleep)
            {
                if (asleep_after_death_counter++ >= Launcher.framerate_limit * 1.133F)
                {
                    asleep_after_death_counter = 0;
                    asleep_after_death = false;
                    asleep = false;
                }
            }

            if (getX() != bench_target_x)
            {
                if (getX() < bench_target_x)
                {
                    xMove = speedX * 0.5F;
                    if (getX() + xMove > bench_target_x)
                    {
                        setX(bench_target_x);
                        xMove = 0;
                    }
                }

                else
                {
                    xMove = -speedX * 0.5F;
                    if (getX() + xMove < bench_target_x)
                    {
                        setX(bench_target_x);
                        xMove = 0;
                    }
                }
            }

            else
                xMove = 0;
            
            if (getY() != bench_target_y)
            {
                if (getY() > bench_target_y)
                {
                    yMove = -speedX;
                    if (getY() + yMove < bench_target_y)
                    {
                        setY(bench_target_y);
                        yMove = 0;
                    }
                }

                else
                {
                    setY(bench_target_y);
                    yMove = 0;
                }
            }
        }

        else
        {
            if (asleep)
                asleep = false;
            if (GameFlags.bench_vfx)
                GameFlags.bench_vfx = false;
            if (bench_duration != 0)
                bench_duration = 0;
        }

        if (itemDisplayed)
        {
            itemDisplayCounter++;
            if (itemDisplayState == 0)
            {
                if (itemDisplayCounter >= Launcher.framerate_limit / 3F)
                {
                    itemDisplayCounter = 0;
                    itemDisplayState++;
                }
            }

            else if (itemDisplayState == 1)
            {
                if (itemDisplayCounter >= Launcher.framerate_limit * 4)
                {
                    itemDisplayCounter = 0;
                    itemDisplayState++;
                }
            }

            else if (itemDisplayState == 2)
            {
                if (itemDisplayCounter >= Launcher.framerate_limit / 6F)
                {
                    itemDisplayCounter = 0;
                    itemDisplayState = 0;
                    itemDisplayed = false;
                    latestItemDisplayed = null;
                }
            }
        }

        for (Item i : items)
        {
            if (i.type() == Item.SKILL)
            {
                if (!hasMothwingCloak && i.name().equals(Items.mothwingCloak.name()))
                {
                    hasMothwingCloak = true;
                    continue;
                }

                if (!hasShadeCloak && i.name().equals(Items.shadeCloak.name()))
                {
                    hasShadeCloak = true;
                    continue;
                }

                if (!hasMantisClaw && i.name().equals(Items.mantisClaw.name()))
                {
                    hasMantisClaw = true;
                    continue;
                }

                if (!hasCrystalHeart && i.name().equals(Items.crystalHeart.name()))
                {
                    hasCrystalHeart = true;
                    continue;
                }

                if (!hasMonarchWings && i.name().equals(Items.monarchWings.name()))
                    hasMonarchWings = true;
            }
        }

        if (item_pickup)
        {
            item_pickup_timer++;
            if (item_pickup_timer >= Launcher.framerate_limit * 0.8)
            {
                item_pickup_timer = 0;
                item_pickup = false;
            }
        }

        if (item_pickup && damage_shocked)
        {
            item_pickup = false;
            item_pickup_timer = 0;
        }

        if (fireball_knockback)
        {
            fireball_knockback_timer++;
            if (fireball_knockback_timer >= Launcher.framerate_limit * 0.3)
            {
                fireball_knockback_timer = 0;
                fireball_knockback = false;
            }
        }

        if (fireball_blocked)
        {
            fireball_cooldown_timer++;
            if (fireball_cooldown_timer >= Launcher.framerate_limit * 0.25)
            {
                fireball_cooldown_timer = 0;
                fireball_blocked = false;
            }
        }

        if (fall_shocked && !illegal_heal && handler.getKeyManager().a)
            illegal_heal = true;

        if (healing && !grounded)
        {
            healing = false;
            illegal_heal = true;
        }
        if (Math.ceil(soul) >= 33 && !can_heal && !healing)
            can_heal = true;

        if (!healing && heal_buffer_timer == 0 && Math.ceil(soul) < 33)
            can_heal = false;

        //collapsing floor superdash buffer bug fix
        if (superdash_charge_timer > 0 && !(grounded || cling_right || cling_left))
        {
            superdash_charge_timer = 0;
            illegal_superdash = true;
        }

        //invulnerability visual effect
        if (invulnerable)
        {
            black_sprite_timer++;
            if (black_sprite_timer >= Launcher.framerate_limit * 0.1)
            {
                black_sprite_timer = 0;
                render_black_sprite = !render_black_sprite;
            }
        }

        else if (render_black_sprite)
            render_black_sprite = false;

        if (damage_shocked)
        {
            if (!damage_shocked_right)
            {
                if (!hazard_triggered)
                    xMove = -speedX * 1.5F;
                else
                    xMove = 0;
                if (!facing_right)
                    facing_right = true;
            }

            else
            {
                if (!hazard_triggered)
                    xMove = speedX * 1.5F;
                else
                    xMove = 0;
                if (facing_right)
                    facing_right = false;
            }
        }

        if (fireball_knockback)

        {
            if (isFacingRight())
                xMove = -DEFAULT_SPEED * 0.15F;
            else
                xMove = DEFAULT_SPEED * 0.15F;
            if (jumping)
                jumping = false;
            yMove = 0;
        }

        if (checkEntityCollisions(xMove, yMove))
        {
            for (Entity e : getCollidingEntities(xMove, yMove))
                e.playerContact();
        }

        if (has_buffered_geo)
        {
            geo_buffer_timer++;
            if (geo_buffer_timer >= Launcher.framerate_limit * 104 / 60)
            {
                has_buffered_geo = false;
                geo += geo_buffer;
                geo_buffer_timer = 0;
                geo_buffer = 0;
            }
        }

        if (ceiling_collide)
        {
            pogo = false;
            pogo_timer = 0;
        }

        if (superdash)
        {
            if (xMove > 0)
            {
                int tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.SIZE);
                boolean bool = collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.SIZE))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.SIZE))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.SIZE))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.SIZE)) || checkEntityMoveCollisions(xMove + 2, 0);
                if (bool)
                {
                    superdash = false;
                    superdash_shocked = true;
                }
            }

            else if (xMove < 0)
            {
                int tx = (int) Math.floor((x + xMove + bounds.x) / Tile.SIZE);
                boolean bool = collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.SIZE))
                        || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.SIZE))
                        || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.SIZE))
                        || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.SIZE))
                        || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.SIZE)) || checkEntityMoveCollisions(xMove - 2, 0);
                if (bool)
                {
                    superdash = false;
                    superdash_shocked = true;
                }
            }
        }

        if (!can_shadow_dash && shadow_dash_cooldown > 0)
        {
            shadow_dash_cooldown++;

            if (shadow_dash_cooldown >= Launcher.framerate_limit * 1.5)
            {
                shadow_dash_cooldown = 0;
                can_shadow_dash = true;
            }
        }

        if (superdash_shocked)
        {
            superdash_shock_timer++;
            if (!superdash_charged && superdash_charge_timer == 0 && !cling_left && !cling_right)
            {
                if (isFacingRight())
                    xMove = DEFAULT_SPEED;
                else
                    xMove = -DEFAULT_SPEED;
            }

            if (superdash_shock_timer >= Launcher.framerate_limit * 0.3)
            {
                superdash_shocked = false;
                superdash_shock_timer = 0;
            }
        }

        if ((cling_left || cling_right) && (!superdash_charged || superdash_charge_timer == 0) && minimum_superdash_timer > 0)
        {
            superdash = false;
            superdash_shocked = true;
        }

        if (superdash && minimum_superdash_timer < Launcher.framerate_limit * 0.1)
            minimum_superdash_timer++;

        if (!superdash)
            minimum_superdash_timer = 0;

        if (double_jumping)
        {
            fall_distance = 0;
            will_hard_fall = false;
        }

        if (cling_left || cling_right)
        {
            if (up_slashing)
                up_slashing = false;
            if (down_slashing)
                down_slashing = false;
        }

        if (just_knocked_back && !slashing)
            just_knocked_back = false;

        if (attack_knockback)
        {
            just_knocked_back = true;
            float s = DEFAULT_SPEED * 0.46f;
            if (facing_right)
                xMove = -s;

            else
                xMove = s;

            attack_knockback_timer++;
            if (attack_knockback_timer >= Launcher.framerate_limit * 0.2)
            {
                attack_knockback_timer = 0;
                attack_knockback = false;
            }
        }

        if (pogo)
        {
            speedY = 0;
            pogo_timer++;
            if (pogo_timer >= Launcher.framerate_limit * 0.25)
            {
                pogo_timer = 0;
                pogo = false;
                yMove = 0;
            }

            if (!can_dash && hasMothwingCloak)
                can_dash = true;

            if (did_double_jump)
                did_double_jump = false;

            if (fall_distance > 0)
                fall_distance = 0;
        }

        if (!handler.getKeyManager().x && !slashing)
        {
            if (handler.getKeyManager().up)
            {
                up_slashing = true;
                down_slashing = false;
            }

            else if (!handler.getKeyManager().up)
            {
                up_slashing = false;
            }
        }

        if (!handler.getKeyManager().x && !slashing && !grounded)
        {
            if (handler.getKeyManager().down)
            {
                down_slashing = true;
                up_slashing = false;
            }

            else if (!handler.getKeyManager().down)
            {
                down_slashing = false;
            }
        }

        if (grounded && down_slashing && !slashing)
            down_slashing = false;

        if (!handler.getKeyManager().x && handler.getKeyManager().up && handler.getKeyManager().down)
        {
            down_slashing = false;
            up_slashing = false;
        }

        if (slashing)
        {
            slash_timer++;
            if (!slash_blocked)
                slash_blocked = true;
        }

        if (slash_blocked)
            slash_cooldown_timer++;

        if (slash_timer >= Launcher.framerate_limit * (quickSlash ? 0.25 : 0.35))
        {
            slash_timer = 0;
            slashing = false;
        }

        if (slash_cooldown_timer >= Launcher.framerate_limit * (quickSlash ? 0.28 : 0.41))
        {
            slash_cooldown_timer = 0;
            slash_blocked = false;
        }

        //Respawn system
        if (health <= 0)
        {
            //handler.getKeyManager().esc = true;
            die();
        }

        if (invulnerable)
            invulnerable_timer++;

        if (damage_shocked)
        {
            if (!hazard_triggered)
                damage_shock_timer++;

            if (looking_up)
                looking_up = false;

            if (looking_down)
                looking_down = false;
        }


        if (invulnerable_timer >= Launcher.framerate_limit * 1.3)
        {
            invulnerable = false;
            invulnerable_timer = 0;
        }

        if (damage_shock_timer >= Launcher.framerate_limit * 0.25)
        {
            damage_shocked = false;
            damage_shock_timer = 0;
        }

        if (hasMothwingCloak && (cling_right || cling_left))
            can_dash = true;

        //prevent hard fall by dashing or clinging
        if (dashing || grounded || cling_right || cling_left)
        {
            fall_distance = 0;
        }
        
        if ((cling_right && !jumping) || (cling_left && !jumping))
        {
            wall_jumping_right = false;
            wall_jumping_left = false;
        }

        getInput();
        moveX();
        if (!horizontal_room_transitioning && !pre_upward_room_transitioning)
            moveY();
        else
            y += rt_temp_yMove;

        //wall cling reset
        if (grounded || dashing)
        {
            if (cling_right)
                cling_right = false;

            if (cling_left)
                cling_left = false;
        }

        if (dashing || cling_right || cling_left)
        {
            will_hard_fall = false;
            if (-speedY > DEFAULT_SPEED * 2 * 0.4 || yMove > DEFAULT_SPEED * 2 * 0.4)
            {
                yMove = 0;
                if (superdash_shocked)
                    speedY = 0;
                else
                    speedY = DEFAULT_SPEED * 2 * 0.4f;
            }

            if (superdash_charge_timer > 0 || superdash_charged)
            {
                xMove = 0;
                yMove = 0;
                speedY = 0;
            }

        }

        if (pogo)
            yMove = -DEFAULT_SPEED * 1.25F;

        if (superdash)
        {
            speedY = 0;
            yMove = 0;

            if (facing_right)
                xMove = DEFAULT_SPEED * 2.51027861f;
            else
                xMove = -DEFAULT_SPEED * 2.51027861f;

            if (damage_shocked)
                superdash = false;
        }

        if (hazard_triggered)
        {
            if (!flag1)
            {

                flag1 = true;
                dealDamage();
            }

            hazard_timer++;
            if (hazard_timer >= Launcher.framerate_limit)
            {
                hazard_timer = 0;
                hazard_triggered = false;
                flag1 = false;
                xMove = 0;
                yMove = 0;
                setX(respawnX);
                setY(respawnY);
                handler.getWorld().getEntityManager().getPlayerCamera().clearCameraQueue();
                invulnerable = true;
                fall_shocked = true;
                dashing = false;
                shadow_dashing = false;
                superdash = false;
                pogo = false;
                damage_shocked = false;
            }
        }

        if (dashing && !shadow_dashing)
        {
            anim_dash_left.update();
            anim_dash_right.update();
        }

        else
        {
            anim_dash_left.reset();
            anim_dash_right.reset();
        }

        if (shadow_dashing)
        {
            anim_shadow_dash_right.update();
            anim_shadow_dash_left.update();
        }

        else
        {
            anim_shadow_dash_right.reset();
            anim_shadow_dash_left.reset();
        }

        if (health <= 20 && !dead)
        {
            if (grounded)
            {
                anim_low_hp_left.update();
                anim_low_hp_right.update();
            }

            if (low_hp_particle_counter++ >= Launcher.framerate_limit * 0.2F)
            {
                handler.getWorld().spawnEntity(new ParticleLowHealth(handler, getCenterX(), getCenterY()));
                low_hp_particle_counter = 0;
            }
        }

        anim_walk_left.update();
        anim_walk_right.update();

        if (slashing)
        {
            anim_slash1_char_right.update();
            anim_slash2_char_right.update();
            anim_slash1_char_left.update();
            anim_slash2_char_left.update();
            anim_upslash_char_left.update();
            anim_upslash_char_right.update();
            anim_downslash_char_right.update();
            anim_downslash_char_left.update();
        }

        else
        {
            anim_slash1_char_right.reset();
            anim_slash2_char_right.reset();
            anim_slash1_char_left.reset();
            anim_slash2_char_left.reset();
            anim_upslash_char_left.reset();
            anim_upslash_char_right.reset();
            anim_downslash_char_right.reset();
            anim_downslash_char_left.reset();
        }

        if (double_jump_initiated)
        {
            anim_wings_left.update();
            anim_wings_right.update();
        }

        else
        {
            anim_wings_left.reset();
            anim_wings_right.reset();
        }
    }

    long dash_length_timer = 0;
    long dash_cooldown_timer = 0;
    long fall_shock_timer = 0;
    long wall_jump_force_horizontal_move_timer = 0;

    private void getInput()
    {

        if (item_pickup || damage_shocked || fall_shocked || room_transitioning || benched)
        {
            if (handler.getKeyManager().a)
            {
                illegal_heal = true;
                illegal_fireball = true;
            }

            if (handler.getKeyManager().s)
                illegal_superdash = true;

            if (handler.getKeyManager().f)
                illegal_fireball = true;

            if (handler.getKeyManager().z)
            {
                illegal_jumping = true;
                illegal_double_jumping = true;
            }

            if (handler.getKeyManager().x)
                illegal_slash = true;

            if (handler.getKeyManager().c)
                can_dash = false;
        }

        if (dashing && !illegal_slash && handler.getKeyManager().x)
            illegal_slash = true;

        if (illegal_heal && heal_button_fireball)
        {
            heal_button_fireball_timer++;
            if (heal_button_fireball_timer >= Launcher.framerate_limit * 0.25F)
            {
                heal_button_fireball_timer = 0;
                heal_button_fireball = false;
            }
        }

        if (!post_heal && !heal_button_fireball && !illegal_heal && handler.getKeyManager().a)
            heal_button_fireball = true;

        if ((((heal_buffer_timer > 0 && !post_heal && !healing) || illegal_heal) && heal_button_fireball && !handler.getKeyManager().a) || (!illegal_fireball && !fireball_blocked && !dashing && !superdash && superdash_charge_timer == 0 && !superdash_charged && !item_pickup && !dead &&!fall_shocked && !damage_shocked && !sleeping && !benched && !post_heal && handler.getKeyManager().f))
        {
            if (Math.ceil(soul) >= 33 && (slash_timer == 0 || slash_timer >= Launcher.framerate_limit * 0.175F))
            {
                handler.getWorld().spawnEntity(new Fireball(handler, getCenterX(), getCenterY(), shamanStone, isFacingRight()));
                speedY = 0;
                soul -= 33;
                fireball_blocked = true;
                fireball_knockback = true;
                if (slashing) // nail cancel
                    slashing = false;
            }
            illegal_fireball = true;
            heal_button_fireball = false;
        }

        if (illegal_fireball && !handler.getKeyManager().f)
            illegal_fireball = false;

        if (!fireball_knockback && can_heal && !illegal_heal && grounded && !dashing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !item_pickup && !fall_shocked && !dead && !damage_shocked && !sleeping && !benched && handler.getKeyManager().a)
        {
            if (!healing)
            {
                heal_buffer_timer++;
                if (heal_buffer_timer >= Launcher.framerate_limit * 0.25F)
                {
                    heal_buffer_timer = 0;
                    healing = true;
                }
            }

            else
            {
                if (!post_heal)
                    post_heal = true;

                soul -= 33F / Launcher.framerate_limit / 0.891F;
                if (soul < 0)
                    soul = 0;

                heal_timer++;
                if (heal_timer >= Launcher.framerate_limit * 0.891F)
                {
                    heal_timer = 0;
                    if (health < max_health)
                        health += 20;
                    GameState state = (GameState) State.getState();
                    state.triggerHealEffect();
                    heal_counter++;
                    heal_button_fireball = false;
                    if (heal_counter == 3 || Math.ceil(soul) < 33 || health >= max_health)
                    {
                        heal_counter = 0;
                        healing = false;
                        illegal_heal = true;
                    }
                }
            }
        }

        if (!grounded && handler.getKeyManager().a)
            illegal_heal = true;

        if (!handler.getKeyManager().a)
        {
            heal_buffer_timer = 0;
            heal_timer = 0;
            if (illegal_heal)
                illegal_heal = false;
            if (healing)
                healing = false;
        }

        if (post_heal && !healing)
        {
            if (!illegal_heal)
                illegal_heal = true;
            post_heal_timer++;
            if (post_heal_timer >= Launcher.framerate_limit * 0.25F)
            {
                post_heal_timer = 0;
                post_heal = false;
            }
        }

        if (!fireball_knockback && !post_heal && !healing && !looking_up && grounded && !superdash_shocked && !damage_shocked && !sleeping && !benched && !item_pickup && !fall_shocked && !dead && xMove == 0 && handler.getKeyManager().up)
        {
            looking_up_timer++;
            if (looking_up_timer >= Launcher.framerate_limit * 0.5)
            {
                looking_up_timer = 0;
                looking_up = true;
            }
        }

        if (!fireball_knockback && !looking_down && grounded && !superdash_shocked && !damage_shocked && !sleeping && !benched && !item_pickup && !fall_shocked && !dead && xMove == 0 && handler.getKeyManager().down)
        {
            looking_down_timer++;
            if (looking_down_timer >= Launcher.framerate_limit * 0.5)
            {
                looking_down_timer = 0;
                looking_down = true;
            }
        }

        if (looking_up_timer > 0 && !looking_up && !handler.getKeyManager().up)
            looking_up_timer = 0;

        if (looking_down_timer > 0 && !looking_down && !handler.getKeyManager().down)
            looking_down_timer = 0;
        
        if (looking_up && (!handler.getKeyManager().up || slashing || dashing || jumping || double_jumping))
            looking_up = false;

        if (looking_down && (!handler.getKeyManager().down || slashing || dashing || jumping || double_jumping))
            looking_down = false;

        if (hasCrystalHeart && ((!grounded && !cling_left && !cling_right) || superdash_shocked || superdash) && handler.getKeyManager().s)
            illegal_superdash = true;

        if (illegal_superdash && !handler.getKeyManager().s)
            illegal_superdash = false;

        if (!post_heal && !healing && hasCrystalHeart && (grounded || cling_right || cling_left) && !illegal_superdash &&!superdash_charged && handler.getKeyManager().s)
        {
            superdash_charge_timer++;
            if (superdash_charge_timer >= Launcher.framerate_limit * 0.8)
            {
                superdash_charge_timer = 0;
                superdash_charged = true;
                minimum_superdash_timer++;
            }
        }

        if (superdash_charge_timer > 0 && !handler.getKeyManager().s)
            superdash_charge_timer = 0;

        if (superdash_charged && !handler.getKeyManager().s)
        {
            superdash = true;
            superdash_charged = false;
        }

        if (!room_transitioning && superdash && minimum_superdash_timer >= Launcher.framerate_limit * 0.1 && (handler.getKeyManager().s || handler.getKeyManager().z))
        {
            superdash = false;
            superdash_shocked = true;
        }

        if (!fireball_knockback && !post_heal && !healing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !dashing && !item_pickup && !fall_shocked && !dead && !damage_shocked && !sleeping && !benched && !slashing && !slash_blocked && !illegal_slash && handler.getKeyManager().x)
        {
            slashing = true;
            handler.getWorld().spawnEntity(new PlayerSlash(handler));
            alternative_slash_sprite = !alternative_slash_sprite;
        }

        if (slash_blocked && handler.getKeyManager().x)
            illegal_slash = true;

        if (!handler.getKeyManager().x)
            illegal_slash = false;

        //So that player doesn't stop during various events
        if (!benched && !fireball_knockback && !dashing && !superdash && !damage_shocked && !superdash_shocked && !attack_knockback && !room_transitioning)
            xMove = 0;
        if (!pogo && !benched)
            yMove = 0;

        //Limit dash to 0.272 seconds
        if (dashing)
        {
            yMove = 0;
            if (slashing)
                slashing = false;

            dash_length_timer++;
            if (dash_length_timer >= Launcher.framerate_limit * 0.272)
            {
                dashing = false;
                shadow_dashing = false;
                can_dash = false;
                dash_length_timer = 0;
                just_dashed = true;
            }
        }

        //Freeze for 0.8167 seconds after a hard fall
        if (grounded && (will_hard_fall || fall_shocked))
        {
            if (will_hard_fall)
                will_hard_fall = false;
            if (!fall_shocked)
                fall_shocked = true;
        }

        if (fall_shocked)
        {
            fall_shock_timer++;
            if (fall_shock_timer >= Launcher.framerate_limit * 0.8167)
            {
                fall_shocked = false;
                fall_shock_timer = 0;
            }
        }

        //Add dash cooldown of 0.4 seconds (0.6 in the original is too much for us)
        if (just_dashed)
        {
            dash_cooldown_timer++;
            if (dash_cooldown_timer >= Launcher.framerate_limit * 0.4)
            {
                just_dashed = false;
                dash_cooldown_timer = 0;
            }
        }

        if (!fireball_knockback && !item_pickup && !fall_shocked && !dead && !damage_shocked && !sleeping && !benched)
        {
            if (!dashing && !can_dash && (grounded || can_dash_twice) && !handler.getKeyManager().c)
            {
                if (hasMothwingCloak)
                    can_dash = true;

                if (hasShadeCloak && shadow_dash_cooldown == 0)
                    can_shadow_dash = true;
            }

            if (!post_heal && !healing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !just_dashed && !dashing && can_dash && handler.getKeyManager().c)
            {
                dashing = true;

                if (can_shadow_dash)
                {
                    shadow_dashing = true;
                    handler.getWorld().spawnEntity(new ParticleShadeCloakTrail(handler, getCenterX(), getCenterY(), isFacingRight()));
                    can_shadow_dash = false;
                    shadow_dash_cooldown = 1;
                }

                if (can_dash_twice && !grounded && !cling_left && !cling_right)
                    can_dash_twice = false;

                if (!can_dash_twice && (grounded || cling_left || cling_right))
                    can_dash_twice = true;

                if (attack_knockback)
                    attack_knockback = false;

                if (facing_right)
                    xMove = DEFAULT_SPEED * 2.51027861F;
                else
                    xMove = -DEFAULT_SPEED * 2.51027861F;

                speedY = 0;
            }

            //Double jumping
            if (grounded || cling_right || cling_left)
            {
                can_double_jump = false;
                double_jump_initiated = false;
                illegal_double_jumping = false;
                did_double_jump = false;
            }

            if (double_jump_initiated && !handler.getKeyManager().z)
            {
                double_jump_initiated = false;
                double_jump_limiter = 0;
            }

            if (hasMonarchWings && !grounded && !illegal_double_jumping && !handler.getKeyManager().z)
                can_double_jump = true;

            if (illegal_jumping && !handler.getKeyManager().z)
                illegal_double_jumping = false;

            // Initiate double jump
            if (!superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !grounded && !jumping && !did_double_jump && !illegal_double_jumping && can_double_jump && handler.getKeyManager().z)
            {
                if (!double_jump_initiated)
                    double_jump_initiated = true;

                if (double_jump_limiter++ >= Launcher.framerate_limit * 0.1F)
                {
                    double_jumping = true;
                    illegal_double_jumping = true;
                    did_double_jump = true;
                    speedY = DEFAULT_SPEED * 2.5F; // double jump initial speed
                    if (!dashing)
                        yMove += -speedY;
                }
            }

            // Hold to double jump higher
            else if (double_jumping && handler.getKeyManager().z && (speedY - DEFAULT_SPEED * 0.0375 * 144 / Launcher.framerate_limit) > 0)
            {
                yMove += -speedY;
                speedY -= DEFAULT_SPEED * 0.0375 * 144 / Launcher.framerate_limit;
                if (ceiling_collide)
                {
                    jumping = false;
                    speedY = 0;
                }
            }

            else if (double_jumping && !handler.getKeyManager().z && speedY != 0)
            {
                speedY = 0;
            }

            else if (double_jumping)
            {
                can_double_jump = false;
                double_jumping = false;
                double_jump_initiated = false;
                double_jump_limiter = 0;

                if (!grounded && speedY <= (int) (DEFAULT_SPEED * 2))
                    speedY += DEFAULT_SPEED * 0.05  * 144 / Launcher.framerate_limit;

                if (grounded || dashing)
                    speedY = 0;

                if (!dashing)
                    yMove += speedY;
            }

            //Jumping START
            if (((superdash_charge_timer == 0 && !superdash_charged && !superdash && !grounded && !cling_left && !cling_right && !jumping) || dashing) && handler.getKeyManager().z)
                illegal_jumping = true;

            if (illegal_jumping && (grounded || cling_right || cling_left) && !handler.getKeyManager().z)
                illegal_jumping = false;

            if (!post_heal && !healing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !dashing && !illegal_jumping && (grounded || cling_right || cling_left) && !jumping && handler.getKeyManager().z)
            {
                if (cling_left)
                    wall_jumping_right = true;

                else if (cling_right)
                    wall_jumping_left = true;
                
                illegal_jumping = true;
                jumping = true;
                illegal_double_jumping = true;

                if (hasMonarchWings)
                    can_double_jump = true;
                
                speedY = DEFAULT_SPEED * 2.5F; //Jumping initial speed
                if (!dashing || !just_dashed)
                    yMove += -speedY;
            }

            else if (jumping && (handler.getKeyManager().z || post_upward_room_transitioning) && (speedY - DEFAULT_SPEED * 0.0375 * 144 / Launcher.framerate_limit) > 0)
            {
                yMove += -speedY;
                if (!room_transitioning)
                    speedY -= DEFAULT_SPEED * 0.0375 * 144 / Launcher.framerate_limit;

                if (ceiling_collide)
                {
                    jumping = false;
                    speedY = 0;
                }
                
                if (wall_jumping_right)
                {
                    wall_jump_force_horizontal_move_timer++;
                    if (wall_jump_force_horizontal_move_timer >= Launcher.framerate_limit * 0.15)
                    {
                        wall_jump_force_horizontal_move_timer = 0;
                        wall_jumping_right = false;
                    }
                    
                    else
                    {
                        handler.getKeyManager().right = true;
                        handler.getKeyManager().left = false;
                    }
                }

                else if (wall_jumping_left)
                {
                    wall_jump_force_horizontal_move_timer++;
                    if (wall_jump_force_horizontal_move_timer >= Launcher.framerate_limit * 0.15)
                    {
                        wall_jump_force_horizontal_move_timer = 0;
                        wall_jumping_left = false;
                    }

                    else
                    {
                        handler.getKeyManager().right = false;
                        handler.getKeyManager().left = true;
                    }
                }
            }

            else if (jumping && !handler.getKeyManager().z && !post_upward_room_transitioning && speedY != 0)
            {
                speedY = 0;
            }

            else
            {
                jumping = false;

                if (!superdash_shocked && !double_jumping && !superdash_charged && superdash_charge_timer == 0)
                {
                    int t = (int) (DEFAULT_SPEED * 2);

                    if (cling_left || cling_right)
                        t = (int) (DEFAULT_SPEED * 2 * 0.4);

                    if (!grounded && speedY <= t)
                        speedY += DEFAULT_SPEED * 0.05  * 144 / Launcher.framerate_limit;

                    if (grounded || dashing)
                        speedY = 0;

                    if (!dashing && !superdash)
                        yMove += speedY;
                }
            }

            if ((grounded || cling_right || cling_left) && !handler.getKeyManager().z)
                illegal_jumping = false;
            //Jumping END

            if (!post_heal && !healing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !dashing && !attack_knockback && !room_transitioning && handler.getKeyManager().right)
            {
                if (!slashing)
                {
                    if(!facing_right)
                        facing_right = true;
                }
                if (looking_down)
                    looking_down = false;
                if (looking_up)
                    looking_up = false;
                xMove += speedX;
            }

            if (!post_heal && !healing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !dashing && !attack_knockback && !room_transitioning && handler.getKeyManager().left)
            {
                if (!slashing)
                {
                    if(facing_right)
                        facing_right = false;
                }
                if (looking_down)
                    looking_down = false;
                if (looking_up)
                    looking_up = false;
                xMove += -speedX;
            }
        }

        //Quit game
        if (handler.getKeyManager().esc)
        {
            GameFlags.data.updateValue("geo", getGeo());
            GameFlags.data.updateLocalFile();
            System.exit(0);
        }
    }

    @Override
    public int renderRank()
    {
        return 1;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        // Taking damage effect
        if (damageFreezeTriggered() && !dead)
        {
            if (isFacingRight())
                gfx.drawImage(Assets.damage_right, (int) (x - handler.getCamera().getxOffset() - 650), (int) (y - handler.getCamera().getyOffset() - 50), null);
            else
                gfx.drawImage(Assets.damage_left, (int) (x - handler.getCamera().getxOffset() - 650), (int) (y - handler.getCamera().getyOffset() - 50), null);
        }

        //Render player

        if (benched)
        {
            if (asleep)
                gfx.drawImage(blackSprite(Assets.bench_char_2), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 10), null);
            else
                gfx.drawImage(blackSprite(Assets.bench_char_1), (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset()), null);
        }

        else if (cling_left)
        {
            if (superdash_charge_timer > 0)
            {
                gfx.drawImage(Utils.rotateImageByDegrees(anim_superdash_wind.getCurrentFrame(), 90), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 300), null);
                if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.75)
                    gfx.drawImage(Assets.superdash_crystals_wall_left_3, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 90), null);
                else if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.5)
                    gfx.drawImage(Assets.superdash_crystals_wall_left_2, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 90), null);
                else if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.25)
                    gfx.drawImage(Assets.superdash_crystals_wall_left_1, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 90), null);
                gfx.drawImage(Assets.superdash_charge_wall_right, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 10), null);
            }

            else if (superdash_charged)
            {
                gfx.drawImage(Assets.superdash_crystals_wall_left_4, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() - 90), null);
                gfx.drawImage(Assets.superdash_charge_wall_right, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 10), null);
            }
            else
                gfx.drawImage(Assets.cling_left, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset()), null);
        }

        else if (cling_right)
        {
            if (superdash_charge_timer > 0)
            {
                gfx.drawImage(Utils.mirrorImage(Utils.rotateImageByDegrees(anim_superdash_wind.getCurrentFrame(), 90)), (int) (x - handler.getCamera().getxOffset() - 240), (int) (y - handler.getCamera().getyOffset() - 300), null);
                if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.75)
                    gfx.drawImage(Assets.superdash_crystals_wall_right_3, (int) (x - handler.getCamera().getxOffset() - 55), (int) (y - handler.getCamera().getyOffset() - 90), null);
                else if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.5)
                    gfx.drawImage(Assets.superdash_crystals_wall_right_2, (int) (x - handler.getCamera().getxOffset() - 55), (int) (y - handler.getCamera().getyOffset() - 90), null);
                else if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.25)
                    gfx.drawImage(Assets.superdash_crystals_wall_right_1, (int) (x - handler.getCamera().getxOffset() - 55), (int) (y - handler.getCamera().getyOffset() - 90), null);
                gfx.drawImage(Assets.superdash_charge_wall_left, (int) (x - handler.getCamera().getxOffset() - 14), (int) (y - handler.getCamera().getyOffset() + 10), null);
            }
            else if (superdash_charged)
            {
                gfx.drawImage(Assets.superdash_crystals_wall_right_4, (int) (x - handler.getCamera().getxOffset() - 55), (int) (y - handler.getCamera().getyOffset() - 90), null);
                gfx.drawImage(Assets.superdash_charge_wall_left, (int) (x - handler.getCamera().getxOffset() - 14), (int) (y - handler.getCamera().getyOffset() + 10), null);
            }
            else
                gfx.drawImage(Assets.cling_right, (int) (x - handler.getCamera().getxOffset() - 14), (int) (y - handler.getCamera().getyOffset()), null);

        }

        else if (facing_right)
        {
            if (sleeping)
            {
                if (sleep_state == 0)
                    gfx.drawImage(Assets.sleeping_right, (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset() + 70), null);
                if (sleep_state == 1)
                    gfx.drawImage(Assets.wakeup_right, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 27), null);
            }

            else if (dead)
            {
                if (dead_state == 0)
                {
                    gfx.drawImage(Assets.death_right_1, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 15), null);
                }

                else if (dead_state == 1)
                {
                    gfx.drawImage(Assets.death_right_2, (int) (x - handler.getCamera().getxOffset() - 25), (int) (y - handler.getCamera().getyOffset() + 15), null);
                }
            }

            else if (item_pickup)
            {
                if (item_pickup_ground)
                    gfx.drawImage(Assets.fall_shock_right, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 20), null);
                else
                    gfx.drawImage(Assets.item_pickup_right, (int) (x - handler.getCamera().getxOffset() + 5), (int) (y - handler.getCamera().getyOffset() + 5), null);
            }

            else if (fireball_knockback)
                gfx.drawImage(Assets.spell_char_right, (int) (x - handler.getCamera().getxOffset() - 140), (int) (y - handler.getCamera().getyOffset() - 220), null);

            else if (healing)
            {
                gfx.drawImage(Assets.heal_right, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 5), null);
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, heal_timer / (Launcher.framerate_limit * 0.891F)));
                gfx.drawImage(Assets.heal_decal, (int) (x - handler.getCamera().getxOffset() - 30), (int) (y - handler.getCamera().getyOffset() - 80), null);
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            }

            else if (looking_up)
                gfx.drawImage(Assets.player_up_right, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset()), null);

            else if (looking_down)
                gfx.drawImage(Assets.player_down_right, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset()), null);

            else if (!superdash && superdash_shocked)
                gfx.drawImage(Assets.superdash_shocked_right, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), null);

            else if (fall_shocked)
                gfx.drawImage(blackSprite(Assets.fall_shock_right), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 20), null);

            else if (damage_shocked)
                gfx.drawImage(blackSprite(Assets.damage_shock_right), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset()), null);

            else if (dashing)
            {
                if (shadow_dashing)
                    gfx.drawImage(anim_shadow_dash_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 4 - 111 + 30 - 50), (int) (y - handler.getCamera().getyOffset() - 20), null);

                else
                    gfx.drawImage(blackSprite(anim_dash_right.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 4 - 111 + 30), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (superdash_charge_timer > 0)
            {
                gfx.drawImage(anim_superdash_wind.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 360), (int) (y - handler.getCamera().getyOffset() - 160), null);

                if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.75)
                    gfx.drawImage(Assets.superdash_crystals_right_3, (int) (x - handler.getCamera().getxOffset() - 180), (int) (y - handler.getCamera().getyOffset() + 20), null);
                else if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.5)
                    gfx.drawImage(Assets.superdash_crystals_right_2, (int) (x - handler.getCamera().getxOffset() - 180), (int) (y - handler.getCamera().getyOffset() + 20), null);
                else if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.25)
                    gfx.drawImage(Assets.superdash_crystals_right_1, (int) (x - handler.getCamera().getxOffset() - 180), (int) (y - handler.getCamera().getyOffset() + 20), null);
                gfx.drawImage(Assets.superdash_charge_right, (int) (x - handler.getCamera().getxOffset() - 15), (int) (y - handler.getCamera().getyOffset() + 15), null);
            }

            else if (superdash_charged)
            {
                gfx.drawImage(Assets.superdash_crystals_right_4, (int) (x - handler.getCamera().getxOffset() - 180), (int) (y - handler.getCamera().getyOffset() + 20), null);
                gfx.drawImage(Assets.superdash_charge_right, (int) (x - handler.getCamera().getxOffset() - 15), (int) (y - handler.getCamera().getyOffset() + 15), null);
            }

            else if (superdash)
            {
                gfx.drawImage(anim_superdash.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 60), (int) (y - handler.getCamera().getyOffset() + 20), null);
                gfx.drawImage(anim_superdash_trail.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 600), (int) (y - handler.getCamera().getyOffset() - 125), null);
            }

            else if (slashing)
            {
                if (up_slashing)
                {
                    gfx.drawImage(blackSprite(anim_upslash_char_right.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 43), (int) (y - handler.getCamera().getyOffset() + 30 - 31), null);
                }

                else if (down_slashing)
                {
                    gfx.drawImage(blackSprite(anim_downslash_char_right.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 55), (int) (y - handler.getCamera().getyOffset() + 8), null);
                }

                else if (alternative_slash_sprite)
                {
                    gfx.drawImage(blackSprite(anim_slash1_char_right.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset()), null);
                }
                else
                {
                    gfx.drawImage(blackSprite(anim_slash2_char_right.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 54), (int) (y - handler.getCamera().getyOffset()), null);
                }
            }

            else if (grounded)
            {
                if (xMove == 0)
                {
                    if (health <= 20)
                        gfx.drawImage(blackSprite(anim_low_hp_right.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 5), null);
                    else
                        gfx.drawImage(blackSprite(Assets.player_right), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);
                }

                else
                    gfx.drawImage(blackSprite(anim_walk_right.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 4 - 7), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (double_jump_initiated)
                gfx.drawImage(anim_wings_right.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 170), (int) (y - handler.getCamera().getyOffset() - 150), null);

            else
            {
                if (jumping)
                    gfx.drawImage(Assets.jump_right, (int) (x - handler.getCamera().getxOffset() - 4 - 15), (int) (y - handler.getCamera().getyOffset()),null);
                else
                    gfx.drawImage(Assets.fall_right, (int) (x - handler.getCamera().getxOffset() - 4 - 30), (int) (y - handler.getCamera().getyOffset()), null);
            }
        }

        else
        {
            if (sleeping)
            {
                if (sleep_state == 0)
                    gfx.drawImage(Assets.sleeping_left, (int) (x - handler.getCamera().getxOffset() - 40), (int) (y - handler.getCamera().getyOffset() + 70), null);
                if (sleep_state == 1)
                    gfx.drawImage(Assets.wakeup_left, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 27), null);
            }

            else if (dead)
            {
                if (dead_state == 0)
                {
                    gfx.drawImage(Assets.death_left_1, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() + 15), null);
                }

                else if (dead_state == 1)
                {
                    gfx.drawImage(Assets.death_left_2, (int) (x - handler.getCamera().getxOffset() - 25), (int) (y - handler.getCamera().getyOffset() + 15), null);
                }
            }

            else if (item_pickup)
            {
                if (item_pickup_ground)
                    gfx.drawImage(Assets.fall_shock_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 20), null);
                else
                    gfx.drawImage(Assets.item_pickup_left, (int) (x - handler.getCamera().getxOffset() - 15), (int) (y - handler.getCamera().getyOffset() + 5), null);
            }

            else if (fireball_knockback)
                gfx.drawImage(Assets.spell_char_left, (int) (x - handler.getCamera().getxOffset() - 200), (int) (y - handler.getCamera().getyOffset() - 220), null);

            else if (healing)
            {
                gfx.drawImage(Assets.heal_left, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 5), null);
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, heal_timer / (Launcher.framerate_limit * 0.891F)));
                gfx.drawImage(Assets.heal_decal, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset() - 80), null);
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            }

            else if (looking_up)
                gfx.drawImage(Assets.player_up_left, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset()), null);

            else if (looking_down)
                gfx.drawImage(Assets.player_down_left, (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset()), null);

            else if (!superdash && superdash_shocked)
                gfx.drawImage(Assets.superdash_shocked_left, (int) (x - handler.getCamera().getxOffset() - 15), (int) (y - handler.getCamera().getyOffset()), null);

            else if (fall_shocked)
                gfx.drawImage(blackSprite(Assets.fall_shock_left), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 20), null);

            else if (damage_shocked)
                gfx.drawImage(blackSprite(blackSprite(Assets.damage_shock_left)), (int) (x - handler.getCamera().getxOffset() - 20), (int) (y - handler.getCamera().getyOffset()), null);

            else if (dashing)
            {
                if (shadow_dashing)
                    gfx.drawImage(anim_shadow_dash_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 4 + 1 - 30 - 50), (int) (y - handler.getCamera().getyOffset() - 20), null);
                else
                    gfx.drawImage(blackSprite(anim_dash_left.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 4 + 1 - 30), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (superdash_charge_timer > 0)
            {
                gfx.drawImage(Utils.mirrorImage(anim_superdash_wind.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 400), (int) (y - handler.getCamera().getyOffset() - 160), null);
                if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.75)
                    gfx.drawImage(Assets.superdash_crystals_left_3, (int) (x - handler.getCamera().getxOffset() - 175), (int) (y - handler.getCamera().getyOffset() + 20), null);
                else if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.5)
                    gfx.drawImage(Assets.superdash_crystals_left_2, (int) (x - handler.getCamera().getxOffset() - 175), (int) (y - handler.getCamera().getyOffset() + 20), null);
                else if (superdash_charge_timer >= Launcher.framerate_limit * 0.8 * 0.25)
                    gfx.drawImage(Assets.superdash_crystals_left_1, (int) (x - handler.getCamera().getxOffset() - 175), (int) (y - handler.getCamera().getyOffset() + 20), null);
                gfx.drawImage(Assets.superdash_charge_left, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 15), null);
            }

            else if (superdash_charged)
            {
                gfx.drawImage(Assets.superdash_crystals_left_4, (int) (x - handler.getCamera().getxOffset() - 175), (int) (y - handler.getCamera().getyOffset() + 20), null);
                gfx.drawImage(Assets.superdash_charge_left, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 15), null);
            }

            else if (superdash)
            {
                gfx.drawImage(Utils.mirrorImage(anim_superdash.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset() + 20), null);
                gfx.drawImage(Utils.mirrorImage(anim_superdash_trail.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() + 60), (int) (y - handler.getCamera().getyOffset() - 125), null);
            }

            else if (slashing)
            {
                if (up_slashing)
                {
                    gfx.drawImage(blackSprite(anim_upslash_char_left.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 4 - 13), (int) (y - handler.getCamera().getyOffset() + 30 - 31), null);
                }

                else if (down_slashing)
                {
                    gfx.drawImage(blackSprite(anim_downslash_char_left.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 8), null);
                }

                else if (alternative_slash_sprite)
                {
                    gfx.drawImage(blackSprite(anim_slash1_char_left.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 11), (int) (y - handler.getCamera().getyOffset()), null);
                }
                else
                {
                    gfx.drawImage(blackSprite(anim_slash2_char_left.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 18), (int) (y - handler.getCamera().getyOffset()), null);
                }
            }

            else if (grounded)
            {
                if (xMove == 0)
                {
                    if (health <= 20)
                        gfx.drawImage(blackSprite(anim_low_hp_left.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 5), null);
                    else
                        gfx.drawImage(blackSprite(Assets.player_left), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);
                }

                else
                    gfx.drawImage(blackSprite(anim_walk_left.getCurrentFrame()), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (double_jump_initiated)
                gfx.drawImage(anim_wings_left.getCurrentFrame(), (int) (x - handler.getCamera().getxOffset() - 130), (int) (y - handler.getCamera().getyOffset() - 150), null);

            else
            {
                if (jumping)
                    gfx.drawImage(Assets.jump_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()),null);
                else
                    gfx.drawImage(Assets.fall_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);
            }
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
        //bruh
    }

    @Override
    public void fireballHit() {

    }

    @Override
    public void playerContact()
    {
        //bruh indeed
    }

    public int getGeo()
    {
        return geo;
    }

    public void addGeo(int i)
    {
        geo_buffer += i;
        if (!has_buffered_geo)
            has_buffered_geo = true;
        geo_buffer_timer = 0;
    }

    public void removeGeo(int i)
    {
        addGeo(-i);
    }

    public boolean isSlashing()
    {
        return slashing;
    }

    public boolean isFacingRight()
    {
        return facing_right;
    }

    //Flashing sprites when invulnerable
    private BufferedImage blackSprite(BufferedImage in)
    {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        RescaleOp rescaleOp = new RescaleOp(1f, -255, null);
        if (render_black_sprite)
            rescaleOp.filter(in, out);
        else
            out = in;

        return out;
    }

    public void dealDamage()
    {
        if (lifeblood >= 20)
        {
            lifeblood -= 20;
            handler.getWorld().spawnEntity(new ParticleHit(handler, Colors.lifeblood, getCenterX(), getCenterY()));
        }

        else
            health -= 20;
    }

    public void dealDoubleDamage()
    {
        if (lifeblood >= 40)
            lifeblood -= 40;

        else if (lifeblood == 20)
        {
            lifeblood -= 20;
            health -= 20;
        }

        else
            health -= 40;
    }

    public void setScreenShakeLength(float i)
    {
        screen_shake_length = i;
    }

    public void setScreenShakeLevel(float i)
    {
        screen_shake_level = i;
    }

    public void setDamageShockFreezeLength(float i)
    {
        damage_shock_freeze_length = i;
    }

    public float getScreenShakeLength()
    {
        return screen_shake_length;
    }

    public float getScreenShakeLevel()
    {
        return screen_shake_level;
    }

    public float getDamageShockFreezeLength()
    {
        return damage_shock_freeze_length;
    }

    public void setGeo(int geo)
    {
        this.geo = geo;
    }

    /*
    true -> right
    false -> left
     */
    public void setDirection(boolean facing_right)
    {
        this.facing_right = facing_right;
    }

    public boolean flatSurface()
    {
        return grounded;
    }

    public void setSpeedX(float speed)
    {
        this.speedX = speed;
    }

    public void setSpeedY(float speed)
    {
        this.speedY = speed;
    }

    public void dealDamageGeneric()
    {
        if (!dead && !invulnerable && !shadow_dashing)
        {
            dealDamage();
            triggerScreenShake();
            setScreenShakeLength(Launcher.framerate_limit);
            setScreenShakeLevel(10);
            triggerDamageFreeze();
            setDamageShockFreezeLength(Launcher.framerate_limit / 3F);
            invulnerable = true;
            damage_shocked = true;
            benched = false;
            fall_shocked = false;
            illegal_superdash = true;
            illegal_heal = true;
            illegal_fireball = true;
            illegal_slash = true;
            can_dash = false;
            slashing = false;
            dashing = false;
        }
    }
}
