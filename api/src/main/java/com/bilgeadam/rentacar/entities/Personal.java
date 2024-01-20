package com.bilgeadam.rentacar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "personal", schema = "rent")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Personal implements UserDetails {

    @Id
    @GeneratedValue(generator = "personal_id_generator")
    @SequenceGenerator(name = "personal_id_generator", schema = "rent", sequenceName = "personal_id_seq", allocationSize = 1)
    private Long id;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    private String surname;
    private String password;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonBackReference
    private Address address;

    private String roles;

    @Transient
    private String fullName;

    public String getFullName() {
        return this.firstName + " " + this.surname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles));
    }

    @Override
    public String getUsername() {
        return email;
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
