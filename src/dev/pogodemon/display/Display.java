package dev.pogodemon.display;

import dev.pogodemon.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Display
{
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            cursorImg, new Point(0, 0), "blank cursor");

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;
    private final boolean full_screen;

    public Display(String title, int width, int height, boolean full_screen)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        this.full_screen = full_screen;
        createDisplay();
    }

    private void createDisplay()
    {
        frame = new JFrame(title);
        frame.setUndecorated(full_screen);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getClass().getResource("../assets/textures/ui/icon_256x256.png")).getImage());

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
        if (Launcher.hide_cursor)
            frame.getContentPane().setCursor(blankCursor);
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public JFrame getFrame()
    {
        return frame;
    }
}
