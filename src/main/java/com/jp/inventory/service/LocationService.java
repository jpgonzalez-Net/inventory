package com.jp.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.inventory.model.Location;
import com.jp.inventory.repository.LocationRepo;

@Service
public class LocationService {

    @Autowired
    LocationRepo locationRepo;

    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    public List<Location> getAllLocations() {
        List<Location> locations = locationRepo.findAll();
        return locations;
    }

    public Optional<Location> getLocation(Integer locationId) {
        return locationRepo.findById(locationId);
    }

    public boolean removeLocation(Integer locationId) {
        Optional<Location> locationOptional = getLocation(locationId);
        if (locationOptional.isPresent()) {
            locationRepo.deleteById(locationId);
            return true;
        }
        return false;
    }

    public Optional<Location> insterLocation(Location location) {
        if (location.getLocationId() == null || location.getState() == null) {
            return Optional.ofNullable(null);
        }

        locationRepo.save(location);
        return Optional.of(location);
    }

    public boolean validateId(Integer locationId) {
        return !locationRepo.existsById(locationId);
    }
}
