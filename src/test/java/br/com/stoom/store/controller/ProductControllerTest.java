package br.com.stoom.store.controller;

import br.com.stoom.store.constantes.RabbitMQConstants;
import br.com.stoom.store.dto.ProductDto;
import br.com.stoom.store.service.ProductService;
import br.com.stoom.store.service.RabbitMQService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private RabbitMQService rabbitMQService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        List<ProductDto> products = new ArrayList<>();
        when(productService.getAll()).thenReturn(products);

        List<ProductDto> result = productController.getAll();

        verify(productService, times(1)).getAll();
    }

    @Test
    public void testPost() {
        List<ProductDto> productList = new ArrayList<>();
        when(productService.insertMultipleProducts(anyList())).thenReturn(ResponseEntity.ok(new ArrayList<>()));

        ResponseEntity<List<String>> result = productController.post(productList);

        verify(productService, times(1)).insertMultipleProducts(productList);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_PRODUCT), eq(productList));
    }

    @Test
    public void testGetByBrand() {
        String brandName = "Nike";
        List<Map<String, Object>> products = new ArrayList<>();
        when(productService.getProductsByBrandName(anyString())).thenReturn(products);

        List<Map<String, Object>> result = productController.getByBrand(brandName);

        verify(productService, times(1)).getProductsByBrandName(brandName);
    }

    @Test
    public void testGetByCategory() {
        String categoryName = "Electronics";
        List<Map<String, Object>> products = new ArrayList<>();
        when(productService.getProductsByCategoryName(anyString())).thenReturn(products);

        List<Map<String, Object>> result = productController.getByCategory(categoryName);

        verify(productService, times(1)).getProductsByCategoryName(categoryName);
    }

    @Test
    public void testInactivateProduct() {
        long id = 1L;
        when(productService.inactivateProductById(anyLong())).thenReturn(ResponseEntity.ok("Success"));

        ResponseEntity<String> result = productController.inactivateProduct(id);

        verify(productService, times(1)).inactivateProductById(id);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_PRODUCT), eq(id));
    }

    @Test
    public void testActivateProduct() {
        long id = 1L;
        when(productService.activateProductById(anyLong())).thenReturn(ResponseEntity.ok("Success"));

        ResponseEntity<String> result = productController.activateProduct(id);

        verify(productService, times(1)).activateProductById(id);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_PRODUCT), eq(id));
    }
}
