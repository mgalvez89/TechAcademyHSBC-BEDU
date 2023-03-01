package com.bedu.tarjetas.repositories;

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

    @Query(value = "SELECT * " +
            "FROM " +
            "   packages, locations " +
            "WHERE " +
            "   packages.location_id_location = locations.id_location " +
            "AND " +
            "   request_id_request = :idRequest", nativeQuery = true)
    List<Package> getPackagesByIdRequestAndLocation(long idRequest);

    @Query(value = "SELECT  p.name_package as namePackage, " +
                "   p.name_product as nameProduct, p.number_cards as numberCards, " +
                "   l.name_location as nameLocation, b.name_branch as nameBranch  " +
                "FROM " +
                "   packages p " +
                "INNER JOIN " +
                "   locations l on p.location_id_location = l.id_location " +
                "INNER JOIN " +
                "   deliveries d on p.id_package = d.a_package_id_package " +
                "INNER JOIN " +
                "   branches b on d.branch_id_branch = b.id_branch " +
                "WHERE " +
                "   p.request_id_request = :idRequest", nativeQuery = true )
    List<IResultDTO> getPackagesByIdRequestAndBranch(long idRequest);

}
