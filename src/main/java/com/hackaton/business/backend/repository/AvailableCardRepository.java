package com.hackaton.business.backend.repository;

import com.hackaton.business.backend.repository.entity.AvailableCard;
import com.hackaton.business.backend.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableCardRepository extends JpaRepository<AvailableCard, Long> {
    @Query(value = "SELECT a.* FROM group1.available_card a "
            + "WHERE a.available = 'Y' ", nativeQuery = true)
    List<AvailableCard> getAllByAvailable();
}
