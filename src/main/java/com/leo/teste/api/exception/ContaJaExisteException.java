package com.leo.teste.api.exception;

public class ContaJaExisteException extends RuntimeException {
    public ContaJaExisteException(String msg){
        super(msg);
    }
}
