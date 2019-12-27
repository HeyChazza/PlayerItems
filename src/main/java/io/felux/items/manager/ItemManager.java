package io.felux.items.manager;

import io.felux.items.api.FeluxItem;

import java.util.List;

public class ItemManager {
    private List<FeluxItem> items;

    public ItemManager(List<FeluxItem> items) {
        this.items = items;
    }

    public List<FeluxItem> getItems() {
        return items;
    }

    public FeluxItem getItem(String id) {
        return items.stream().filter(item -> item.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }
}
