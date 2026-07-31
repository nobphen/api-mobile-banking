package com.phen.mbanking.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_roles")

public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /// USER , CUSTOMER , MANAGER ,ADMIN
    private  String name ;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Override
    public @Nullable String getAuthority() {
        return "ROLE_" + name;
    }
}
