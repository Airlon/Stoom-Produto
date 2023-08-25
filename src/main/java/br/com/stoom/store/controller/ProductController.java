package br.com.stoom.store.controller;


import br.com.stoom.store.constantes.RabbitMQConstants;
import br.com.stoom.store.dto.ProductDto;
import br.com.stoom.store.service.ProductService;
import br.com.stoom.store.service.RabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value =  "/product", produces =  MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping
    @ResponseBody
    public List<ProductDto> getAll() {
        log.info("Getting all products.");
        return productService.getAll();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<List<String>> post(@RequestBody List<ProductDto> productList) {
        log.info("Creating multiple products: {}", productList);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_PRODUCT, productList);
        return productService.insertMultipleProducts(productList);
    }

    @GetMapping("/brand/{marca}")
    @ResponseBody
    public List<Map<String, Object>> getByBrand(@PathVariable String marca) {
        log.info("Getting products by brand: {}", marca);
        return productService.getProductsByBrandName(marca);
    }

    @GetMapping("/category/{categoria}")
    @ResponseBody
    public List<Map<String, Object>> getByCategory(@PathVariable String categoria) {
        log.info("Getting products by category: {}", categoria);
        return productService.getProductsByCategoryName(categoria);
    }

    @PatchMapping("/{id}/inactivate")
    public ResponseEntity<String> inactivateProduct(@PathVariable long id) {
        log.info("Inactivating product with id: {}", id);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_PRODUCT, id);
        return productService.inactivateProductById(id);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateProduct(@PathVariable long id) {
        log.info("Activating product with id: {}", id);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_PRODUCT, id);
        ResponseEntity<String> response = productService.activateProductById(id);
        return response;
    }
}
