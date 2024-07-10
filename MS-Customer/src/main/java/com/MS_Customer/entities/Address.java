package com.MS_Customer.entities;

import com.MS_Customer.client.models.AddressByCep;
import com.MS_Customer.request.AddressRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    @Pattern(regexp = "\\d{5}-?\\d{3}",
    message = "Invalid CEP")
    private String cep;

    private String complement;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Customer customerId;

    public Address(AddressByCep byCep, AddressRequest request, Customer customer){
        this.state = byCep.getUf().getNome();
        this.city = byCep.getLocalidade();
        this.district = request.getDistrict();
        this.street = request.getStreet();
        this.number = request.getNumber();
        this.cep = request.getCep();
        this.complement = request.getComplement();
        this.customerId = customer;
    }
}
