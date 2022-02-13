package dev.pogodemon.display;

import java.awt.image.BufferedImage;

public class Assets
{
    public static BufferedImage player_right, player_left, tile_collision, tile_empty, grass, husk_right, husk_left;

    public static void init()
    {
        player_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player_right.png");
        player_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/player_left.png");
        tile_collision = ImageLoader.loadImage("/dev/pogodemon/assets/textures/tile_collision.png");
        tile_empty = ImageLoader.loadImage("/dev/pogodemon/assets/textures/tile_empty.png");
        grass = ImageLoader.loadImage("/dev/pogodemon/assets/textures/grass.png");
        husk_right = ImageLoader.loadImage("/dev/pogodemon/assets/textures/husk_right.png");
        husk_left = ImageLoader.loadImage("/dev/pogodemon/assets/textures/husk_left.png");
    }
}
