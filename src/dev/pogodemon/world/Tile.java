package dev.pogodemon.world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile
{
    public static final int SIZE = 40;

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

    public void render(Graphics2D gfx, int x, int y)
    {
        gfx.drawImage(texture, x, y, SIZE, SIZE, null);
    }

    public int getId()
    {
        return id;
    }
}
