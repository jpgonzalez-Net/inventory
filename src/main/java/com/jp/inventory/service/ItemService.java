package com.jp.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.jp.inventory.dao.ItemDao;
import com.jp.inventory.model.Item;

@Service
public class ItemService {

    private ItemDao itemDao;

    @Autowired
    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public List<Item> getAllItems() {
        List<Item> items = itemDao.getAllItems();
        return items;
    }

    public Optional<Item> getItem(Integer itemId) {
        return itemDao.getItem(itemId);
    }

    public int removeItem(Integer itemId) {
        Optional<Item> itemOptional = getItem(itemId);
        if (itemOptional.isPresent()) {
            return itemDao.removeItem(itemId);
        }
        return -1;
    }

    public Optional<Item> insertItem(Integer itemId, Item item) {
        if (itemId == null || item.getItemName() == null || !this.validateId(itemId)) {
            return Optional.of(null);
        }
        if (item.getLocation().isPresent()) {
            if (item.getLocation().get().getLocationId() == null || item.getLocation().get().getLocationId() == null) {
                return Optional.of(null);
            }
        }

        return itemDao.insertItem(itemId, item);
    }

    public boolean validateId(Integer itemId) {
        return itemDao.validateId(itemId);
    }

    public boolean validateLocationId(Integer locationId) {
        return itemDao.validateLocationId(locationId);
    }
}
