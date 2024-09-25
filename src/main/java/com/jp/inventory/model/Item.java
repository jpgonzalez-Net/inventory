package com.jp.inventory.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    private Integer itemId; // unico, obligatorio
    private String itemName; // obligatorio
    private Optional<String> description;
    private Optional<Location> location;

    protected Item() {
    }

    public Item(Integer itemId, String itemName, Optional<String> description, Optional<Location> location) {
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

    public Optional<Location> getLocation() {
        return location;
    }
}
