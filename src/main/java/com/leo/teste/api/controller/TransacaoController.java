package com.leo.teste.api.controller;

import com.leo.teste.api.dto.TransacaoDTO;
import com.leo.teste.api.service.ContaService;
import com.leo.teste.api.service.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transacao")
public class TransacaoController {

    Logger logger = LoggerFactory.getLogger(TransacaoController.class);

    @Autowired
    private ContaService contaService;

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoDTO> createTransacao(@RequestBody TransacaoDTO transacao){
        logger.info("POST - createTransacao - dto = " + transacao.toString());
        TransacaoDTO transacaoRetorno = transacaoService.criarTransacao(transacao);
        logger.debug("createTransacao - criado com sucesso");
        return new ResponseEntity<>(transacaoRetorno, HttpStatus.CREATED);
    }

}
