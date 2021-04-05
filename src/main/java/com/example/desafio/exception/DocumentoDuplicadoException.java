package com.example.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Dcoumento j esta sendo utilizado")
public class DocumentoDuplicadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DocumentoDuplicadoException(String exception) {
        super(exception);
    }
}
