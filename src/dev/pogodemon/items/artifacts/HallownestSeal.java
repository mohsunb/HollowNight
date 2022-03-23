package dev.pogodemon.items.artifacts;

import dev.pogodemon.display.Assets;
import dev.pogodemon.items.Item;

import java.awt.image.BufferedImage;

public class HallownestSeal  extends Item
{
    @Override
    public int type()
    {
        return 3;
    }

    @Override
    public String name()
    {
        return "Hallownest Seal";
    }

    @Override
    public BufferedImage icon()
    {
        return Assets.hallownest_seal;
    }
}
