package com.hackaton.business.backend.repository;

import com.hackaton.business.backend.repository.entity.AvailableCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableCardRepository extends JpaRepository<AvailableCard, Long> {
}
