package io.felux.items.listener;

import io.felux.items.api.FeluxItem;
import io.felux.items.hook.WorldGuardHook;
import io.felux.items.util.Lang;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    @EventHandler
    public void onPouchInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();

        if (itemStack == null || itemStack.getType() == Material.AIR) return;

        FeluxItem item = FeluxItem.getItem(itemStack);
        if (item == null) return;

        e.setCancelled(true);

        if (!Bukkit.getVersion().contains("1.8")) {
            if (e.getHand() == EquipmentSlot.OFF_HAND)
                return;
        }

        if (!item.hasPermission(player)) {
            Lang.ERROR_NO_PERMISSION_ITEM.send(player, Lang.PREFIX.asString(), item.getId().toLowerCase());
            return;
        }

        if (item.getBlacklistedWorlds().contains(player.getWorld().getName())) {
            Lang.CANNOT_USE_IN_WORLD.send(player, Lang.PREFIX.asString());
            return;
        }

        if (WorldGuardHook.isEnabled()) {
            for (final String region : item.getBlacklistedRegions()) {
                if (WorldGuardHook.checkIfPlayerInRegion(player, region)) {
                    Lang.CANNOT_USE_IN_REGION.send(player, Lang.PREFIX.asString());
                    return;
                }
            }
        }

        if (itemStack.getAmount() == 1) player.setItemInHand(null);
        else player.getItemInHand().setAmount(itemStack.getAmount() - 1);

        item.runCommands(player);
    }
}
