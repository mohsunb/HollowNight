package dev.pogodemon.items;

import java.awt.image.BufferedImage;

public abstract class Item
{
    public static final int SKILL = 0;
    public static final int CHARM = 1;
    public static final int ITEM = 2;
    public static final int ARTIFACT = 3;

    public abstract int type();
    public abstract String name();
    public abstract BufferedImage icon();
}
