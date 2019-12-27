package io.felux.items.util;

import io.felux.items.api.FeluxItem;
import io.felux.lib.api.exception.InvalidMaterialException;
import io.felux.lib.api.general.StringUtil;
import io.felux.lib.api.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class ItemMapper {
    public static FeluxItem itemMap(String id, ConfigurationSection data) {

        FeluxItem item = new FeluxItem(id);
        item.setItem(itemstackMap(id, data));

        ConfigurationSection settingsSection = data.getConfigurationSection("settings");
        if (settingsSection == null) throw new RuntimeException("Item '" + id + "' doesn't have any settings!");

        item.setCommands(settingsSection.getStringList("commands"));
        item.setBlacklistedRegions(settingsSection.getStringList("blacklist.region"));
        item.setBlacklistedWorlds(settingsSection.getStringList("blacklist.world"));

        return item;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack itemstackMap(String id, ConfigurationSection data) {
        if (id == null) return null;

        ConfigurationSection itemSection = data.getConfigurationSection("item");
        if (itemSection == null) throw new RuntimeException("Item '" + id + "' doesn't have any item data!");

        ItemBuilder itemBuilder;
        try {
            itemBuilder = new ItemBuilder(itemSection.getString("type", "PAPER"));
        } catch (InvalidMaterialException e) {
            e.printStackTrace();
            return new ItemBuilder(Material.PAPER).withName("&a" + id + " Item").withLore("&7No item data specified!").getItem();
        }

        itemBuilder.withName(StringUtil.translate(itemSection.getString("name", "&b" + id + " Item")));
        itemBuilder.withLore(StringUtil.translate(itemSection.getStringList("lore")));

        if (itemSection.getBoolean("glow", false)) {
            itemBuilder.withEnchant(Enchantment.WATER_WORKER, 1);
            itemBuilder.withFlag(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        }

        itemBuilder.withData(itemSection.getInt("data", 0));
        itemBuilder.withNBTString("items-id", id);
        return itemBuilder.getItem();
    }
}
