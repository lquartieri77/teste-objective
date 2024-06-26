package com.leo.teste.api.service;

import com.leo.teste.api.dto.TransacaoDTO;
import com.leo.teste.api.exception.TransacaoNaoRealizadaException;

public interface TransacaoService {
    /**
     * Cria uma nova transacao
     * @param transacao
     * @return TransacaoDTO
     */
    TransacaoDTO criarTransacao(TransacaoDTO transacao) throws TransacaoNaoRealizadaException;

}
