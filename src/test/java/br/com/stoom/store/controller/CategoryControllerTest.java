package br.com.stoom.store.controller;

import br.com.stoom.store.constantes.RabbitMQConstants;
import br.com.stoom.store.dto.CategoryDto;
import br.com.stoom.store.service.CategoryService;
import br.com.stoom.store.service.RabbitMQService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private RabbitMQService rabbitMQService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllActiveCategories() {
        List<CategoryDto> categories = new ArrayList<>();
        when(categoryService.getAllActiveCategories()).thenReturn(categories);

        List<CategoryDto> result = categoryController.getAllActiveCategories();

        verify(categoryService, times(1)).getAllActiveCategories();
    }

    @Test
    public void testActivateCategory() {
        long id = 1L;
        when(categoryService.activateCategoryById(anyLong())).thenReturn(ResponseEntity.ok("Success"));

        ResponseEntity<String> result = categoryController.activateCategory(id);

        verify(categoryService, times(1)).activateCategoryById(id);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_CATEGORY), eq(id));
    }

    @Test
    public void testDeactivateCategory() {
        long id = 1L;
        when(categoryService.deactivateCategoryById(anyLong())).thenReturn(ResponseEntity.ok("Success"));

        ResponseEntity<String> result = categoryController.deactivateCategory(id);

        verify(categoryService, times(1)).deactivateCategoryById(id);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_CATEGORY), eq(id));
    }

    @Test
    public void testGetAllInactiveCategories() {
        List<CategoryDto> categories = new ArrayList<>();
        when(categoryService.getAllInactiveCategories()).thenReturn(categories);

        List<CategoryDto> result = categoryController.getAllInactiveCategories();

        verify(categoryService, times(1)).getAllInactiveCategories();
    }

    @Test
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.createCategory(categoryDto)).thenReturn(categoryDto);

        CategoryDto result = categoryController.createCategory(categoryDto);

        verify(categoryService, times(1)).createCategory(categoryDto);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_CATEGORY), eq(categoryDto));
    }
}