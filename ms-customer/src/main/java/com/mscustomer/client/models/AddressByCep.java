package com.MS_Customer.client.models;

import com.MS_Customer.enums.StateUFEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AddressByCep {
    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private StateUFEnum uf;

    private String cep;

    private String erro = "false";

}
