package com.bedu.tarjetas.services;

import com.bedu.tarjetas.entities.Location;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ILocationService {

    Location create(Location data);

    void createLocations(MultipartFile file);

    Optional<Location> getLocationAvailable(int numberCards);

    List<Location> getAllLocations();

    void updateCapacity(int idLocation, int capacity);

    void deleteLocation(int idLocation);
}
