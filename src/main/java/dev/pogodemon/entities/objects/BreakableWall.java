package dev.pogodemon.entities.objects;

import dev.pogodemon.GameFlags;
import dev.pogodemon.entities.Player;
import dev.pogodemon.entities.StaticEntity;
import dev.pogodemon.entities.particles.ParticleEnemyHit;
import dev.pogodemon.utils.Handler;

public abstract class BreakableWall extends StaticEntity
{
    private final String save_file_entry;

    public BreakableWall(Handler handler, float x, float y, float width, float height, int health, String save_file_entry)
    {
        super(handler, x, y, width, height);
        this.health = health;
        setSolid(true);
        setClimbable(true);
        this.save_file_entry = save_file_entry;
    }

    @Override
    public void update()
    {
        if (was_just_attacked && !handler.getWorld().getEntityManager().getPlayer().slashing)
            was_just_attacked = false;

        if (health <= 0)
        {
            handler.getWorld().removeEntity(this);
            GameFlags.data.updateValue(save_file_entry, true);
            GameFlags.data.updateLocalFile();
        }
    }

    @Override
    public void hasBeenHit()
    {
        if (!was_just_attacked)
        {
            was_just_attacked = true;
            health--;
            Player p = handler.getWorld().getEntityManager().getPlayer();
            float yy = (p.up_slashing || p.down_slashing) ? getCenterY() : p.getCenterY();
            handler.getWorld().spawnEntity(new ParticleEnemyHit(handler, getCenterX(), yy));
        }
    }

    @Override
    public void playerContact()
    {

    }
}
