package dev.pogodemon.items.artifacts;

import dev.pogodemon.display.Assets;
import dev.pogodemon.items.Item;

import java.awt.image.BufferedImage;

public class WanderersJournal  extends Item
{
    @Override
    public int type()
    {
        return 3;
    }

    @Override
    public String name()
    {
        return "Wanderer's Journal";
    }

    @Override
    public BufferedImage icon()
    {
        return Assets.wanderers_journal;
    }
}
