package com.MS_Customer.client.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressByCep {
    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

    private String cep;

}
