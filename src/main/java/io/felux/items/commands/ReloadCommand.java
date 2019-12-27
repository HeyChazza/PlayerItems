package io.felux.items.commands;

import io.felux.items.FeluxItems;
import io.felux.items.util.Lang;
import io.felux.lib.api.command.Command;
import org.bukkit.command.CommandSender;

public class ReloadCommand {
    @Command(aliases = {"reload"}, about = "Reload the configuration files.", permission = "feluxitems.reload", usage = "reload")
    public static void execute(final CommandSender sender, final FeluxItems plugin, final String[] args) {
        plugin.reloadConfig();
        Lang.init();
        plugin.loadItems();
        Lang.RELOAD_COMMAND.send(sender, Lang.PREFIX.asString(), plugin.getItemManager().getItems().size());
    }
}
