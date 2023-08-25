package br.com.stoom.store.exception;

public class ProductExceptions extends RuntimeException {
    public ProductExceptions() {
        super("A produto já está cadastrada.");
    }
 }

