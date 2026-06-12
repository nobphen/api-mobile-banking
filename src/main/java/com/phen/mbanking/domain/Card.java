package com.phen.mbanking.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_cards")

public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 12, nullable = false, unique = true)
    private String number;

    @Column(length = 100, nullable = false)
    private String holder;

    @Column(nullable = false)
    private Integer cvv;

    @Column(nullable = false)
    private LocalDate issuedAt;

    @Column(nullable = false)
    private LocalDate expiredAt;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    private CardType cardType;

    @OneToOne(mappedBy = "card")
    private Account account;

}
