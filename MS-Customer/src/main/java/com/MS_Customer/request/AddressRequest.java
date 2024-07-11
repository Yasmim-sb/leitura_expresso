package com.MS_Customer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP must be exactly 8 characters, or in format xxxxx-xxx")
    private String cep;

    @NotBlank(message = "District cannot be blank")
    private String district;

    private String complement;

    @NotNull(message = "Custom ID cannot be null")
    private Long customerId;
}
