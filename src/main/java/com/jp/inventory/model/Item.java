package com.jp.inventory.model;

import java.util.Optional;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Entity
@Data
@AllArgsConstructor
public class Item implements java.io.Serializable {

    @Id
    private Integer itemId; // unico, obligatorio
    private String itemName; // obligatorio
    // private Optional<String> description;
    // private Optional<Location> location;

    public Item() {
    }

    // public Item(int itemId, String itemName, Optional<String> description,
    // Optional<Location> location) {
    // this.itemId = itemId;
    // this.itemName = itemName;
    // // this.description = description;
    // // this.location = location;
    // }

    // public int getItemId() {
    // return this.itemId;
    // }

    // public String getItemName() {
    // return this.itemName;
    // }

    // public Optional<String> getDescription() {
    // // return description;
    // return Optional.of(null);
    // }

    // public Optional<String> getLocation() {
    // // return location;
    // return Optional.of(null);
    // }
}
