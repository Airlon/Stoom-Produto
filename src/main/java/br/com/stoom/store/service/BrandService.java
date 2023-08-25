package br.com.stoom.store.service;


import br.com.stoom.store.dto.BrandDto;
import br.com.stoom.store.exception.BrandExceptions;
import br.com.stoom.store.product.Brand;

import br.com.stoom.store.repository.BrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private static final Logger log = LoggerFactory.getLogger(BrandService.class);

    @Autowired
    private BrandRepository brandRepository;

    public List<BrandDto> getAllActiveBrands() {
        log.info("Getting all active brands.");
        List<Brand> activeBrands = brandRepository.findByAtivo(true);
        return activeBrands.stream().map(this::convert).collect(Collectors.toList());
    }

    public BrandDto convert(Brand brand) {
        BrandDto result = new BrandDto();
        result.setId(brand.getId());
        result.setNome(brand.getNome());
        return result;
    }

    public ResponseEntity<String> activateBrandById(long id) {
        log.info("Activating brand with id: {}", id);
        Optional<Brand> optionalBrand = brandRepository.findById(id);

        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setAtivo(true);
            brandRepository.save(brand);
            log.info("Brand activated successfully with id: {}", id);
            return ResponseEntity.ok("Marca ativada com sucesso.");
        } else {
            log.info("Brand with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deactivateBrandById(long id) {
        log.info("Deactivating brand with id: {}", id);
        Optional<Brand> optionalBrand = brandRepository.findById(id);

        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setAtivo(false);
            brandRepository.save(brand);
            log.info("Brand deactivated successfully with id: {}", id);
            return ResponseEntity.ok("Marca desativada com sucesso.");
        } else {
            log.info("Brand with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    public List<BrandDto> getAllInactiveBrands() {
        log.info("Getting all inactive brands.");
        List<Brand> inactiveBrands = brandRepository.findByAtivo(false);
        return inactiveBrands.stream().map(this::convert).collect(Collectors.toList());
    }

    public BrandDto createBrand(BrandDto brandDto) {
        log.info("Creating brand: {}", brandDto);
        List<Brand> existingBrand = brandRepository.findByNome(brandDto.getNome());

        if (!existingBrand.isEmpty()) {
            throw new BrandExceptions();
        }

        Brand newBrand = new Brand();
        newBrand.setAtivo(brandDto.isAtivo());
        newBrand.setNome(brandDto.getNome());
        newBrand.setDtCadastro(brandDto.getDtCadastro());

        brandRepository.save(newBrand);

        brandDto.setId(newBrand.getId());
        return brandDto;
    }
}
