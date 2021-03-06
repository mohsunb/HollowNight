package dev.pogodemon.entities.objects;

import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.world.Tile;

import java.awt.*;

public class Grass extends StaticEntity
{
    public Grass(Handler handler, float x, float y)
    {
        super(handler, x, y, Tile.SIZE, Tile.SIZE * 2);
        can_be_killed = false;
    }

    @Override
    public void update()
    {

    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        gfx.drawImage(Assets.grass, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), (int) width, (int) height, null);

        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.blue);
            gfx.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        }
    }

    @Override
    public void hasBeenHit()
    {

    }

    @Override
    public void fireballHit() {

    }

    @Override
    public void playerContact() {

    }
}
