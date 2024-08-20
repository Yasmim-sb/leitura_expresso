package com.leituraexpresso.challenge.mscustomer.entities;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.dto.requests.CustomerRequestDTO;
import com.leituraexpresso.challenge.mscustomer.entities.validate.ValidAge;
import com.leituraexpresso.challenge.mscustomer.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    @Column(unique = true)
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}",
            message = "CPF must be in the format 000.000.000-00")
    private String cpf;

    @NotNull(message = "A data de nascimento não pode ser nula.")
    @ValidAge
    private LocalDate birthdate;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    @NotBlank
    private String password;

    private boolean active = true;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Address> addressList = new ArrayList<>();

    public Customer(CustomerDTO customerDTO, Customer customer){
        id = customerDTO.getId();
        firstName = customerDTO.getFirstName();
        lastName = customerDTO.getLastName();
        sex = customerDTO.getSex();
        cpf = customerDTO.getCpf();
        birthdate = customerDTO.getBirthdate();
        email = customerDTO.getEmail();
        password = customerDTO.getPassword();
        active = customerDTO.isActive();
        addressList.addAll(
            customerDTO.getAddressList().stream()
            .map(address -> new Address(address, customer)).toList()
        );
    }

    public Customer(CustomerDTO customerDTO){
        id = customerDTO.getId();
        firstName = customerDTO.getFirstName();
        lastName = customerDTO.getLastName();
        sex = customerDTO.getSex();
        cpf = customerDTO.getCpf();
        birthdate = customerDTO.getBirthdate();
        email = customerDTO.getEmail();
        password = customerDTO.getPassword();
        active = customerDTO.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;  // O email será usado como o nome de usuário.
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
