package dev.pogodemon.world;

import dev.pogodemon.Launcher;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile
{
    public static final int TILE_WIDTH = Launcher.game_width / 48, TILE_HEIGHT = Launcher.game_height / 27;

    public static Tile[] tiles = new Tile[10];
    public static Tile air = new TileEmpty(0);
    public static Tile box = new TileCollision(1);

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public boolean isSolid()
    {
        return false;
    }

    public void update()
    {

    }

    public void render(Graphics gfx, int x, int y)
    {
        gfx.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public int getId()
    {
        return id;
    }
}
