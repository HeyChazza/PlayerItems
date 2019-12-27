package io.felux.items;

import io.felux.items.api.FeluxItem;
import io.felux.items.hook.WorldGuardHook;
import io.felux.items.manager.ItemManager;
import io.felux.items.registerable.CommandRegisterable;
import io.felux.items.util.ItemMapper;
import io.felux.items.util.Lang;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class FeluxItems extends JavaPlugin {
    private static FeluxItems instance;
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Lang.init();
        loadItems();
        new WorldGuardHook();
        new CommandRegisterable().register();
    }

    @Override
    public void onDisable() {

    }

    public void loadItems() {
        if (getConfig().getConfigurationSection("items") != null) {
            itemManager = new ItemManager(getConfig().getConfigurationSection("items").getKeys(false).stream().map(item -> ItemMapper.itemMap(item, getConfig().getConfigurationSection("items." + item))).collect(Collectors.toList()));
        }

        for (FeluxItem item : itemManager.getItems()) {
            getLogger().info("--- " + item.getId() + " ---");
            getLogger().info("- Commands: " + Arrays.toString(item.getCommands().toArray()));
            getLogger().info("- Blacklist (Regions): " + Arrays.toString(item.getBlacklistedRegions().toArray()));
            getLogger().info("- Blacklist (Worlds): " + Arrays.toString(item.getBlacklistedWorlds().toArray()));
        }
//        Lang.init(this);
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public static FeluxItems getInstance() {
        return instance;
    }
}
