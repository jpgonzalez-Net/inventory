package com.jp.inventory.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.jp.inventory.dao.ItemDao;
import com.jp.inventory.model.Item;

@Service
public class ItemService {
    private ItemDao itemDao;

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

    public int insertItem(Integer itemId, Item item) {
        return itemDao.insertItem(itemId, item);
    }
}
