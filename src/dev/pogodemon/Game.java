package dev.pogodemon;

import dev.pogodemon.display.Assets;
import dev.pogodemon.display.Camera;
import dev.pogodemon.display.Display;
import dev.pogodemon.input.KeyManager;
import dev.pogodemon.states.GameState;
import dev.pogodemon.states.State;
import dev.pogodemon.utils.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable
{
    private Display display;

    private Thread thread;

    private boolean running = false;
    private final boolean full_screen;

    private BufferStrategy buffer;
    private Graphics gfx;

    private State gameState;
    private KeyManager keyManager;
    private Camera camera;
    private Handler handler;

    public String title;
    private int width, height;

    public Game(String title, int width, int height, boolean full_screen)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        this.full_screen = full_screen;
        keyManager = new KeyManager();
    }

    private void init()
    {
        display = new Display(title, width, height, full_screen);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        camera = new Camera(handler,0, 0);

        gameState = new GameState(handler);
        State.setState(gameState);
    }

    private void update()
    {
        keyManager.update();

        if (State.getState() != null)
            State.getState().update();
    }

    private void render()
    {
        buffer = display.getCanvas().getBufferStrategy();

        if (buffer == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        gfx = buffer.getDrawGraphics();

        //Clear the windows
        gfx.clearRect(0, 0, width, height);

        //Assets begin here

        if (State.getState() != null)
            State.getState().render(gfx);

        //Assets end here

        buffer.show();
        gfx.dispose();
    }

    public void run()
    {
        init();

        double timePerTick = 1e9D / Launcher.framerate_limit;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) /timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
                update();
                render();
                ticks++;
                delta--;
            }

            //FPS counter
            if (timer >= 1_000_000_000)
            {
                if (Launcher.log_fps)
                    System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start()
    {
        if (running)
            return;

        running = true;

        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if (!running)
            return;

        running = false;

        try
        {
            thread.join();
        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
