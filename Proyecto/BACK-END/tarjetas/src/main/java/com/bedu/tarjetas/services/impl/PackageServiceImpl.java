package com.bedu.tarjetas.services.impl;

import com.bedu.tarjetas.entities.Location;
import com.bedu.tarjetas.entities.Package;
import com.bedu.tarjetas.entities.Request;
import com.bedu.tarjetas.helper.ExcelHelper;
import com.bedu.tarjetas.helper.Status;
import com.bedu.tarjetas.repositories.ILocationRepository;
import com.bedu.tarjetas.repositories.IPackageRepository;
import com.bedu.tarjetas.repositories.IRequestRepository;
import com.bedu.tarjetas.services.IPackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PackageServiceImpl implements IPackageService {

    private static Logger logger = LoggerFactory.getLogger(LoactionServiceImpl.class);

    IPackageRepository iPackageRepository;
    ILocationRepository iLocationRepository;

    IRequestRepository iRequestRepository;

    @Autowired
    public PackageServiceImpl(IPackageRepository iPackageRepository, ILocationRepository iLocationRepository, IRequestRepository iRequestRepository){
        this.iPackageRepository = iPackageRepository;
        this.iLocationRepository = iLocationRepository;
        this.iRequestRepository = iRequestRepository;
    }
    @Transactional
    @Override
    public Package create(Package dataPackage) {
        Optional<Location> optionalLocation = iLocationRepository.getLocation(dataPackage.getNumberCards());
        if(optionalLocation.isPresent())
        {
            dataPackage.setLocation(optionalLocation.get());
            dataPackage.setStatus(Status.SITUATED);
            Package paquete1 = iPackageRepository.save(dataPackage);
            iLocationRepository.updateSpace(dataPackage.getNumberCards(), optionalLocation.get().getIdLocation());
            logger.info("The Package was created correctly!");
            return paquete1;
        }
        else
        {
            logger.error("Could not create the package!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Location %b dosen't exists", optionalLocation));
        }

    }
    @Transactional
    @Override
    public void createPackages(MultipartFile file) {
        try
        {
            Request request = new Request();
            request.setTypeRequest("STORAGE");
            request.setStorageDate(new Date());
            request.setFileName(file.getOriginalFilename());
            request.setStatus(Status.SITUATED);
            Request request1 = iRequestRepository.save(request);

            List<Package> packageList = ExcelHelper.excelToStorage(file.getInputStream());
            packageList.forEach(
                    (packageUnit) -> {
                        Optional<Location> optionalLocation = iLocationRepository.getLocation(packageUnit.getNumberCards());
                        if(optionalLocation.isPresent())
                        {
                            packageUnit.setRequest(request1);
                            packageUnit.setLocation(optionalLocation.get());
                            packageUnit.setStatus(Status.SITUATED);
                            Package aPackage = iPackageRepository.save(packageUnit);
                            iLocationRepository.updateSpace(packageUnit.getNumberCards(), optionalLocation.get().getIdLocation());
                            logger.info("The Package was created correctly!");
                        }
                        else{
                            logger.error("Location doesn't exist!");
                        }

                    });
        }
        catch (IOException e)
        {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

    }

    @Transactional
    @Override
    public Map<String, String> changeLocation(long idPackage, long idNewLocation) {
        Map<String, String> message = new HashMap<>();


        Optional<Package> optionalPackage = iPackageRepository.findById(idPackage);
        if(optionalPackage.isPresent()){
            Optional<Location> optionalLocation = iLocationRepository.getLocationById(optionalPackage.get().getNumberCards(), idNewLocation);
            if(optionalLocation.isPresent())
            {
                iPackageRepository.reassignLocation(idPackage, idNewLocation);
                iLocationRepository.updateSpace(optionalPackage.get().getNumberCards(), optionalLocation.get().getIdLocation());
                iLocationRepository.updateSpaceAdd(optionalPackage.get().getLocation().getIdLocation(), optionalPackage.get().getNumberCards());
                logger.info("The Package was changed of location");
            }
            else {
                logger.error("The location is not available");
                message.put("message", "La Ubicación con id: '" + idNewLocation + "' NO tiene esapacio disponible ó no existe en la Base de Datos, favor de verificar.");
            }
        }
        else {
            logger.error("The Package doesn't exist");
            message.put("message", "El Paquete con id: '" + idPackage + "' NO existe en la Base de Datos, favor de verificar.");
        }
        return message;
    }
    @Override
    public List<Package> getAllPackages() {
        return iPackageRepository.findAll();
    }

    @Transactional
    @Override
    public void deletePackage(long idPackage) {
        Optional<Package> optionalPackage = iPackageRepository.findById(idPackage);

        if(optionalPackage.isPresent()){
            iPackageRepository.deleteById(idPackage);
            iLocationRepository.updateSpaceAdd(optionalPackage.get().getLocation().getIdLocation(), optionalPackage.get().getNumberCards());
            logger.info("The Package was delete correctly");
        }
        else
            logger.error("The Package doesn't exist");
    }

    @Override
    public List<Package> getPackagesByIdRequest(long idRequest) {
        return iPackageRepository.getPackagesByIdRequest( idRequest );
    }


}
