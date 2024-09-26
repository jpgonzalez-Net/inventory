package com.jp.inventory.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.inventory.model.Location;
import com.jp.inventory.service.LocationService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/locations")
public class LocationResource {

    @Autowired
    LocationService locationService;

    @GetMapping()
    public List<Location> fetchLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping()
    public ResponseEntity<?> insertNewLocation(@RequestBody Location location) {

        // check if location is valid (id, state)
        // 400 - BAD REQUEST
        if (location.getLocationId().equals(null) || location.getState().equals(null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("invalid item"));
        }

        // check if locationId is already present in database
        // 409 - conflict
        if (!locationService.validateId(location.getLocationId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorMessage("item " + location.getLocationId() + " is already present in database"));
        }

        // location is valid and present
        // 201 - CREATED
        Optional<Location> locationOptional = locationService.insterLocation(location);
        if (locationOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(locationOptional.get());
        }

        // item is not valid
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("invalid item"));
    }

    @DeleteMapping(path = "{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable Integer locationId) {
        // Check if locationId exists
        if (!locationService.validateId(locationId)) {
            locationService.removeLocation(locationId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("location " + locationId + " was deleted from database");
        }

        // if not exist
        // 404 - Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage("location " + locationId + " was not found"));
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
