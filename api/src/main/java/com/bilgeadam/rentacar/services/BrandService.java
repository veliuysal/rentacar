package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.entities.Brand;
import com.bilgeadam.rentacar.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }
}
