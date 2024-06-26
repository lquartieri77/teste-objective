package com.leo.teste.api.exception;

public class TransacaoNaoRealizadaException extends RuntimeException {
    public TransacaoNaoRealizadaException(String msg){
        super(msg);
    }
}
