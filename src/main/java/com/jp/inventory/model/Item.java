package com.jp.inventory.model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Item")
public class Item implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId; // unico, obligatorio
    private String itemName; // obligatorio
    // private Optional<String> description;
    // private Optional<Location> location;

    public Item(Integer itemId, String itemName, Optional<String> description, Optional<Location> location) {
        this.itemId = itemId;
        this.itemName = itemName;
        // this.description = description;
        // this.location = location;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public Optional<String> getDescription() {
        // return description;
        return Optional.of(null);
    }

    public Optional<String> getLocation() {
        // return location;
        return Optional.of(null);
    }
}
