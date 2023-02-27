package com.bedu.tarjetas.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u.id, u.email, u.firstname, u.lastname, u.password, u.role " +
                   "FROM " +
                   "    _user u, token t " +
                   "WHERE " +
                   "    u.id = t.user_id " +
                   "AND " +
                   "    t.token = :token", nativeQuery = true)
    Optional<User> validateToken(String token);
}

