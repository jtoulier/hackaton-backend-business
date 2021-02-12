package com.hackaton.business.backend.repository;

import com.hackaton.business.backend.repository.entity.CreditLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditLineRepository extends JpaRepository<CreditLine, Long> {

    @Query(value = "SELECT c.* FROM credit_line c "
            + "WHERE c.dni =:dni ", nativeQuery = true)
    Optional<CreditLine> findCreditLineByDni(String dni);

}
