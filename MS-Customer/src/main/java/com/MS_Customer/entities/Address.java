package com.MS_Customer.entities;

import com.MS_Customer.client.models.AddressByCep;
import com.MS_Customer.request.AddressRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Customer customerId;

    public Address(AddressByCep byCep, AddressRequest request){
        this.state = byCep.getUf().getNome();
        this.city = byCep.getLocalidade();
        this.district = byCep.getBairro();
        this.street = request.getStreet();
        this.number = request.getNumber();
        this.cep = request.getCep();
        this.complement = request.getComplement();
    }
}
