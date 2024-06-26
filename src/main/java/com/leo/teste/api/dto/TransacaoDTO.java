package com.leo.teste.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leo.teste.api.enums.TipoTransacaoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A criacao deste DTO visa trafegar as informacoes necessarias e nao as de dominio nem meta-dados de persistencia
 */
@Getter
@Setter
public class TransacaoDTO implements Serializable {

    private Long id;
    @JsonProperty("numero_conta")
    private Integer numeroConta;
    @JsonProperty("forma_pagamento")
    private char tipoTransacaoChar;
    private TipoTransacaoEnum tipoTransacaoEnum;
    private ContaDTO conta;
    private BigDecimal valor;
    private LocalDate dataHora;

    public TransacaoDTO(
            Long id,
            Integer numeroConta,
            char tipoTransacaoChar,
            TipoTransacaoEnum tipoTransacaoEnum,
            ContaDTO conta,
            BigDecimal valor,
            LocalDate dataHora
    ){
        this.setId(id);
        this.setNumeroConta(numeroConta);
        this.setTipoTransacaoChar(tipoTransacaoChar);
        this.setTipoTransacaoEnum(tipoTransacaoEnum);
        this.setConta(conta);
        this.setValor(valor);
        this.setDataHora(dataHora);

    }

    @Override
    public String toString() {
        return "TransacaoDTO{" +
                "id=" + id +
                ", numeroConta=" + numeroConta +
                ", tipoTransacaoChar=" + tipoTransacaoChar +
                ", tipoTransacaoEnum=" + tipoTransacaoEnum +
                ", conta=" + conta +
                ", valor=" + valor +
                ", dataHora=" + dataHora +
                '}';
    }
}
