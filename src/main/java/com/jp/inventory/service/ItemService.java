package com.jp.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import com.jp.inventory.model.Item;
import com.jp.inventory.repository.ItemRepo;

@Service
public class ItemService {

    @Autowired
    ItemRepo itemDao;

    public List<Item> getAllItems() {
        List<Item> items = itemDao.findAll();
        return items;
    }

    public Optional<Item> getItem(Integer itemId) {
        return itemDao.findById(itemId);
    }

    public void removeItem(Integer itemId) {
        Optional<Item> itemOptional = getItem(itemId);
        if (itemOptional.isPresent()) {
            itemDao.deleteById(itemId);
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

        itemDao.save(item);
        return Optional.of(item);
    }

    public boolean validateId(Integer itemId) {
        return !itemDao.existsById(itemId);
    }

    public boolean validateLocationId(Integer locationId) {
        return true;
        // return itemDao.validateLocationId(locationId);
    }
}
