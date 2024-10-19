package com.lifters.eleicoesapp.domain.exception;

public class RelatorioJasperException extends RuntimeException{
    public RelatorioJasperException(String message) {
        super(message);
    }

    public RelatorioJasperException(String message, Throwable cause) {
        super(message, cause);
    }
}
