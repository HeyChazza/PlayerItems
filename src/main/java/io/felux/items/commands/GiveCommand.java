package io.felux.items.commands;

import io.felux.items.FeluxItems;
import io.felux.items.api.FeluxItem;
import io.felux.items.util.Lang;
import io.felux.lib.api.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand {
    @Command(aliases = {"give"}, about = "Give a player an item.", permission = "feluxitems.give", usage = "give <player> <item> [amount]", requiredArgs = 2)
    public static void execute(final CommandSender sender, final FeluxItems plugin, final String[] args) {
        final int amount = args.length > 2 ? Integer.parseInt(args[2]) : 1;

        if (plugin.getItemManager().getItems().isEmpty()) {
            Lang.ERROR_NO_ITEMS_EXIST.send(sender, Lang.PREFIX.asString());
            return;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            Lang.ERROR_INVALID_PLAYER.send(sender, Lang.PREFIX.asString());
            return;
        }

        final FeluxItem pouch = FeluxItems.getInstance().getItemManager().getItem(args[1]);
        if (pouch == null) {
            Lang.ERROR_INVALID_ITEM.send(sender, Lang.PREFIX.asString());
            return;
        }
        ItemStack itemStack = pouch.getItem().clone();
        itemStack.setAmount(amount);
        target.getInventory().addItem(itemStack);

        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (player.getUniqueId() == target.getUniqueId()) {
                // Self
                Lang.GIVE_COMMAND_SELF.send(sender, Lang.PREFIX.asString(), pouch.getId(), amount);
            } else {
                // Another player
                Lang.GIVE_COMMAND_OTHER.send(sender, Lang.PREFIX.asString(), target.getName(), pouch.getId(), amount);
            }
            return;
        }

        // Console
        Lang.GIVE_COMMAND_OTHER.send(sender, Lang.PREFIX.asString(), target.getName(), pouch.getId(), amount);
    }
}
