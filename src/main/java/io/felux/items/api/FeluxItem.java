package io.felux.items.api;

import io.felux.items.FeluxItems;
import io.felux.lib.api.general.StringUtil;
import io.felux.lib.nbt.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FeluxItem {

    private String id;
    private ItemStack item;
    private List<String> commands;
    private List<String> blacklistedWorlds;
    private List<String> blacklistedRegions;

    public FeluxItem(String id) {
        this.id = id;
        this.item = new ItemStack(Material.PAPER);
        this.commands = new ArrayList<>();
        this.blacklistedWorlds = new ArrayList<>();
        this.blacklistedRegions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public ItemStack getItem() {
        return item;
    }

    public List<String> getCommands() {
        return commands;
    }

    public List<String> getBlacklistedWorlds() {
        return blacklistedWorlds;
    }

    public List<String> getBlacklistedRegions() {
        return blacklistedRegions;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public void setBlacklistedWorlds(List<String> blacklistedWorlds) {
        this.blacklistedWorlds = blacklistedWorlds;
    }

    public void setBlacklistedRegions(List<String> blacklistedRegions) {
        this.blacklistedRegions = blacklistedRegions;
    }

    public static FeluxItem getItem(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) return null;
        NBTItem nbt = new NBTItem(itemStack);
        if (nbt.hasNBTData() && nbt.hasKey("items-id") && FeluxItems.getInstance().getItemManager().getItem(nbt.getString("items-id")) != null)
            return FeluxItems.getInstance().getItemManager().getItem(nbt.getString("items-id"));

        return null;
    }

    public boolean hasPermission(Player p) {
        return p.hasPermission("feluxitems.use." + id.toLowerCase());
    }

    public void runCommands(Player p) {
        List<String> strings = getCommands();
        strings.forEach(msg -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), StringUtil.translate(msg.replace("%player%", p.getName()))));
    }
}
