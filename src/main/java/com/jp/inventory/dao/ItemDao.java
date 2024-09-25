package com.jp.inventory.dao;

import java.util.Optional;
import java.util.List;

import com.jp.inventory.model.Item;

public interface ItemDao {
    List<Item> getAllItems();

    Optional<Item> getItem(Integer itemId);

    int removeItem(Integer itemId);

    Optional<Item> insertItem(Integer itemId, Item item);

    boolean validateId(Integer itemId);
}
