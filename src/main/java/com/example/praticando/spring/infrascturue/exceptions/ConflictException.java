package com.example.praticando.spring.infrascturue.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
    public ConflictException(String mensagem, Throwable throwable){
        super(mensagem);
    }
}
