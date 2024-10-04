package com.jp.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.jp.inventory.model.Item;
import com.jp.inventory.model.Location;
import com.jp.inventory.repository.ItemRepo;
import com.jp.inventory.repository.LocationRepo;

@Service
public class ItemService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    LocationService locationService;

    public ItemService(ItemRepo itemRepo, LocationService locationService) {
        this.itemRepo = itemRepo;
        this.locationService = locationService;
    }

    public List<Item> getAllItems() {
        List<Item> items = itemRepo.findAll();
        return items;
    }

    public Optional<Item> getItem(Integer itemId) {
        return itemRepo.findById(itemId);
    }

    public boolean removeItem(Integer itemId) {
        Optional<Item> itemOptional = getItem(itemId);
        if (itemOptional.isPresent()) {
            itemRepo.deleteById(itemId);
            return true;
        }
        return false;
    }

    public Optional<Item> insertItem(Item item) {
        if (item.getItemId() == null || item.getItemName() == null || !this.validateId(item.getItemId())) {
            return Optional.ofNullable(null);
        }

        if (item.getLocation() != null) {
            Optional<Location> loc = locationService.getLocation(item.getLocation().getLocationId());
            if (loc.isEmpty()) {
                return Optional.ofNullable(null);
            }
        }

        itemRepo.save(item);
        return itemRepo.findById(item.getItemId());
    }

    public boolean validateId(Integer itemId) {
        return !itemRepo.existsById(itemId);
    }
}
