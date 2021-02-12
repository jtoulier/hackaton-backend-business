package com.hackaton.business.backend.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "credit_line", schema = "group1")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dni")
    private String dni;

    @Column(name = "authorized_amount")
    private double authorizedAmount;

    @Column(name = "used_amount")
    private double usedAmount;

}
