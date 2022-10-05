package com.google.location.controller;

import com.google.location.component.GooglePlacesComponent;
import com.google.location.model.Location;
import com.google.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final GooglePlacesComponent googlePlacesComponent;

    @GetMapping("/search")
    public ResponseEntity<String> getNearbySearch(@RequestParam("lat") Double latitude,
                                          @RequestParam("long") Double longitude,
                                          @RequestParam("r") Integer radius){

        Optional<Location> optionalLocation = locationService.findLocation(latitude, longitude, radius);
        Location location;
         if(optionalLocation.isPresent())
             location = optionalLocation.get();
         else
             location = locationService.saveLocation(latitude, longitude, radius);

        return ResponseEntity.ok(googlePlacesComponent.searchNearby(location));
    }

}
