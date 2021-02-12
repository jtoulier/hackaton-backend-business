package com.hackaton.business.backend.repository.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customer", schema = "group1")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "dni")
    private String dni;

    @Column(name = "password")
    private String password;

    @Column(name = "first_surname")
    private String firstSurname;

    @Column(name = "second_surname")
    private String secondSurname;

    @Column(name = "given_name")
    private String givenName;

    @OneToMany(targetEntity = CreditLine.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private List<CreditLine> creditLineList;

    @OneToMany(targetEntity = VirtualCard.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private List<VirtualCard> virtualCards;

}
