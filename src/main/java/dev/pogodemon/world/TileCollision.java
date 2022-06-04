package dev.pogodemon.world;

import dev.pogodemon.display.Assets;

public class TileCollision extends Tile
{

    public TileCollision(int id)
    {
        super(Assets.tile_collision, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
