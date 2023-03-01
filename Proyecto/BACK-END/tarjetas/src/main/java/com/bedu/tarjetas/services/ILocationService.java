package com.bedu.tarjetas.services;

import com.bedu.tarjetas.entities.Location;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ILocationService {

    Location create(Location data);

    void createLocations(MultipartFile file);

    Optional<Location> getLocationAvailable(int numberCards);

    List<Location> getAllLocations();

    int updateCapacity(long idLocation, int capacity);

    int deleteLocation(long idLocation);

    Optional<Location> getNameLocation(@Valid int idLocation);
}
