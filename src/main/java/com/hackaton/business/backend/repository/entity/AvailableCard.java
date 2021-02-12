package com.hackaton.business.backend.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "available_card", schema = "group1")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableCard {

    @Id
    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "available")
    private String available;


    @OneToMany(targetEntity = VirtualCard.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_number", referencedColumnName = "card_number")
    private List<VirtualCard> virtualCards;

}
