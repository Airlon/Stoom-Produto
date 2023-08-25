package br.com.stoom.store.service;

import br.com.stoom.store.dto.CategoryDto;
import br.com.stoom.store.exception.CategoryExceptions;
import br.com.stoom.store.product.Category;
import br.com.stoom.store.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllActiveCategories() {
        List<Category> activeCategories = new ArrayList<>();
        when(categoryRepository.findByAtivo(true)).thenReturn(activeCategories);

        List<CategoryDto> result = categoryService.getAllActiveCategories();

        verify(categoryRepository, times(1)).findByAtivo(true);
    }

    @Test
    public void testActivateCategoryById() {
        long id = 1L;
        Category category = new Category();
        category.setId(id);
        category.setAtivo(false);

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        ResponseEntity<String> result = categoryService.activateCategoryById(id);

        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testDeactivateCategoryById() {
        long id = 1L;
        Category category = new Category();
        category.setId(id);
        category.setAtivo(true);

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        ResponseEntity<String> result = categoryService.deactivateCategoryById(id);

        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testGetAllInactiveCategories() {
        List<Category> inactiveCategories = new ArrayList<>();
        when(categoryRepository.findByAtivo(false)).thenReturn(inactiveCategories);

        List<CategoryDto> result = categoryService.getAllInactiveCategories();

        verify(categoryRepository, times(1)).findByAtivo(false);
    }

    @Test(expected = CategoryExceptions.class)
    public void testCreateExistingCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setNome("Eletronics");

        List<Category> existingCategories = new ArrayList<>();
        existingCategories.add(new Category());

        when(categoryRepository.findByNome(categoryDto.getNome())).thenReturn(existingCategories);

        categoryService.createCategory(categoryDto);

        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setNome("Clothing");
        categoryDto.setAtivo(true);
        categoryDto.setDtCadastro(Timestamp.valueOf(LocalDateTime.now()));

        when(categoryRepository.findByNome(categoryDto.getNome())).thenReturn(new ArrayList<>());
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());

        categoryService.createCategory(categoryDto);

        verify(categoryRepository, times(1)).save(any(Category.class));
    }
}
