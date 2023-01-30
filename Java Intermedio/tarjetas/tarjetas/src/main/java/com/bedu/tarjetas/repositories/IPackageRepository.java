package com.bedu.tarjetas.repositories;

import com.bedu.tarjetas.entities.Delivery;
import com.bedu.tarjetas.entities.Location;
import com.bedu.tarjetas.entities.Package;
import com.bedu.tarjetas.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPackageRepository extends JpaRepository<Package, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE packages " +
            "SET location_id_location = :idNewLocation " +
            "WHERE id_package = :idPackage", nativeQuery = true)
    void reassignLocation(long idPackage, long idNewLocation);

    public Optional<Package> findOneByNamePackage(String namePackage);

    @Modifying
    @Transactional
    @Query(value = "UPDATE packages " +
            "SET request_id_request = :idRequest, " +
            "status = :status " +
            "WHERE id_package = :idPackage", nativeQuery = true)
    int updateStatusPackage(long idPackage, long idRequest, String status);

    List<Package> findByRequestAndStatus(Request request, String status);


    @Modifying
    @Query(value = "UPDATE packages " +
            "SET status = :status " +
            "WHERE id_package = :idPackage", nativeQuery = true)
    int releasePackage(long idPackage, String status);
}
