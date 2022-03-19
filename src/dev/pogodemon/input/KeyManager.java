package dev.pogodemon.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
    private boolean[] keys;
    public boolean up, down, right, left, q, z, x, c, s, a, f;
    public KeyManager()
    {
        keys = new boolean[256];
    }

    public void update()
    {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        right = keys[KeyEvent.VK_RIGHT];
        left = keys[KeyEvent.VK_LEFT];
        q = keys[KeyEvent.VK_Q];
        z = keys[KeyEvent.VK_Z];
        x = keys[KeyEvent.VK_X];
        c = keys[KeyEvent.VK_C];
        s = keys[KeyEvent.VK_S];
        a = keys[KeyEvent.VK_A];
        f = keys[KeyEvent.VK_F];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
