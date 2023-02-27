package com.bedu.tarjetas.repositories;

import com.bedu.tarjetas.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBranchRepository extends JpaRepository<Branch, Long> {

    public Optional<Branch> findOneByNumberBranch(String numberBranch);
}
