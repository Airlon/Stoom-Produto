package br.com.stoom.store.service;

import br.com.stoom.store.dto.CategoryDto;
import br.com.stoom.store.exception.CategoryExceptions;
import br.com.stoom.store.product.Category;
import br.com.stoom.store.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllActiveCategories() {
        log.info("Getting all active categories.");
        List<Category> activeCategories = categoryRepository.findByAtivo(true);
        return activeCategories.stream().map(this::convert).collect(Collectors.toList());
    }

    public CategoryDto convert(Category category) {
        CategoryDto result = new CategoryDto();
        result.setId(category.getId());
        result.setNome(category.getNome());
        return result;
    }

    public ResponseEntity<String> activateCategoryById(long id) {
        log.info("Activating category with id: {}", id);
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setAtivo(true);
            categoryRepository.save(category);
            log.info("Category activated successfully with id: {}", id);
            return ResponseEntity.ok("Categoria ativada com sucesso.");
        } else {
            log.info("Category with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deactivateCategoryById(long id) {
        log.info("Deactivating category with id: {}", id);
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setAtivo(false);
            categoryRepository.save(category);
            log.info("Category deactivated successfully with id: {}", id);
            return ResponseEntity.ok("Categoria desativada com sucesso.");
        } else {
            log.info("Category with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    public List<CategoryDto> getAllInactiveCategories() {
        log.info("Getting all inactive categories.");
        List<Category> inactiveCategories = categoryRepository.findByAtivo(false);
        return inactiveCategories.stream().map(this::convert).collect(Collectors.toList());
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        log.info("Creating category: {}", categoryDto);
        List<Category> existingCategory = categoryRepository.findByNome(categoryDto.getNome());

        if (!existingCategory.isEmpty()) {
            throw new CategoryExceptions();
        }

        Category newCategory = new Category();
        newCategory.setAtivo(categoryDto.isAtivo());
        newCategory.setNome(categoryDto.getNome());
        newCategory.setDtCadastro(categoryDto.getDtCadastro());

        categoryRepository.save(newCategory);

        categoryDto.setId(newCategory.getId());
        return categoryDto;
    }
}