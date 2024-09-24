package com.jp.model;

import java.util.Optional;

public class Item {
    private final Integer itemId; // unico, obligatorio
    private final String itemName; // obligatorio
    private final Optional<String> description;
    private final Optional<Integer> location;

    public Item(Integer itemId, String itemName, Optional<String> description, Optional<Integer> location) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.location = location;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public Optional<Integer> getLocation() {
        return location;
    }
}
