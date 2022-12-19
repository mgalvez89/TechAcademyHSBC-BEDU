package com.bedu.tarjetas.services;

import com.bedu.tarjetas.entities.Package;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPackageService {

    Package create(Package dataPackage);

    void createPackages(MultipartFile file);

    void changeLocation(int idPackage, int idNewLocation);

    List<Package> getAllPackages();

    void deletePackage(int idPackage);
}
