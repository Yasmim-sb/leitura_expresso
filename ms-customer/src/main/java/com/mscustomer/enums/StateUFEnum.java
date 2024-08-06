package com.MS_Customer.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StateUFEnum {
    AM("Amazonas"),
    AL("Alagoas"),
    AC("Acre"),
    AP("Amapá"),
    BA("Bahia"),
    PA("Pará"),
    MT("Mato Grosso"),
    MG("Minas Gerais"),
    MS("Mato Grosso do Sul"),
    GO("Goiás"),
    MA("Maranhão"),
    RS("Rio Grande do Sul"),
    TO("Tocantins"),
    PI("Piauí"),
    SP("São Paulo"),
    RO("Rondônia"),
    RR("Roraima"),
    PR("Paraná"),
    CE("Ceará"),
    PE("Pernambuco"),
    SC("Santa Catarina"),
    PB("Paraíba"),
    RN("Rio Grande do Norte"),
    ES("Espírito Santo"),
    RJ("Rio de Janeiro"),
    SE("Sergipe"),
    DF("Distrito Federal");

    private final String nome;
}
