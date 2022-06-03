package dev.pogodemon.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
    private final boolean[] keys;
    /*
    debug tools:
    k -> kill player
    j -> set spawn point
     */
    public boolean up, down, right, left, esc, z, x, c, s, a, f, k, j;
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
        esc = keys[KeyEvent.VK_ESCAPE];
        z = keys[KeyEvent.VK_Z];
        x = keys[KeyEvent.VK_X];
        c = keys[KeyEvent.VK_C];
        s = keys[KeyEvent.VK_S];
        a = keys[KeyEvent.VK_A];
        f = keys[KeyEvent.VK_F];
        k = keys[KeyEvent.VK_K];
        j = keys[KeyEvent.VK_J];
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
