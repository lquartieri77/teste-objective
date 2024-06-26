package com.leo.teste.api.service;

import com.leo.teste.api.domain.Conta;
import com.leo.teste.api.dto.ContaDTO;
import org.springframework.data.domain.Page;

public interface ContaService {
    /**
     * Cria uma nova conta
     * @param conta
     * @return ContaDTO
     */
    ContaDTO createConta(ContaDTO conta);

    /**
     * Retorna a conta por id (caso nao exista, retoran um 404)
     * @param contaId
     * @return ContaDTO
     */
    ContaDTO getContaById(Long contaId);

    /**
     * Retorna uma lista paginada das contas existentes
     * @param pageNumber
     * @param qtdElementos
     * @return Page
     */
    Page getAllContas(int pageNumber, int qtdElementos);

}
