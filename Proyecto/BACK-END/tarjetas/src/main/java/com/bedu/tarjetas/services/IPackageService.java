package com.bedu.tarjetas.services;

import com.bedu.tarjetas.entities.Package;
import com.bedu.tarjetas.entities.ResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IPackageService {

    Package create(Package dataPackage);

    Map<String, String> createPackages(MultipartFile file);

    Map<String, String> changeLocation(long idPackage, long idNewLocation);

    List<Package> getAllPackages();

    void deletePackage(long idPackage);


    List<ResultDTO> getPackagesByIdRequest(long idRequest);
}
