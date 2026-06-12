package com.phen.mbanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_accounts")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alias;
    private String accountName;
    private String accountNo;
    private BigDecimal balance;
    private BigDecimal transferLimit;
    private Boolean isHidden;

    @ManyToOne
    private AccountType accountType;

    @OneToOne
    private Card card;

    @ManyToOne
    @JoinTable(
            name = "user_accounts",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private User user;

    @OneToMany(mappedBy = "owner")
    private List<Transaction> transactionsOfOwner;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> transactionsOfReceiver;
}
