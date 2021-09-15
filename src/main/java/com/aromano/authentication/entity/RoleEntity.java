package com.aromano.authentication.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "role")
@Data
public class RoleEntity implements GrantedAuthority {

    public RoleEntity(String name) {
        this.name = name;
    }

    public RoleEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    @Override
    public String getAuthority() {
        return  this.name;
    }
}
