package com.hackaton.business.backend.repository;

import com.hackaton.business.backend.repository.entity.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualCardRepository extends JpaRepository<VirtualCard, Long> {
}
