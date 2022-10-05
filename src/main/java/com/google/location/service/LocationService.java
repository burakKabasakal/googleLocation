package com.google.location.service;

import com.google.location.model.Location;

import java.util.Optional;

public interface LocationService {

    public Optional<Location> findLocation(Double latitude, Double longitude, Integer radius);

    public Location saveLocation(Double latitude, Double longitude, Integer radius);

}
