package com.leo.teste.api.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A criacao deste DTO visa trafegar as informacoes necessarias e nao as de dominio nem meta-dados de persistencia
 */
@Getter
@Setter
public class ContaDTO implements Serializable {

    private Long id;
    @JsonProperty("numero_conta")
    private Integer numeroConta;
    private BigDecimal saldo;

    public ContaDTO(
            Long id,
            Integer numeroConta,
            BigDecimal saldo
    ){
        this.setId(id);
        this.setNumeroConta(numeroConta);
        this.setSaldo(saldo);

    }



}
