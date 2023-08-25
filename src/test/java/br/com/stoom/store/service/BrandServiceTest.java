package br.com.stoom.store.service;

import br.com.stoom.store.dto.BrandDto;
import br.com.stoom.store.exception.BrandExceptions;
import br.com.stoom.store.product.Brand;
import br.com.stoom.store.repository.BrandRepository;
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

public class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private BrandRepository brandRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllActiveBrands() {
        List<Brand> activeBrands = new ArrayList<>();
        when(brandRepository.findByAtivo(true)).thenReturn(activeBrands);

        List<BrandDto> result = brandService.getAllActiveBrands();

        verify(brandRepository, times(1)).findByAtivo(true);
    }

    @Test
    public void testActivateBrandById() {
        long id = 1L;
        Brand brand = new Brand();
        brand.setId(id);
        brand.setAtivo(false);

        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        ResponseEntity<String> result = brandService.activateBrandById(id);

        verify(brandRepository, times(1)).findById(id);
        verify(brandRepository, times(1)).save(brand);
    }

    @Test
    public void testDeactivateBrandById() {
        long id = 1L;
        Brand brand = new Brand();
        brand.setId(id);
        brand.setAtivo(true);

        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);

        ResponseEntity<String> result = brandService.deactivateBrandById(id);

        verify(brandRepository, times(1)).findById(id);
        verify(brandRepository, times(1)).save(brand);
    }

    @Test
    public void testGetAllInactiveBrands() {
        List<Brand> inactiveBrands = new ArrayList<>();
        when(brandRepository.findByAtivo(false)).thenReturn(inactiveBrands);

        List<BrandDto> result = brandService.getAllInactiveBrands();

        verify(brandRepository, times(1)).findByAtivo(false);
    }

    @Test(expected = BrandExceptions.class)
    public void testCreateExistingBrand() {
        BrandDto brandDto = new BrandDto();
        brandDto.setNome("Nike");

        List<Brand> existingBrands = new ArrayList<>();
        existingBrands.add(new Brand());

        when(brandRepository.findByNome(brandDto.getNome())).thenReturn(existingBrands);

        brandService.createBrand(brandDto);

        verify(brandRepository, never()).save(any(Brand.class));
    }

    @Test
    public void testCreateBrand() {
        BrandDto brandDto = new BrandDto();
        brandDto.setNome("Adidas");
        brandDto.setAtivo(true);
        brandDto.setDtCadastro(Timestamp.valueOf(LocalDateTime.now()));

        when(brandRepository.findByNome(brandDto.getNome())).thenReturn(new ArrayList<>());
        when(brandRepository.save(any(Brand.class))).thenReturn(new Brand());

        brandService.createBrand(brandDto);

        verify(brandRepository, times(1)).save(any(Brand.class));
    }

}
