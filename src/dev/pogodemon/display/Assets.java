package dev.pogodemon.display;

import java.awt.image.BufferedImage;

public class Assets
{
    public static BufferedImage player_right, player_left, tile_collision, tile_empty, grass, husk_right, husk_left, mask_full, mask_empty
            , soul_vessel_hud, dash_right, dash_left, walk_right, walk_left, jump_right, jump_left, fall_right, fall_left, fall_shock_right, fall_shock_left;

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
    }
}
