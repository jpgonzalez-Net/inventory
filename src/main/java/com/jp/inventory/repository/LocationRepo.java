package com.jp.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.inventory.model.Location;

public interface LocationRepo extends JpaRepository<Location, Integer> {

}
