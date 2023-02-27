package com.bedu.tarjetas.repositories;

import com.bedu.tarjetas.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IRequestRepository extends JpaRepository<Request, Long> {

    @Modifying
    @Query(value = "UPDATE requests " +
            "SET delivery_date = :deliveryDate, " +
            "status = :status " +
            "WHERE id_request = :idRequest", nativeQuery = true)
    int realeaseRequest(long idRequest, Date deliveryDate, String status);

    @Modifying
    @Query(value = "UPDATE requests " +
            "SET " +
            "   status = :status " +
            "WHERE id_request = :idRequest", nativeQuery = true)
    int requestFail(long idRequest, String status);


    @Override
    Optional<Request> findById(Long aLong);

    List<Request> findByStatus(String status);


    List<Request> findByTypeRequest(String type);
}
