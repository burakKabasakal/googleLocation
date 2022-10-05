package com.google.location.repository;

import com.google.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {

    public Optional<Location> findByLatitudeEqualsAndLongitudeEqualsAndRadiusEquals(Double latitude,
                                                                                    Double longitude,
                                                                                    Integer radius);

}
