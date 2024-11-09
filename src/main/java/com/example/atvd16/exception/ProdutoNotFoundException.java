package com.example.atvd16.exception;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(Long id) {
        super("Produto com ID " + id + " não encontrado.");
    }
}

