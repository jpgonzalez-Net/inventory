package com.jp.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.jp.inventory.model.Item;
import com.jp.inventory.repository.ItemRepo;

@Service
public class ItemService {

    @Autowired
    ItemRepo itemRepo;

    public List<Item> getAllItems() {
        List<Item> items = itemRepo.findAll();
        return items;
    }

    public Optional<Item> getItem(Integer itemId) {
        return itemRepo.findById(itemId);
    }

    public void removeItem(Integer itemId) {
        Optional<Item> itemOptional = getItem(itemId);
        if (itemOptional.isPresent()) {
            itemRepo.deleteById(itemId);
        }
    }

    public Optional<Item> insertItem(Item item) {
        if (item.getItemId() == null || item.getItemName() == null || !this.validateId(item.getItemId())) {
            return Optional.of(null);
        }
        // if (item.getLocation().isPresent()) {
        // if (item.getLocation().get().getLocationId() == null ||
        // item.getLocation().get().getLocationId() == null) {
        // return Optional.of(null);
        // }
        // }

        itemRepo.save(item);
        return Optional.of(item);
    }

    public boolean validateId(Integer itemId) {
        return !itemRepo.existsById(itemId);
    }
}
