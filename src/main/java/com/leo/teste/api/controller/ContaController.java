package com.leo.teste.api.controller;

import com.leo.teste.api.dto.ContaDTO;

import com.leo.teste.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaDTO> createConta(@RequestBody ContaDTO conta){
        ContaDTO contaSalva = contaService.createConta(conta);
        return new ResponseEntity<>(contaSalva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ContaDTO> getContaById(@RequestParam("numero_conta") Long numeroConta){
        ContaDTO conta = contaService.getContaById(numeroConta);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

}
