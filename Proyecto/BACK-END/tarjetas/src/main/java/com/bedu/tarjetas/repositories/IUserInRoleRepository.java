package com.bedu.tarjetas.repositories;

import com.bedu.tarjetas.entities.UserOld;
import com.bedu.tarjetas.entities.UserInRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserInRoleRepository extends JpaRepository<UserInRole, Long> {
    List<UserInRole> findByUser(UserOld user);
}
