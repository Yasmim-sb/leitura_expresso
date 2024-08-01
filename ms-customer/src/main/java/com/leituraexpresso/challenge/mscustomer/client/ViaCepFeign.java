package com.leituraexpresso.challenge.mscustomer.client;

import com.leituraexpresso.challenge.mscustomer.client.models.AddressByCep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepFeign {
    @GetMapping("{cep}/json")
    AddressByCep searchLocationByCep(@PathVariable("cep") String cep);
}
