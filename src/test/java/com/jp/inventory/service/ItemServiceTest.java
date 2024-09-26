package com.jp.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jp.inventory.model.Item;
import com.jp.inventory.repository.ItemRepo;

public class ItemServiceTest {
    @Mock
    private ItemRepo fakeItemRepo;
    private ItemService itemService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService();
    }

    @Test
    void testGetAllItems() throws Exception {
        Item newItem = new Item(1, "baseball", "description", null);

        List<Item> items = List.of(newItem);
        given(fakeItemRepo.findAll()).willReturn(items);

        List<Item> allItems = itemService.getAllItems();

        assertThat(allItems).hasSize(1);

        Item item = items.get(0);
        assertItemFields(item);
    }

    @Test
    void testGetItem() {

    }

    @Test
    void testInsertItem() {

    }

    @Test
    void testRemoveItem() {

    }

    @Test
    void testValidateId() {

    }

    private void assertItemFields(Item item) {
        assertThat(item.getItemId()).isEqualTo(1);
        assertThat(item.getItemName()).isEqualTo("baseball");
        assertThat(item.getDescription()).isEqualTo("description");
        assertThat(item.getLocation()).isEqualTo(null);
    }
}
