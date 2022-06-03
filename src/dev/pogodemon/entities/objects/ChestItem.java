package dev.pogodemon.entities.objects;

import dev.pogodemon.entities.InteractableItem;
import dev.pogodemon.items.Item;
import dev.pogodemon.utils.Handler;

public class ChestItem extends Chest
{
    private Item item;
    public ChestItem(Handler handler, float x, float y, Item item, String entry)
    {
        super(handler, x, y, entry);
        this.item = item;
    }

    @Override
    protected void open()
    {
        handler.getWorld().spawnEntity(new InteractableItem(handler, getCenterX(), getCenterY() - bounds.height, true, item));
    }

    @Override
    public void fireballHit()
    {

    }
}
