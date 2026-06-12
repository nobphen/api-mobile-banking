package com.phen.mbanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_transactions")

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Account owner;

    @ManyToOne
    private Account receiver;

    private String paymentReceiver;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(length = 30, nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private LocalDateTime transactionAt;

    @Column(nullable = false)
    private Boolean status;


    @Column(nullable = false)
    private Boolean isDeleted;

}
