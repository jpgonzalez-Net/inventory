package com.jp.model;

import java.util.Optional;

public class Location {
    private final Integer locationId; // unico, obligatorio
    private final String state; // obligatorio
    private final Optional<String> address;
    private final Optional<Integer> phoneNumber;

    public Location(Integer locationId, String state, String address, Integer phoneNumber) {
        this.locationId = locationId;
        this.state = state;
        this.address = Optional.of(address);
        this.phoneNumber = Optional.of(phoneNumber);
    }

    public Integer getLocationId() {
        return locationId;
    }

    public String getState() {
        return state;
    }

    public Optional<String> getAddress() {
        return address;
    }

    public Optional<Integer> getPhoneNumber() {
        return phoneNumber;
    }

}
