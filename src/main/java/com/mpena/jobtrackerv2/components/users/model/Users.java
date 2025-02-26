package com.mpena.jobtrackerv2.components.users.model;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "authorities")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name="id", updatable=false, nullable=false, unique = true)
    private long id;
    
    @Column(name="username", length=50, nullable=false, unique = true)
    @Size(max=50, min=2, message = "username must between 2 and 50 chars")
    private String username;

    @Column(name="password", nullable=false)
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable
    (name = "user_authority", joinColumns = @JoinColumn(name="user_id"), 
        inverseJoinColumns= @JoinColumn(name="authority_id"))
    private Collection<Authority> authorities;

    @Override
    public Collection<Authority> getAuthorities() {
        return authorities;
    }
    
}
