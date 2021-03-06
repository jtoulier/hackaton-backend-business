package com.hackaton.business.backend.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "virtual_card", schema = "group1")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VirtualCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dni")
    private String dni;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "authorized_amount")
    private double authorizedAmount;

    @Column(name = "used_amount")
    private double usedAmount;

    @Column(name = "cvv")
    private String cvv;

}
