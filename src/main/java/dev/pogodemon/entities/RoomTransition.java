package dev.pogodemon.entities;

import dev.pogodemon.Launcher;
import dev.pogodemon.states.GameState;
import dev.pogodemon.states.State;
import dev.pogodemon.utils.Handler;
import dev.pogodemon.utils.Utils;
import dev.pogodemon.world.World;
import dev.pogodemon.world.EntityData;

import java.awt.*;

public class RoomTransition extends StaticEntity
{
    private final World world;
    private final EntityData data;
    private final int direction;

    private boolean bool = false;
    private int i = 0;

    public RoomTransition(Handler handler, float x, float y, float width, float height, World world, EntityData data, int direction)
    {
        super(handler, x, y, width, height);
        this.world = world;
        this.data = data;
        has_knockback = false;
        this.direction = direction;
    }

    @Override
    public void update()
    {
        if (bool)
        {
            if (i++ >= Launcher.framerate_limit * (1.4 + 1F/3))
            {
                bool = false;
                i = 0;
                handler.setWorld(world);
                data.spawnEntities();
                GameState state = (GameState) State.getState();
                state.updatePlayerState();
                handler.getWorld().getEntityManager().getPlayer().room_transitioning = true;
            }
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
        if (Launcher.show_hitboxes)
        {
            gfx.setColor(Color.pink);
            Utils.setOpacity(gfx, 0.5F);
            gfx.fillRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
            Utils.setOpacity(gfx, 1);
        }
    }

    @Override
    public void hasBeenHit()
    {

    }

    @Override
    public void fireballHit()
    {

    }

    @Override
    public void playerContact()
    {
        if (!handler.getWorld().getEntityManager().getPlayer().room_transitioning)
        {
            handler.getWorld().getEntityManager().getPlayer().room_transitioning = true;
            if (direction == 0 || direction == -1 || direction == -3)
            {
                handler.getWorld().getEntityManager().getPlayer().pre_upward_room_transitioning = true;
                handler.getWorld().getEntityManager().getPlayer().rt_temp_yMove = handler.getWorld().getEntityManager().getPlayer().getyMove();
            }
            else if (direction == 1 || direction == 3)
                handler.getWorld().getEntityManager().getPlayer().horizontal_room_transitioning = true;
            else if (direction == 2)
                handler.getWorld().getEntityManager().getPlayer().setxMove(0);
            GameState state = (GameState) State.getState();
            state.triggerRoomTransitionEffect();
            state.bufferPlayerState(direction);
            bool = true;
        }
    }
}
