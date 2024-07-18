package com.MS_Customer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerNewPasswordRequest {
    @Size(min = 6, message = "Password need at last 6 characters")
    @NotBlank (message = "Password don't be blank")
    private String newPassword;
}
