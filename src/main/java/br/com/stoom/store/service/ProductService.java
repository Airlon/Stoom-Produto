package br.com.stoom.store.service;


import br.com.stoom.store.dto.ProductDto;
import br.com.stoom.store.product.Brand;
import br.com.stoom.store.product.Category;
import br.com.stoom.store.product.Product;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    public ResponseEntity<List<String>> insertMultipleProducts(List<ProductDto> productDtoList) {
        log.info("Inserting multiple products.");

        List<ProductDto> insertedProducts = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        for (ProductDto productDto : productDtoList) {
            log.info("Processing product: {}", productDto.getNome());

            Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoria().getId());
            if (!categoryOptional.isPresent()) {
                errorMessages.add("Categoria inválida para o produto: " + productDto.getNome());
                log.warn("Invalid category for product: {}", productDto.getNome());
                continue;
            }
            Category categoryEntity = categoryOptional.get();

            Optional<Brand> brandOptional = brandRepository.findById(productDto.getMarca().getId());
            if (!brandOptional.isPresent()) {
                errorMessages.add("Marca inválida para o produto: " + productDto.getNome());
                log.warn("Invalid brand for product: {}", productDto.getNome());
                continue;
            }
            Brand brandEntity = brandOptional.get();

            Product product = new Product();
            product.setNome(productDto.getNome());
            product.setDescricao(productDto.getDescricao());
            product.setPreco(productDto.getPreco());
            product.setQuantidade(productDto.getQuantidade());
            product.setDtCadastro(Timestamp.from(Instant.now()));
            product.setAtivo(productDto.isAtivo());
            product.setCategoria(categoryEntity);
            product.setMarca(brandEntity);

            repository.save(product);

            productDto.setId(product.getId());
            insertedProducts.add(productDto);
        }

        if (!errorMessages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }

        return ResponseEntity.ok(Collections.singletonList("Produto criado com sucesso"));
    }

    public List<ProductDto> getAll() {
        log.info("Getting all products.");
        return repository
                .findByAtivo(true)
                .stream()
                .map(ConverterService::convert)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getProductsByBrandName(String brandName) {
        log.info("Getting products by brand: {}", brandName);
        return repository.findByMarcaNomeAndAtivo(brandName, true)
                .stream()
                .map(ConverterService::convertBrand)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getProductsByCategoryName(String categoryName) {
        log.info("Getting products by category: {}", categoryName);
        return repository.findByCategoriaNomeAndAtivo(categoryName, true)
                .stream()
                .map(ConverterService::convertCategory)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> inactivateProductById(long id) {
        log.info("Inactivating product with id: {}", id);
        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setAtivo(false);
            repository.save(product);
            log.info("Product inactivated successfully with id: {}", id);
            return ResponseEntity.ok("Produto inativado com sucesso.");
        } else {
            log.info("Product with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> activateProductById(long id) {
        log.info("Activating product with id: {}", id);
        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setAtivo(true);
            repository.save(product);
            log.info("Product activated successfully with id: {}", id);
            return ResponseEntity.ok("Produto ativado com sucesso.");
        } else {
            log.info("Product with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }
}
