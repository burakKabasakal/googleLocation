package com.google.location.component.impl;

import com.google.location.component.GooglePlacesComponent;
import com.google.location.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static com.google.location.constants.QueryConstants.*;

@Component
@RequiredArgsConstructor
public class GooglePlacesComponentImpl implements GooglePlacesComponent {

    private final RestTemplate restTemplate;

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.url}")
    private String apiUrl;

    @Value("${google.api.scheme}")
    private String apiScheme;

    @Override
    public String searchNearby(Location location) {
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity(this.getRootUri(location)
                        ,String.class);

        return responseEntity.getBody();
    }

    private String getRootUri(Location location){

        String url = UriComponentsBuilder
                .newInstance()
                .scheme(apiScheme)
                .host(apiUrl)
                .queryParam(LOCATION,location.getLatitude()+","+location.getLongitude())
                .queryParam(RADIUS,location.getRadius().toString())
                .queryParam(KEY,apiKey)
                .build()
                .toUriString();

        System.out.println(url);

        return url;

    }

}
