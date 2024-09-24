package com.jp.dao;

import java.util.Optional;
import java.util.List;

import com.jp.model.Item;

public interface ItemDao {
    List<Item> getAllItems();

    Optional<Item> getItem(Integer itemId);

    int removeItem(Integer itemId);

    int insertItem(Item item);

}
