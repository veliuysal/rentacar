package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.entities.Brand;
import com.bilgeadam.rentacar.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandByID(Integer id) {
        Optional<Brand> optBrand = brandRepository.findById(id);
        return optBrand.isEmpty() ? null : optBrand.get();
    }

    public Brand saveBrand(Brand brand) {
        Integer idValue = brandRepository.getMaxId() + 1;
        brand.setId(idValue);
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public void deleteBrand(Integer id) {
        brandRepository.deleteById(id);
    }
}
