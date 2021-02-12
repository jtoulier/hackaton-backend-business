package com.hackaton.business.backend.repository;

import com.hackaton.business.backend.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c.* FROM customer c "
            + "WHERE c.dni =:dni AND c.password =:password ", nativeQuery = true)
    Optional<Customer> findCustomerByDniAndPassword(String dni, String password);

}
