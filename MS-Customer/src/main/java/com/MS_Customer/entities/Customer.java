package com.MS_Customer.entities;

import com.MS_Customer.enums.CustomerRole;
import com.MS_Customer.enums.SexEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

    private SexEnum sex;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
            message = "CPF must be in the format 000.000.000-00")
    //@Column(unique = true) - Está comentado pois durante os testes eu não quero ficar trocando informações
    private String cpf;

    private LocalDate birthdate;

    //@Column(unique = true) -  Está comentado pois durante ostestes eu não quero ficar trocando informações
    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    @NotBlank
    private String password;

    private boolean active = true;

    private CustomerRole role;

    public Customer(String email, String password, CustomerRole role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == CustomerRole.REGISTERED_CUSTOMER) return List.of(new SimpleGrantedAuthority("ROLE_REGISTERED_CUSTOMER"), new SimpleGrantedAuthority("ROLE_UNREGISTERED_CUSTOMER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_UNREGISTERED_CUSTOMER"));
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
        return active;
    }
}
