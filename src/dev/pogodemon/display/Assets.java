package dev.pogodemon.display;

import java.awt.image.BufferedImage;

public class Assets
{
    public static BufferedImage player_right, player_left, tile_collision, tile_empty, grass, husk_right, husk_left, mask_full, mask_empty
            , soul_vessel_hud, dash_right, dash_left, walk_right, walk_left, jump_right, jump_left, fall_right, fall_left, fall_shock_right
            , fall_shock_left, cling_right, cling_left, slash1_right, slash1_left, slash2_right, slash2_left, upslash_right, upslash_left
            , downslash_right, downslash_left, upslash_char_right, upslash_char_left, downslash_char_right, downslash_char_left
            , slash1_char_left, slash1_char_right, slash2_char_right, slash2_char_left, spikes_small_up, spikes_small_right
            , spikes_small_down, spikes_small_left, spikes_medium_up, spikes_medium_right, spikes_medium_down, spikes_medium_left
            , spikes_large_up, spikes_large_down, spikes_large_left, spikes_large_right, damage_shock_right, damage_shock_left;

    public static void init()
    {
        player_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player_right.png");
        player_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player_left.png");
        tile_collision = ImageLoader.loadImage("/dev/pogodemon/assets/textures/tile_collision.png");
        tile_empty = ImageLoader.loadImage("/dev/pogodemon/assets/textures/tile_empty.png");
        grass = ImageLoader.loadImage("/dev/pogodemon/assets/textures/grass.png");
        husk_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/husk_right.png");
        husk_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/husk_left.png");
        mask_full = ImageLoader.loadImage("/dev/pogodemon/assets/textures/mask_full.png");
        mask_empty = ImageLoader.loadImage("/dev/pogodemon/assets/textures/mask_empty.png");
        soul_vessel_hud = ImageLoader.loadImage("/dev/pogodemon/assets/textures/soul_vessel_hud.png");
        dash_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/dash_right.png");
        dash_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/dash_left.png");
        walk_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/walk_right.png");
        walk_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/walk_left.png");
        jump_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/jump_right.png");
        jump_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/jump_left.png");
        fall_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/fall_right.png");
        fall_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/fall_left.png");
        fall_shock_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/fall_shock_right.png");
        fall_shock_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/fall_shock_left.png");
        cling_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/cling_right.png");
        cling_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/cling_left.png");
        slash1_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/slash1_right.png");
        slash1_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/slash1_left.png");
        slash2_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/slash2_right.png");
        slash2_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/slash2_left.png");
        upslash_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/upslash_right.png");
        upslash_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/upslash_left.png");
        downslash_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/downslash_right.png");
        downslash_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/downslash_left.png");
        upslash_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/upslash_char_right.png");
        upslash_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/upslash_char_left.png");
        downslash_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/downslash_char_right.png");
        downslash_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/downslash_char_left.png");
        slash1_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/slash1_char_left.png");
        slash1_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/slash1_char_right.png");
        slash2_char_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/slash2_char_right.png");
        slash2_char_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/slash2_char_left.png");
        spikes_small_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_small_up.png");
        spikes_small_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_small_down.png");
        spikes_small_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_small_left.png");
        spikes_small_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_small_right.png");
        spikes_medium_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_medium_up.png");
        spikes_medium_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_medium_down.png");
        spikes_medium_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_medium_left.png");
        spikes_medium_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_medium_right.png");
        spikes_large_up = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_large_up.png");
        spikes_large_down = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_large_down.png");
        spikes_large_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_large_left.png");
        spikes_large_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/spikes_large_right.png");
        damage_shock_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/damage_shock_right.png");
        damage_shock_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/damage_shock_left.png");
    }
}
