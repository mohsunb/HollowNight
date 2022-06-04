package dev.pogodemon.entities.objects;

import dev.pogodemon.GameFlags;
import dev.pogodemon.Launcher;
import dev.pogodemon.display.Assets;
import dev.pogodemon.states.GameState;
import dev.pogodemon.states.State;
import dev.pogodemon.utils.Coordinate;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.MapHelper;
import dev.pogodemon.world.World;

import java.awt.*;

public class BreakableWallDirtmouthEntrance extends BreakableWall
{
    public BreakableWallDirtmouthEntrance(Handler handler, float x, float y, float width, float height)
    {
        super(handler, x, y, width, height, 13, null);
        setClimbable(false);
    }

    @Override
    public void update()
    {
        if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
            was_just_attacked = false;

        if (health <= 0)
        {
            GameState state = (GameState) State.getState();
            state.bufferPlayerState(1);
            state.triggerKingsPassExit();
            GameFlags.dirtmouth_entrance_opened = true;
            GameFlags.data.updateValue("dirtmouth_entrance_opened", true);
            GameFlags.load_coordinates = new Coordinate(480, 1632);
            GameFlags.load_state = World.SLEEPING;
            GameFlags.load_map_id = MapHelper.DIRTMOUTH;
            GameFlags.data.updateValue("load_coordinates_x", 480);
            GameFlags.data.updateValue("load_coordinates_y", 1632);
            GameFlags.data.updateValue("load_state", World.SLEEPING);
            GameFlags.data.updateValue("load_map_id", MapHelper.DIRTMOUTH);
            GameFlags.data.updateValue("geo", handler.getWorld().getEntityManager().getPlayer().getGeo());
            GameFlags.data.updateLocalFile();
        }
    }

    @Override
    public int renderRank()
    {
        return 0;
    }

    @Override
    public void render(Graphics2D gfx)
    {
        if (exists)
        {
            if (health > 9)
                gfx.drawImage(Assets.dirtmouth_entrance_1, (int) (x - handler.getCamera().getxOffset() - 250), (int) (y - handler.getCamera().getyOffset() - 100), null);
            else if (health > 5)
                gfx.drawImage(Assets.dirtmouth_entrance_2, (int) (x - handler.getCamera().getxOffset() - 250), (int) (y - handler.getCamera().getyOffset() - 100), null);
            else
                gfx.drawImage(Assets.dirtmouth_entrance_3, (int) (x - handler.getCamera().getxOffset() - 250), (int) (y - handler.getCamera().getyOffset() - 100), null);

            if (Launcher.show_hitboxes)
            {
                gfx.setColor(Color.blue);
                gfx.drawRect((int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
            }
        }
    }

    @Override
    public void fireballHit()
    {

    }
}
