package com.jp.inventory.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.core.MediaType;

import com.jp.inventory.model.Item;
import com.jp.inventory.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/items")
public class ItemResource {

    @Autowired
    ItemService itemService;

    // public ItemResource(ItemService itemService) {
    // this.itemService = itemService;
    // }

    // GET /items
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public List<Item> fetchItems(@RequestParam(required = false) String itemName,
            @RequestParam(required = false) String description) {

        List<Item> items = itemService.getAllItems();
        if (itemName != null) {
            List<Item> filteredList = new ArrayList<>();
            for (Item item : items) {
                if (item.getItemName().equals(itemName)) {
                    filteredList.add(item);
                }
            }
            items = filteredList;
        }

        if (description != null) {
            List<Item> filteredList = new ArrayList<>();
            for (Item item : items) {
                if (item.getDescription().equals(description)) {
                    filteredList.add(item);
                }
            }
            items = filteredList;
        }

        return items;
    }

    // GET /items/{itemId}
    @GetMapping(path = "{itemId}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> fetchItem(@PathVariable Integer itemId) {
        Optional<Item> itemOptional = itemService.getItem(itemId);
        if (itemOptional.isPresent()) {
            return ResponseEntity.ok(itemOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("item " + itemId + " was not found"));
    }

    // POST /items (add new item)
    @PostMapping()
    public ResponseEntity<?> insterNewItem(@RequestBody Item item) {
        // check if item is valid (id, name)
        // 400 - bad request
        if (item.getItemId() == null || item.getItemName().equals(null)) {
            // check if has location and the location is valid
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("invalid item"));
        }

        // check if itemId is already present in database
        // 409 - conflict
        if (!itemService.validateId(item.getItemId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorMessage("item " + item.getItemId() + " is already present in database"));
        }

        // item is present and avalid
        // 201 - OK
        Optional<Item> itemOptional = itemService.insertItem(item);
        if (itemOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemOptional.get());
        }

        // item is not valid
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("invalid item"));
    }

    // DELETE /items
    @DeleteMapping(path = "{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer itemId) {
        // Check if itemId exists
        if (!itemService.validateId(itemId)) {
            itemService.removeItem(itemId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("item " + itemId + " was deleted from database");
        }

        // if not exist
        // 404 - Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage("item " + itemId + " was not found"));
    }

    class ErrorMessage {
        String message;

        public ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
