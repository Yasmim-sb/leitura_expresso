package com.MS_Customer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRequest {
    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "Number cannot be blank")
    private String number;

    @NotBlank(message = "CEP cannot be blank")
    private String cep;

    @NotBlank(message = "District cannot be blank")
    private String district;

    private String complement;

    @NotNull(message = "Custom ID cannot be null")
    private Long customerId;
}
