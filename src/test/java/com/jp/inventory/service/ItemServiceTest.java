package com.jp.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jp.inventory.model.Item;
import com.jp.inventory.model.Location;
import com.jp.inventory.repository.ItemRepo;

public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepo fakeItemRepo;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(fakeItemRepo);
    }

    @Test
    void testGetAllItems() throws Exception {
        Item newItem = new Item(1, "baseball", "description", new Location(1, "TX", "44 Champions Lane", 811234));

        List<Item> items = List.of(newItem);
        given(fakeItemRepo.findAll()).willReturn(items);

        List<Item> allItems = itemService.getAllItems();

        assertThat(allItems).hasSize(1);

        Item item = allItems.get(0);
        assertItemFields(item);
    }

    @Test
    void testGetItem() {
        Item newItem = new Item(1, "baseball", "description", new Location(1, "TX", "44 Champions Lane", 811234));

        given(fakeItemRepo.findById(1)).willReturn(Optional.of(newItem));

        Optional<Item> itemOptional = itemService.getItem(1);

        assertThat(itemOptional).isPresent();

        assertItemFields(itemOptional.get());
    }

    @Test
    void testGetInvalidItem() {
        Optional<Item> itemOptional = itemService.getItem(1);
        assertThat(itemOptional).isEmpty();
    }

    @Test
    void testInsertItem() {
        Item newItem = new Item(1, "baseball", "description", new Location(1, "TX", "44 Champions Lane", 811234));

        given(fakeItemRepo.save(newItem)).willReturn(newItem);
        ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);
        Optional<Item> itemResult = itemService.insertItem(newItem);
        verify(fakeItemRepo).save(captor.capture());

        Item item = captor.getValue();
        assertItemFields(item);
        assertThat(itemResult).isPresent();
        assertItemFields(itemResult.get());
    }

    @Test
    void testInsertInvalidItem() {
        Item newItem = new Item(null, null, "description", new Location(1, "TX", "44 Champions Lane", 811234));

        given(fakeItemRepo.save(newItem)).willReturn(newItem);
        ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);
        Optional<Item> itemResult = itemService.insertItem(newItem);
        verify(fakeItemRepo, never()).save(captor.capture());

        assertThat(itemResult).isEmpty();
    }

    @Test
    void testRemoveItem() {
        Item newItem = new Item(1, "baseball", "description", new Location(1, "TX", "44 Champions Lane", 811234));

        given(fakeItemRepo.findById(1)).willReturn(Optional.of(newItem));

        boolean deleteResult = itemService.removeItem(1);
        verify(fakeItemRepo).findById(1);
        verify(fakeItemRepo).deleteById(1);

        assertThat(deleteResult).isTrue();
    }

    @Test
    void testRemoveInvalidItem() {
        boolean deleteResult = itemService.removeItem(1);
        verify(fakeItemRepo, never()).deleteById(anyInt());

        assertThat(deleteResult).isFalse();
    }

    private void assertItemFields(Item item) {
        assertThat(item.getItemId()).isEqualTo(1);
        assertThat(item.getItemName()).isEqualTo("baseball");
        assertThat(item.getDescription()).isEqualTo("description");

        Location location = item.getLocation();
        assertThat(location.getLocationId()).isEqualTo(1);
        assertThat(location.getState()).isEqualTo("TX");
        assertThat(location.getAddress()).isEqualTo("44 Champions Lane");
        assertThat(location.getPhoneNumber()).isEqualTo(811234);
    }
}
