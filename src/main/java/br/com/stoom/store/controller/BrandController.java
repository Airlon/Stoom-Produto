package br.com.stoom.store.controller;


import br.com.stoom.store.constantes.RabbitMQConstants;
import br.com.stoom.store.dto.BrandDto;
import br.com.stoom.store.service.BrandService;
import br.com.stoom.store.service.RabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brands", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {

    private static final Logger log = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping("/active")
    public List<BrandDto> getAllActiveBrands() {
        log.info("Getting all active brands.");
        return brandService.getAllActiveBrands();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateBrand(@PathVariable long id) {
        log.info("Activating brand with id: {}", id);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_BRAND, id);
        ResponseEntity<String> response = brandService.activateBrandById(id);
        return response;
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateBrand(@PathVariable long id) {
        log.info("Deactivating brand with id: {}", id);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_BRAND, id);
        ResponseEntity<String> response = brandService.deactivateBrandById(id);
        return response;
    }

    @GetMapping("/inactive")
    public List<BrandDto> getAllInactiveBrands() {
        log.info("Getting all inactive brands.");
        return brandService.getAllInactiveBrands();
    }

    @PostMapping
    @ResponseBody
    public BrandDto createBrand(@RequestBody BrandDto brandDto) {
        log.info("Creating brand: {}", brandDto);
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_BRAND, brandDto);
        return brandService.createBrand(brandDto);
    }

}
