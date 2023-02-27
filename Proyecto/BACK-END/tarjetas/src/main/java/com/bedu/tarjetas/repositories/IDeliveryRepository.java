package com.bedu.tarjetas.repositories;

import com.bedu.tarjetas.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeliveryRepository extends JpaRepository<Delivery, Long> {
    @Modifying
    @Query(value = "UPDATE deliveries " +
            "SET status = :status " +
            "WHERE a_package_id_package = :idPackage", nativeQuery = true)
    int releaseDelivery(long idPackage, String status);
}
