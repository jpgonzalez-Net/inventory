package com.jp.inventory.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.MediaType;

import com.jp.inventory.model.Item;
import com.jp.inventory.service.ItemService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/items")
public class ItemResource {
    private ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    // GET /items
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public List<Item> fetchItems() {
        return itemService.getAllItems();
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
