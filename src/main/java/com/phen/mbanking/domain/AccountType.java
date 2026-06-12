package com.phen.mbanking.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class AccountType {

    private Integer id;
    private String alias;
    private String name;
    private String description;


    private Boolean idDeleted;
}
