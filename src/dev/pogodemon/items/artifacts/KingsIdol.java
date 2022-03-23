package dev.pogodemon.items.artifacts;

import dev.pogodemon.display.Assets;
import dev.pogodemon.items.Item;

import java.awt.image.BufferedImage;

public class KingsIdol  extends Item
{
    @Override
    public int type()
    {
        return 3;
    }

    @Override
    public String name()
    {
        return "King's Idol";
    }

    @Override
    public BufferedImage icon()
    {
        return Assets.kings_idol;
    }
}
