package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.display.SpriteSheet;
import dev.pogodemon.items.Item;
import dev.pogodemon.items.Items;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.ArrayList;

public class Player extends Creature
{
    private boolean screen_shake_triggered = false;
    private int screen_shake_counter = 0;
    private boolean damage_shock_freeze_triggered = false;
    private int damage_shock_freeze_timer = 0;

    private int max_health;
    private int geo = 100;
    private int geo_buffer = 0;
    private boolean has_buffered_geo;
    private int geo_buffer_timer;

    private int heal_buffer_timer = 0;
    private boolean healing = false;
    private int heal_timer = 0;
    private boolean post_heal = false;
    private int post_heal_timer = 0;
    private boolean illegal_heal = false;
    private int heal_counter = 0;
    private boolean can_heal = false;

    private float soul = 0;
    private float max_soul = 99;

    private boolean fireball_knockback = false;
    private int fireball_knockback_timer = 0;
    private boolean fireball_blocked = false;
    private int fireball_cooldown_timer = 0;
    private boolean illegal_fireball = false;
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
    private int itemDisplayState = 0;
    private Item latestItemDisplayed = null;
    private boolean itemDisplayed = false;
    private float itemDisplayCounter = 0;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_WIDTH * 0.75f, Creature.DEFAULT_HEIGHT * 1.625f);

        bounds.x = 6;
        bounds.y = 40;
        bounds.width = 44;
        bounds.height = 87;
        health = 100;
        max_health = health;
        can_be_killed = false;

        CREATURE_TYPE = 0; //Player

        /*giveItem(Items.mothwingCloak);
        giveItem(Items.shadeCloak);
        giveItem(Items.mantisClaw);
        giveItem(Items.crystalHeart);
        giveItem(Items.monarchWings);*/
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
    private boolean illegal_slash = false;
    public boolean down_slashing = false;
    public boolean up_slashing = false;
    public boolean attack_knockback = false;
    public boolean just_knocked_back = false;
    public boolean pogo = false;
    long attack_knockback_timer = 0;
    long pogo_timer = 0;
    long invulnerable_timer = 0;
    long damage_shock_timer = 0;
    long slash_cooldown_timer = 0;
    long slash_timer = 0;

    public boolean hazard_triggered = false;
    private int hazard_timer = 0;

    long superdash_shock_timer = 0;
    private boolean superdash_charged = false;
    long superdash_charge_timer = 0;
    private boolean illegal_superdash = false;

    private float respawnX = 0;
    private float respawnY = 0;

    public void hazardRespawn()
    {
        hazard_triggered = true;

        if (!damage_shocked)
            damage_shocked = true;

        damage_shocked_right = !isFacingRight();
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
        if (screen_shake_triggered && screen_shake_counter++ >= 1)
        {
            screen_shake_counter = 0;
            screen_shake_triggered = false;
        }

        if (damage_shock_freeze_triggered && damage_shock_freeze_timer++ >= 1)
        {
            damage_shock_freeze_timer = 0;
            damage_shock_freeze_triggered = false;
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
                int tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH);
                boolean bool = collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.TILE_HEIGHT))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.TILE_HEIGHT))
                    || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)) || checkEntityMoveCollisions(xMove + 2, 0);
                if (bool)
                {
                    superdash = false;
                    superdash_shocked = true;
                }

            }

            else if (xMove < 0)
            {
                int tx = (int) Math.floor((x + xMove + bounds.x) / Tile.TILE_WIDTH);
                boolean bool = collisionWithTile(tx, (int) Math.floor((y + bounds.y) / Tile.TILE_HEIGHT))
                        || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
                        || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.5) / Tile.TILE_HEIGHT))
                        || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.25) / Tile.TILE_HEIGHT))
                        || collisionWithTile(tx, (int) Math.floor((y + bounds.y + bounds.height * 0.75) / Tile.TILE_HEIGHT)) || checkEntityMoveCollisions(xMove - 2, 0);
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

        if (health <= 0)
            handler.getKeyManager().q = true;

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
        move();

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
            if (-speedY > DEFAULT_SPEED * 2 * 0.25 || yMove > DEFAULT_SPEED * 2 * 0.25)
            {
                yMove = 0;
                if (superdash_shocked)
                    speedY = 0;
                else
                    speedY = DEFAULT_SPEED * 2 * 0.25f;
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
            hazard_timer++;
            if (hazard_timer >= Launcher.framerate_limit)
            {
                hazard_timer = 0;
                hazard_triggered = false;
                xMove = 0;
                yMove = 0;
                setX(respawnX);
                setY(respawnY);
                handler.getWorld().getEntityManager().getPlayerCamera().clearCameraQueue();
                health -= 20;
                triggerScreenShake();
                invulnerable = true;
                fall_shocked = true;
                dashing = false;
                shadow_dashing = false;
                superdash = false;
                pogo = false;
                damage_shocked = false;
            }
        }
    }

    long dash_length_timer = 0;
    long dash_cooldown_timer = 0;
    long fall_shock_timer = 0;
    long wall_jump_force_horizontal_move_timer = 0;

    private void getInput()
    {
        if (item_pickup || damage_shocked || fall_shocked)
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

        if ((((heal_buffer_timer > 0 && !post_heal && !healing) || illegal_heal) && heal_button_fireball && !handler.getKeyManager().a) || (!illegal_fireball && !fireball_blocked && !dashing && !superdash && superdash_charge_timer == 0 && !superdash_charged && !item_pickup && !fall_shocked && !damage_shocked && !post_heal && handler.getKeyManager().f))
        {
            if (Math.ceil(soul) >= 33 && (slash_timer == 0 || slash_timer >= Launcher.framerate_limit * 0.175F))
            {
                handler.getWorld().spawnEntity(new Fireball(handler, getCenterX(), getCenterY(), shamanStone, isFacingRight()));
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

        if (!fireball_knockback && can_heal && !illegal_heal && grounded && !dashing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !item_pickup && !fall_shocked && !damage_shocked && handler.getKeyManager().a)
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

        if (!fireball_knockback && !post_heal && !healing && !looking_up && grounded && !superdash_shocked && !damage_shocked && !item_pickup && !fall_shocked && xMove == 0 && handler.getKeyManager().up)
        {
            looking_up_timer++;
            if (looking_up_timer >= Launcher.framerate_limit * 0.5)
            {
                looking_up_timer = 0;
                looking_up = true;
            }
        }

        if (!fireball_knockback && !looking_down && grounded && !superdash_shocked && !damage_shocked && !item_pickup && !fall_shocked && xMove == 0 && handler.getKeyManager().down)
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

        if (superdash && minimum_superdash_timer >= Launcher.framerate_limit * 0.1 && (handler.getKeyManager().s || handler.getKeyManager().z))
        {
            superdash = false;
            superdash_shocked = true;
        }

        if (!fireball_knockback && !post_heal && !healing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !dashing && !item_pickup && !fall_shocked && !damage_shocked && !slashing && !slash_blocked && !illegal_slash && handler.getKeyManager().x)
        {
            slashing = true;
            alternative_slash_sprite = !alternative_slash_sprite;
        }

        if (slash_blocked && handler.getKeyManager().x)
            illegal_slash = true;

        if (!handler.getKeyManager().x)
            illegal_slash = false;

        //So that player doesn't stop during various events
        if (!fireball_knockback && !dashing && !superdash && !damage_shocked && !superdash_shocked && !attack_knockback)
            xMove = 0;
        if (!pogo)
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

            if (!can_dash_twice && grounded)
                can_dash_twice = true;
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

        if (!fireball_knockback && !item_pickup && !fall_shocked && !damage_shocked)
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
                    can_shadow_dash = false;
                    shadow_dash_cooldown = 1;
                }

                if (can_dash_twice)
                    can_dash_twice = false;

                if (attack_knockback)
                    attack_knockback = false;

                if (facing_right)
                    xMove += DEFAULT_SPEED * 2.51027861;
                else
                    xMove += -DEFAULT_SPEED * 2.51027861;

                speedY = 0;
            }

            //Double jumping
            if (grounded || cling_right || cling_left)
            {
                can_double_jump = false;
                illegal_double_jumping = false;
                did_double_jump = false;
            }

            if (hasMonarchWings && !grounded && !illegal_double_jumping && !handler.getKeyManager().z)
                can_double_jump = true;

            if (illegal_jumping && !handler.getKeyManager().z)
                illegal_double_jumping = false;

            if (!superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !grounded && !jumping && !did_double_jump && !illegal_double_jumping && can_double_jump && handler.getKeyManager().z)
            {
                double_jumping = true;
                illegal_double_jumping = true;
                did_double_jump = true;
                speedY = DEFAULT_SPEED * 2.5F; // double jump initial speed
                if (!dashing)
                    yMove += -speedY;
            }

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

            else if (jumping && handler.getKeyManager().z && (speedY - DEFAULT_SPEED * 0.0375 * 144 / Launcher.framerate_limit) > 0)
            {
                yMove += -speedY;
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

            else if (jumping && !handler.getKeyManager().z && speedY != 0)
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
                        t = (int) (DEFAULT_SPEED * 2 * 0.25);

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

            if (!post_heal && !healing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !dashing && !attack_knockback && handler.getKeyManager().right)
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

            if (!post_heal && !healing && !superdash_shocked && superdash_charge_timer == 0 && !superdash_charged && !superdash && !dashing && !attack_knockback && handler.getKeyManager().left)
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

        //Quit game (for quick debugging)
        if (handler.getKeyManager().q)
            System.exit(0);
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (damageFreezeTriggered())
            gfx.drawImage(Assets.damage, (int) (x - handler.getCamera().getxOffset() - 650), (int) (y - handler.getCamera().getyOffset() - 50), null);

        //Coordinates
        gfx.setColor(Color.white);
        gfx.drawString("X: " + (int) getX() + "  Y: " + (int) getY(), 5, 15);

        if (itemDisplayed)
        {
            if (itemDisplayState == 0)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, itemDisplayCounter / (Launcher.framerate_limit / 3F)));
            else if (itemDisplayState == 1)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
            else if (itemDisplayState == 2)
                gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F - itemDisplayCounter / (Launcher.framerate_limit / 6F)));

            gfx.drawImage(latestItemDisplayed.icon(), 150 - (int) (latestItemDisplayed.icon().getWidth() * 0.5F), Launcher.game_height - 150 - (int) (latestItemDisplayed.icon().getHeight() * 0.5F), null);
            gfx.setFont(new Font("Arial", Font.PLAIN, 45));
            gfx.drawString(latestItemDisplayed.name(), 170 + (int) (latestItemDisplayed.icon().getWidth() * 0.5F), Launcher.game_height - 135);
            gfx.setFont(new Font("Arial", Font.PLAIN, 12));
            gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
        }

        //Render player
        if (cling_left)
        {
            if (superdash_charge_timer > 0)
            {
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
            if (item_pickup)
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
                BufferedImage out = new BufferedImage(Assets.heal_right.getWidth(), Assets.heal_right.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                RescaleOp rescaleOp = new RescaleOp(1f, 255 * heal_timer / (Launcher.framerate_limit * 0.891F), null);
                rescaleOp.filter(Assets.heal_right, out);
                gfx.drawImage(out, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 5), null);
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
                    gfx.drawImage(Assets.shadow_dash_right, (int) (x - handler.getCamera().getxOffset() - 730), (int) (y - handler.getCamera().getyOffset() - 110), null);

                else
                    gfx.drawImage(blackSprite(Assets.dash_right), (int) (x - handler.getCamera().getxOffset() - 4 - 111), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (superdash_charge_timer > 0)
            {
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
                gfx.drawImage(Assets.superdash_right, (int) (x - handler.getCamera().getxOffset() - 710), (int) (y - handler.getCamera().getyOffset() - 150), null);
            }

            else if (slashing)
            {
                if (up_slashing)
                {
                    gfx.drawImage(blackSprite(Assets.upslash_char_right), (int) (x - handler.getCamera().getxOffset() - 43), (int) (y - handler.getCamera().getyOffset() + 30), null);
                }

                else if (down_slashing)
                {
                    gfx.drawImage(blackSprite(Assets.downslash_char_right), (int) (x - handler.getCamera().getxOffset() - 55), (int) (y - handler.getCamera().getyOffset() + 8), null);
                }

                else if (alternative_slash_sprite)
                {
                    gfx.drawImage(blackSprite(Assets.slash1_char_right), (int) (x - handler.getCamera().getxOffset() - 35), (int) (y - handler.getCamera().getyOffset()), null);
                }
                else
                {
                    gfx.drawImage(blackSprite(Assets.slash2_char_right), (int) (x - handler.getCamera().getxOffset() - 35), (int) (y - handler.getCamera().getyOffset()), null);
                }
            }

            else if (grounded)
            {
                if (xMove == 0)
                    gfx.drawImage(blackSprite(Assets.player_right), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);

                else
                    gfx.drawImage(blackSprite(Assets.walk_right), (int) (x - handler.getCamera().getxOffset() - 4 - 7), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (double_jumping)
                gfx.drawImage(Assets.wings_right, (int) (x - handler.getCamera().getxOffset() - 140), (int) (y - handler.getCamera().getyOffset() - 20), null);

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
            if (item_pickup)
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
                BufferedImage out = new BufferedImage(Assets.heal_left.getWidth(), Assets.heal_left.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                RescaleOp rescaleOp = new RescaleOp(1f, 255 * heal_timer / (Launcher.framerate_limit * 0.891F), null);
                rescaleOp.filter(Assets.heal_left, out);
                gfx.drawImage(out, (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 5), null);
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
                    gfx.drawImage(Assets.shadow_dash_left, (int) (x - handler.getCamera().getxOffset() - 15), (int) (y - handler.getCamera().getyOffset() - 110), null);
                else
                    gfx.drawImage(blackSprite(Assets.dash_left), (int) (x - handler.getCamera().getxOffset() - 4 + 1), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (superdash_charge_timer > 0)
            {
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
                gfx.drawImage(Assets.superdash_left, (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() - 150), null);

            else if (slashing)
            {
                if (up_slashing)
                {
                    gfx.drawImage(blackSprite(Assets.upslash_char_left), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset() + 30), null);
                }

                else if (down_slashing)
                {
                    gfx.drawImage(blackSprite(Assets.downslash_char_left), (int) (x - handler.getCamera().getxOffset() - 10), (int) (y - handler.getCamera().getyOffset() + 8), null);
                }

                else if (alternative_slash_sprite)
                {
                    gfx.drawImage(blackSprite(Assets.slash1_char_left), (int) (x - handler.getCamera().getxOffset() - 11), (int) (y - handler.getCamera().getyOffset()), null);
                }
                else
                {
                    gfx.drawImage(blackSprite(Assets.slash2_char_left), (int) (x - handler.getCamera().getxOffset() - 8), (int) (y - handler.getCamera().getyOffset()), null);
                }
            }

            else if (grounded)
            {
                if (xMove == 0)
                    gfx.drawImage(blackSprite(Assets.player_left), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);

                else
                    gfx.drawImage(blackSprite(Assets.walk_left), (int) (x - handler.getCamera().getxOffset() - 4), (int) (y - handler.getCamera().getyOffset()), null);
            }

            else if (double_jumping)
                gfx.drawImage(Assets.wings_left, (int) (x - handler.getCamera().getxOffset() - 80), (int) (y - handler.getCamera().getyOffset() - 20), null);

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

        //Render HUD

        //Soul
        gfx.drawImage(Assets.soul_vessel_hud_up, 90, 80, null);
        if (Math.ceil(soul) >= 33)
            gfx.setColor(Color.white);
        else
            gfx.setColor(Color.lightGray);
        gfx.drawImage(Assets.soul_vessel_hud_down, 90, 80, null);
        gfx.fillOval(105, 110, 103, 103);
        if (soul < max_soul)
            gfx.drawImage(new SpriteSheet(Assets.soul_vessel_hud_down).crop(14, 30, 106, (int) (105 - Math.floor(105D / max_soul * soul))), 104, 110, null);
        if (soul >= 55)
            gfx.drawImage(Assets.soul_vessel_hud_middle, 115, 165, null);

        //Geo
        gfx.drawImage(Assets.geo_hud, 223, 165, null);
        gfx.setColor(Color.white);
        gfx.setFont(new Font("Arial", Font.PLAIN, 45));
        gfx.drawString(Integer.toString(geo), 290, 220);
        if (has_buffered_geo)
            gfx.drawString((geo_buffer > 0 ? "+" : " -") + Math.abs(geo_buffer), 290, 270);

        //Masks
        for (int i = 0; i < max_health / 20; i++)
            gfx.drawImage(Assets.mask_empty, 60 * i + 235, 105, null);

        for (int i = 0; i < health / 20; i++)
            gfx.drawImage(Assets.mask_full, 60 * i + 235, 103, null);
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
}
