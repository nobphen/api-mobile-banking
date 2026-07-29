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
@Table(name = "tb_roles")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /// USER , CUSTOMER , MANAGER ,ADMIN
    private  String name ;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
