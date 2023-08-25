package br.com.stoom.store.controller;

import br.com.stoom.store.constantes.RabbitMQConstants;
import br.com.stoom.store.dto.CategoryDto;
import br.com.stoom.store.service.CategoryService;
import br.com.stoom.store.service.RabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping("/active")
    public List<CategoryDto> getAllActiveCategories() {
        log.info("Getting all active categories.");
        return categoryService.getAllActiveCategories();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateCategory(@PathVariable long id) {
        log.info("Activating category with id: {}", id);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_CATEGORY, id);
        ResponseEntity<String> response = categoryService.activateCategoryById(id);
        return response;
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateCategory(@PathVariable long id) {
        log.info("Deactivating category with id: {}", id);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_CATEGORY, id);
        ResponseEntity<String> response = categoryService.deactivateCategoryById(id);
        return response;
    }

    @GetMapping("/inactive")
    public List<CategoryDto> getAllInactiveCategories() {
        log.info("Getting all inactive categories.");
        return categoryService.getAllInactiveCategories();
    }

    @PostMapping
    @ResponseBody
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        log.info("Creating category: {}", categoryDto);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_CATEGORY, categoryDto);
        return categoryService.createCategory(categoryDto);
    }

}