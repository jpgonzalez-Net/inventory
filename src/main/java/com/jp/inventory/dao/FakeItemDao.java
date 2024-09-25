// package com.jp.inventory.dao;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;

// import com.jp.inventory.model.Item;
// import com.jp.inventory.model.Location;

// import java.util.HashMap;
// import java.util.Map;
// import java.util.Optional;
// import java.util.List;
// import java.util.ArrayList;

// @Repository
// public class FakeItemDao implements ItemDao {
// private Map<Integer, Item> database;

// @Autowired
// public FakeItemDao() {
// database = new HashMap<>();
// int id = 1;
// database.put(id, new Item(id, "soap", Optional.empty(), Optional.of(new
// Location(1, "Texas", null, null))));
// }

// @Override
// public List<Item> getAllItems() {
// return new ArrayList<>(database.values());
// }

// @Override
// public Optional<Item> getItem(Integer itemId) {
// return Optional.ofNullable(database.get(itemId));
// }

// @Override
// public int removeItem(Integer itemId) {
// database.remove(itemId);
// return 1;
// }

// @Override
// public Optional<Item> insertItem(Integer itemId, Item item) {
// database.put(itemId, item);
// return Optional.ofNullable(database.get(itemId));
// }

// @Override
// public boolean validateId(Integer itemId) {
// Item item = database.get(itemId);
// return item == null;
// }

// @Override
// public boolean validateLocationId(Integer locationId) {
// List<Item> items = getAllItems();
// // iterate each item
// for (Item item : items) {
// // check if item has a location
// if (item.getLocation().isPresent()) {
// // check if the id of that location is equal to locationId
// if (item.getLocation().get().getLocationId() == locationId) {
// return false;
// }
// }
// }
// return true;
// }
// }
