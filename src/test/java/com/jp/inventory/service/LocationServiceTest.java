package com.jp.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jp.inventory.model.Location;
import com.jp.inventory.repository.LocationRepo;

public class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepo fakeLocationRepo;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        locationService = new LocationService(fakeLocationRepo);
    }

    @Test
    void testGetAllLocations() {
        Location newLocation = new Location(1, "TX", "44 Champions Lane", 811234);

        List<Location> locations = List.of(newLocation);
        given(fakeLocationRepo.findAll()).willReturn(locations);

        List<Location> allLocations = locationService.getAllLocations();
        assertThat(allLocations).hasSize(1);

        Location location = allLocations.get(0);

        assertLocationFields(location);
    }

    @Test
    void testGetLocation() {
        Location newLocation = new Location(1, "TX", "44 Champions Lane", 811234);

        given(fakeLocationRepo.findById(1)).willReturn(Optional.of(newLocation));

        Optional<Location> locatiOptional = locationService.getLocation(1);

        assertThat(locatiOptional).isPresent();
        assertLocationFields(locatiOptional.get());
    }

    @Test
    void testGetInvalidLocation() {
        Optional<Location> locationOptional = locationService.getLocation(1);
        assertThat(locationOptional).isEmpty();
    }

    @Test
    void testInsterLocation() {
        Location newLocation = new Location(1, "TX", "44 Champions Lane", 811234);

        given(fakeLocationRepo.save(newLocation)).willReturn(newLocation);
        ArgumentCaptor<Location> captor = ArgumentCaptor.forClass(Location.class);
        Optional<Location> locationResult = locationService.insterLocation(newLocation);
        verify(fakeLocationRepo).save(captor.capture());

        Location location = captor.getValue();
        assertLocationFields(location);
        assertThat(locationResult).isPresent();
        assertLocationFields(locationResult.get());
    }

    @Test
    void testInsertInvalidLocation() {
        Location newLocation = new Location(null, null, "44 Champions Lane", 811234);

        given(fakeLocationRepo.save(newLocation)).willReturn(newLocation);
        ArgumentCaptor<Location> captor = ArgumentCaptor.forClass(Location.class);
        Optional<Location> locationResult = locationService.insterLocation(newLocation);
        verify(fakeLocationRepo, never()).save(captor.capture());

        assertThat(locationResult).isEmpty();
    }

    @Test
    void testRemoveLocation() {
        Location newLocation = new Location(1, "TX", "44 Champions Lane", 811234);

        given(fakeLocationRepo.findById(1)).willReturn(Optional.of(newLocation));

        boolean deleteResult = locationService.removeLocation(1);
        verify(fakeLocationRepo).findById(1);
        verify(fakeLocationRepo).deleteById(1);

        assertThat(deleteResult).isTrue();
    }

    @Test
    void testRemoveInvalidLocation() {
        boolean deleteResult = locationService.removeLocation(1);
        verify(fakeLocationRepo, never()).deleteById(anyInt());

        assertThat(deleteResult).isFalse();
    }

    private void assertLocationFields(Location location) {
        assertThat(location.getLocationId()).isEqualTo(1);
        assertThat(location.getState()).isEqualTo("TX");
        assertThat(location.getAddress()).isEqualTo("44 Champions Lane");
        assertThat(location.getPhoneNumber()).isEqualTo(811234);
    }
}
