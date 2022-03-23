package dev.pogodemon.items.skills;

import dev.pogodemon.display.Assets;
import dev.pogodemon.items.Item;

import java.awt.image.BufferedImage;

public class ShadeCloak  extends Item
{
    @Override
    public int type()
    {
        return 0;
    }

    @Override
    public String name()
    {
        return "Shade Cloak";
    }

    @Override
    public BufferedImage icon()
    {
        return Assets.shade_cloak;
    }
}
