package com.bedu.tarjetas.repositories;

import com.bedu.tarjetas.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM locations " +
            "WHERE free_space >= :numberCards " +
            "limit 1", nativeQuery = true)
    Optional<Location> getLocation(int numberCards);

    @Query(value = "SELECT * FROM locations " +
            "WHERE free_space >= :numberCards " +
            "AND id_location = :idLocation", nativeQuery = true)
    Optional<Location> getLocationById(int numberCards, long idLocation);

    @Modifying
    @Query(value = "UPDATE locations " +
            "SET free_space = free_space - :numberCards, " +
            "space_used = space_used + :numberCards " +
            "WHERE id_location = :idLocation", nativeQuery = true)
    int updateSpace(int numberCards, long idLocation);


    public Optional<Location> findByIdLocation(long idLocation);
    @Modifying
    @Transactional
    @Query(value = "UPDATE locations " +
            "SET capacity = :capacity, " +
            "free_space = :capacity " +
            "WHERE id_location = :idLocation", nativeQuery = true)
    int updateCapacitySpace(long idLocation, int capacity);

    @Modifying
    @Query(value = "UPDATE locations " +
            "SET free_space = free_space + :numberCards, " +
            "space_used = space_used - :numberCards " +
            "WHERE id_location = :idLocation", nativeQuery = true)
    int updateSpaceAdd(long idLocation, int numberCards);

}
