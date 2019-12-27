package io.felux.items.commands;

import io.felux.items.FeluxItems;
import io.felux.items.util.Lang;
import io.felux.lib.api.command.Command;
import org.bukkit.command.CommandSender;

public class MainCommand {
    @Command(aliases = {"reload"}, about = "The main command.", usage = "reload")
    public static void execute(final CommandSender sender, final FeluxItems plugin, final String[] args) {
        Lang.MAIN_COMMAND.send(sender, Lang.PREFIX.asString(), plugin.getDescription().getName(), plugin.getDescription().getVersion(), plugin.getDescription().getAuthors().get(0));
    }
}
