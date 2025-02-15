package com.mpena.jobtrackerv2.components.users.model;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "users")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name="id", updatable=false, nullable=false, unique=true)
    private Long id;

    @Column(name="authority", nullable=false, unique=true)
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    @ToString.Exclude
    private Set<Users> users;

    @Override
    public String getAuthority() {
        return authority;
    }

}
