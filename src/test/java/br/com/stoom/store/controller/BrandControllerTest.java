package br.com.stoom.store.controller;

import br.com.stoom.store.constantes.RabbitMQConstants;
import br.com.stoom.store.dto.BrandDto;
import br.com.stoom.store.service.BrandService;
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

public class BrandControllerTest {

    @InjectMocks
    private BrandController brandController;

    @Mock
    private BrandService brandService;

    @Mock
    private RabbitMQService rabbitMQService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllActiveBrands() {
        List<BrandDto> brands = new ArrayList<>();
        when(brandService.getAllActiveBrands()).thenReturn(brands);

        List<BrandDto> result = brandController.getAllActiveBrands();

        verify(brandService, times(1)).getAllActiveBrands();
    }

    @Test
    public void testActivateBrand() {
        long id = 1L;
        when(brandService.activateBrandById(anyLong())).thenReturn(ResponseEntity.ok("Success"));

        ResponseEntity<String> result = brandController.activateBrand(id);

        verify(brandService, times(1)).activateBrandById(id);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_BRAND), eq(id));
    }

    @Test
    public void testDeactivateBrand() {
        long id = 1L;
        when(brandService.deactivateBrandById(anyLong())).thenReturn(ResponseEntity.ok("Success"));

        ResponseEntity<String> result = brandController.deactivateBrand(id);

        verify(brandService, times(1)).deactivateBrandById(id);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_BRAND), eq(id));
    }

    @Test
    public void testGetAllInactiveBrands() {
        List<BrandDto> brands = new ArrayList<>();
        when(brandService.getAllInactiveBrands()).thenReturn(brands);

        List<BrandDto> result = brandController.getAllInactiveBrands();

        verify(brandService, times(1)).getAllInactiveBrands();
    }

    @Test
    public void testCreateBrand() {
        BrandDto brandDto = new BrandDto();
        when(brandService.createBrand(brandDto)).thenReturn(brandDto);

        BrandDto result = brandController.createBrand(brandDto);

        verify(brandService, times(1)).createBrand(brandDto);
        verify(rabbitMQService, times(1)).sendMessage(eq(RabbitMQConstants.QUEUE_BRAND), eq(brandDto));
    }

}
