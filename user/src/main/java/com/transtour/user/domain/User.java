package com.transtour.user.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",schema = "transtour")
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    private String id;

    @Column(name = "dni")
    private String dni;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "")

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<SessionTokens> sessionTokens;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    private FirebaseToken firebaseToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities
                .add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public String getUsername() {
        return dni;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}