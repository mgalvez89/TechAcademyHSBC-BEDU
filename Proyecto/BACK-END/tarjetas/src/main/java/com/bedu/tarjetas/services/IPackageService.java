package com.bedu.tarjetas.services;

import com.bedu.tarjetas.entities.Package;
import com.bedu.tarjetas.repositories.IResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IPackageService {

    Package create(Package dataPackage);

    Map<String, String> createPackages(MultipartFile file, String user);

    Map<String, String> changeLocation(long idPackage, long idNewLocation);

    List<Package> getAllPackages();

    void deletePackage(long idPackage);


    List<IResultDTO> getPackagesBranchByIdRequest(long idRequest);

    List<Package> getPackagesLocationByIdRequest(long idRequest);
}
