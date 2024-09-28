package com.college.directory.model;

import com.college.directory.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String name;
    private String email;
    private String phone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the role into a SimpleGrantedAuthority for Spring Security
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        // Can add logic to check if the account has expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Can add logic to check if the account is locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Can add logic to check if the credentials have expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        // You can control whether the user account is enabled
        return true;
    }
}

