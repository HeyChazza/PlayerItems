package io.felux.items.registerable;

import io.felux.items.FeluxItems;
import io.felux.items.commands.*;
import io.felux.items.util.Lang;
import io.felux.lib.api.command.CommandManager;

import java.util.Arrays;

public class CommandRegisterable {
    private static CommandManager commandManager;
    private final FeluxItems PLUGIN = FeluxItems.getInstance();

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public void register() {
        commandManager = new CommandManager(Arrays.asList(GiveCommand.class, HelpCommand.class, ListCommand.class, ReloadCommand.class), PLUGIN.getDescription().getName().toLowerCase(), PLUGIN);
        commandManager.setMainCommand(MainCommand.class);

        CommandManager.Locale locale = commandManager.getLocale();
        locale.setNoPermission(Lang.ERROR_NO_PERMISSION_COMMAND.asString(Lang.PREFIX.asString()));
        locale.setPlayerOnly(Lang.ERROR_PLAYER_ONLY.asString(Lang.PREFIX.asString()));
        locale.setUnknownCommand(Lang.ERROR_INVALID_COMMAND.asString(Lang.PREFIX.asString()));
        locale.setUsage(Lang.COMMAND_USAGE.asString(Lang.PREFIX.asString(), "{usage}"));
    }
}
