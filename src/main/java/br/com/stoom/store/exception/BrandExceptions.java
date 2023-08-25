package br.com.stoom.store.exception;

public class BrandExceptions extends RuntimeException {
    public BrandExceptions() {
        super("A marca já está cadastrada.");
    }
 }

