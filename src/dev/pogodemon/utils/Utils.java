package dev.pogodemon.utils;

<<<<<<< HEAD
=======
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
>>>>>>> 6aee207 (v0.3.6)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils
{
    public static String loadFileAsString(String path)
    {
        StringBuilder builder = new StringBuilder();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null)
                builder.append(line + "\n");

            reader.close();
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static int parseInt(String number)
    {
        try
        {
            return Integer.parseInt(number);
        }

        catch(NumberFormatException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
<<<<<<< HEAD
=======

    public static void setOpacity(Graphics2D gfx, float opacity)
    {
        gfx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    }

    public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle)
    {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2F, (newHeight - h) / 2F);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

    public static BufferedImage mirrorImage(BufferedImage img)
    {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);

        return img;
    }
>>>>>>> 6aee207 (v0.3.6)
}
