package dev.pogodemon.display;

import java.awt.image.BufferedImage;

public class Animation
{
    private final int speed;
    private int index;
    private long lastTime, timer;
    private final BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames)
    {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update()
    {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > speed)
        {
            index++;
            timer = 0;
            if (index >= frames.length)
                index = 0;
        }
    }

    public void reset()
    {
        if (index != 0)
            index = 0;
    }

    public BufferedImage getCurrentFrame()
    {
        return frames[index];
    }

    public int getIndex()
    {
        return index;
    }

    public int getLength()
    {
        return frames.length;
    }

    public boolean lastFrame()
    {
        return index == frames.length - 1;
    }
}
