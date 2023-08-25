package br.com.stoom.store.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BrandExceptions.class)
    public ResponseEntity<String> handleBrandException(BrandExceptions ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }

    @ExceptionHandler(CategoryExceptions.class)
    public ResponseEntity<String> handleCategoryException(CategoryExceptions ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }

    @ExceptionHandler(ProductExceptions.class)
    public ResponseEntity<String> handleProductException(ProductExceptions ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }

}
