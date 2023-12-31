package com.bilgeadam.rentacar.controller;

import com.bilgeadam.rentacar.dto.brand.BrandSaveDTO;
import com.bilgeadam.rentacar.dto.brand.BrandUpdateDTO;
import com.bilgeadam.rentacar.entities.Brand;
import com.bilgeadam.rentacar.services.BrandService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String isRunning() {
        return "API is working";
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Brand getBrandByID(@PathVariable("id") Integer id) {
        return brandService.getBrandByID(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Brand saveCategory(@RequestBody BrandSaveDTO dto) {
        return brandService.saveBrand(dto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Brand updateBrand(@RequestBody BrandUpdateDTO dto) throws Exception {
        return brandService.updateBrand(dto);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteBrand(@PathVariable("id") Integer id) throws Exception {
        brandService.deleteBrand(id);
        return "Silme İşlemi Başarılı.";
    }
}
