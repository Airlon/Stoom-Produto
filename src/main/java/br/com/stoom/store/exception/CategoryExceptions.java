package br.com.stoom.store.exception;

public class CategoryExceptions extends RuntimeException {
    public CategoryExceptions() {
        super("A categoria já está cadastrada.");
    }
}
