package com.MS_Customer.client.models;

import com.MS_Customer.enums.StateUFEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AddressByCep {
    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private StateUFEnum uf;

    private String cep;

    public String erro = "false";

}
