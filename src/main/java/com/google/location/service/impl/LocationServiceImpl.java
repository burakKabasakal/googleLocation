package com.google.location.service.impl;

import com.google.location.repository.LocationRepository;
import com.google.location.model.Location;
import com.google.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = true)
    @Override
    public Optional<Location> findLocation(Double latitude, Double longitude, Integer radius) {
        return locationRepository
                .findByLatitudeEqualsAndLongitudeEqualsAndRadiusEquals(latitude,
                        longitude,
                        radius);
    }

    @Transactional
    public Location saveLocation(Double latitude, Double longitude, Integer radius){
        Location location = Location
                .builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius).build();

        return locationRepository.save(location);
    }
}
