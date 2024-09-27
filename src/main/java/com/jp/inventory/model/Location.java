package com.jp.inventory.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Entity
@Data
@AllArgsConstructor
public class Location implements java.io.Serializable {

    @Id
    private Integer locationId; // unico, obligatorio
    private String state; // obligatorio
    private String address;
    private Integer phoneNumber;

    public Location() {
    }

    // public Location(Integer locationId, String state, Optional<String> address,
    // Optional<Integer> phoneNumber) {
    // this.locationId = locationId;
    // this.state = state;
    // this.address = address;
    // this.phoneNumber = phoneNumber;
    // }

    // public Integer getLocationId() {
    // return locationId;
    // }

    // public String getState() {
    // return state;
    // }

    // public Optional<String> getAddress() {
    // return address;
    // }

    // public Optional<Integer> getPhoneNumber() {
    // return phoneNumber;
    // }

}
