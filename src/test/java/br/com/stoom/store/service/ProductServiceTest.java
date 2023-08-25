package br.com.stoom.store.service;

import br.com.stoom.store.dto.ProductDto;
import br.com.stoom.store.product.Product;
import br.com.stoom.store.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        List<Product> products = new ArrayList<>();

        when(productRepository.findByAtivo(true)).thenReturn(products);

        List<ProductDto> result = productService.getAll();

        verify(productRepository, times(1)).findByAtivo(true);
    }

    @Test
    public void testGetProductsByBrandName() {
        String brandName = "SomeBrand";
        List<Product> products = new ArrayList<>();

        when(productRepository.findByMarcaNomeAndAtivo(brandName, true)).thenReturn(products);

        List<Map<String, Object>> result = productService.getProductsByBrandName(brandName);

        verify(productRepository, times(1)).findByMarcaNomeAndAtivo(brandName, true);
    }

    @Test
    public void testGetProductsByCategoryName() {
        String categoryName = "SomeCategory";
        List<Product> products = new ArrayList<>();

        when(productRepository.findByCategoriaNomeAndAtivo(categoryName, true)).thenReturn(products);

        List<Map<String, Object>> result = productService.getProductsByCategoryName(categoryName);

        verify(productRepository, times(1)).findByCategoriaNomeAndAtivo(categoryName, true);
    }

    @Test
    public void testInactivateProductById() {
        long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setAtivo(true);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);

        ResponseEntity<String> result = productService.inactivateProductById(id);

        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(ArgumentMatchers.any(Product.class));
    }

    @Test
    public void testActivateProductById() {
        long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setAtivo(false);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);

        ResponseEntity<String> result = productService.activateProductById(id);

        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(ArgumentMatchers.any(Product.class));
    }
}
