package io.felux.items.commands;

import io.felux.items.FeluxItems;
import io.felux.items.api.FeluxItem;
import io.felux.items.util.Lang;
import io.felux.lib.api.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ListCommand {
    @Command(aliases = {"list"}, about = "List loaded items.", permission = "feluxitems.list", usage = "list")
    public static void execute(final CommandSender sender, final FeluxItems plugin, final String[] args) {
        List<FeluxItem> pouchList = plugin.getItemManager().getItems();

        if (pouchList.isEmpty()) {
            Lang.ERROR_NO_ITEMS_EXIST.send(sender, Lang.PREFIX.asString());
            return;
        }

        Lang.LIST_COMMAND_HEADER.send(sender, Lang.PREFIX.asString(), pouchList.size());
        for (FeluxItem pouch : pouchList) Lang.LIST_COMMAND_FORMAT.send(sender, Lang.PREFIX.asString(), pouch.getId());
        Lang.LIST_COMMAND_FOOTER.send(sender, Lang.PREFIX.asString(), pouchList.size());
    }
}
