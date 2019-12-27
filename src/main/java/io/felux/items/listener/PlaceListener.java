package io.felux.items.listener;

import io.felux.items.api.FeluxItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceListener implements Listener {

    @EventHandler
    public void onPouchInteract(BlockPlaceEvent e) {
        if (FeluxItem.getItem(e.getItemInHand()) != null) e.setCancelled(true);
    }
}
