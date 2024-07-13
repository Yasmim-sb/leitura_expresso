package com.MS_Customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CustomerNewPasswordDTO {
    @Size(min = 6, message = "Password need at last 6 characters")
    @NotBlank (message = "Password don't be blank")
    private String newPassword;
}
