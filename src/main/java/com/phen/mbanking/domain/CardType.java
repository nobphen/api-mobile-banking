package com.phen.mbanking.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_card_types")

public class CardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50,nullable = false,unique = true)
    private String name;

    private Boolean isDeleted;

    @OneToMany(mappedBy = "cardType")
    private List<Card> cards;
}
