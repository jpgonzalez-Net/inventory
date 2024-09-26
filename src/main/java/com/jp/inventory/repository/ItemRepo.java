package com.jp.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jp.inventory.model.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

}
