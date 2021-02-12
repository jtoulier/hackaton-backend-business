package com.hackaton.business.backend.repository;

import com.hackaton.business.backend.repository.entity.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirtualCardRepository extends JpaRepository<VirtualCard, Long> {

    @Query(nativeQuery = true, value = " " +
            "select v.* " +
            " from group1.virtual_card v " +
            " where v.dni =:dni ")
    List<VirtualCard> getAllByDni(String dni);

}
