package com.bedu.tarjetas.repositories;


import com.bedu.tarjetas.entities.UserOld;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserOld, Long> {

    Optional<UserOld> findByUsername(String username);
}
