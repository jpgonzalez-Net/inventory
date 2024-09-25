package com.jp.inventory.dao;

import org.springframework.stereotype.Repository;

import com.jp.inventory.model.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Repository
public class FakeItemDao implements ItemDao {
    private Map<Integer, Item> database;

    public FakeItemDao() {
        database = new HashMap<>();
        int id = 1;
        database.put(id, new Item(id, "soap", null, null));
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Item> getItem(Integer itemId) {
        return Optional.ofNullable(database.get(itemId));
    }

    @Override
    public int removeItem(Integer itemId) {
        database.remove(itemId);
        return 1;
    }

    @Override
    public int insertItem(Integer itemId, Item item) {
        database.put(itemId, item);
        return 1;
    }
}
