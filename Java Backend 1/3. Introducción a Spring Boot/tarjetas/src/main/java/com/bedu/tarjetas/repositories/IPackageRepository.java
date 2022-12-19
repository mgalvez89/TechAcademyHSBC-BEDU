package com.bedu.tarjetas.repositories;

import com.bedu.tarjetas.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IPackageRepository extends JpaRepository<Package, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE packages " +
            "SET location_id_location = :idNewLocation " +
            "WHERE id_package = :idPackage", nativeQuery = true)
    void reassignLocation(int idPackage, int idNewLocation);
}
