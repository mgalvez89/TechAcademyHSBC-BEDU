package com.bedu.tarjetas.services.impl;

import com.bedu.tarjetas.entities.Location;
import com.bedu.tarjetas.helper.ExcelHelper;
import com.bedu.tarjetas.repositories.ILocationRepository;
import com.bedu.tarjetas.services.ILocationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LoactionServiceImpl implements ILocationService {

    private static Logger logger = LoggerFactory.getLogger(LoactionServiceImpl.class);
    ILocationRepository iLocationRepository;

    @Autowired
    public LoactionServiceImpl(ILocationRepository iLocationRepository){
        this.iLocationRepository = iLocationRepository;
    }

    @Override
    public Location create(Location location) {
        Location location1 = iLocationRepository.save(location);
        if(location1 != null){
            logger.info("The location was created correctly");
            return location1;
        }
        else
            logger.error("Could not create the location");
        return location1;
    }

    @Override
    public void createLocations(MultipartFile file) {
        try{
            List<Location> locationList = ExcelHelper.excelToLocations(file.getInputStream());
            locationList.forEach(
                    (locationUnit) -> {
                        locationUnit.setFreeSpace(locationUnit.getCapacity());
                        iLocationRepository.save(locationUnit);
                        logger.info("The location was created correctly");
                    }
            );
        }
        catch (IOException e)
        {
            logger.error("fail to store excel data");
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

    }

    @Override
    public Optional<Location> getLocationAvailable(int numberCards) {
        return iLocationRepository.getLocation(numberCards);
    }

    @Override
    public List<Location> getAllLocations() {
        return iLocationRepository.findAll();
    }

    @Override
    public int updateCapacity(long idLocation, int capacity) {
        int status = -1;
        Optional<Location> byIdLocation = iLocationRepository.findByIdLocation(idLocation);
        if(byIdLocation.isPresent()){
            if(byIdLocation.get().getSpaceUsed() ==  0) {
                status = iLocationRepository.updateCapacitySpace(idLocation, capacity);
                logger.info("Capacity of Location was Updated {} ", status);
            }
        else
            {
                logger.warn("Can't Update Capacity of Location because is Used");
                status = 0;
            }
        }
        else{
            logger.error("The location doesn't exist to update");
            status = 2;
        }
            return status;
    }

    @Override
    public int deleteLocation(long idLocation) {
        int status = -1;
        Optional<Location> byIdLocation = iLocationRepository.findByIdLocation(idLocation);
        if(byIdLocation.isPresent()){
            if(byIdLocation.get().getSpaceUsed() ==  0) {
                iLocationRepository.deleteById(idLocation);
                status = 1;
                logger.info("The location was deleted!");
            }
            else {
                logger.warn("Can't delete Location because is Used!");
                status = 0;
            }
        }
        else{
            logger.error("Not exist location to delete");
            status = 2;
        }
        return status;
    }

    @Override
    public Optional<Location> getNameLocation(@Valid int idLocation) {
        return iLocationRepository.findByIdLocation(idLocation);
    }

}
