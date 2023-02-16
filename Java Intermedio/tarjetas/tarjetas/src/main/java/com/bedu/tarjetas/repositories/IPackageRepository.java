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
    List<Package> getPackagesByIdRequest(long idRequest);


//
//    @Query(value = "select packages.id_package, packages.name_package, packages.name_product, packages.number_cards, packages.status, packages.location_id_location, " +
//            " packages.request_id_request, packages.branch_id_branch, locations.id_location, locations.capacity, locations.free_space, locations.name_location, " +
//            " locations.space_used, deliveries.id_delivery, deliveries.a_package_id_package, branches.id_branch, " +
//            " branches.calle, branches.ciudad, branches.colonia, branches.cp, branches.director, branches.email, branches.estado, branches.horario, branches.id_ruta, branches.municipio, branches.name_branch, " +
//            " branches.number_branch, branches.regional, branches.telefono, branches.territorial, branches.tipo_sucursal, branches.module_id_module" +
//            " FROM packages, locations, deliveries, branches " +
//            "where " +
//            "packages.location_id_location = locations.id_location " +
//            "and " +
//            "packages.id_package = deliveries.a_package_id_package " +
//            "and " +
//            "deliveries.branch_id_branch = branches.id_branch " +
//            "and " +
//            "request_id_request = :idRequest", nativeQuery = true)
}
