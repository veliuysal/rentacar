package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.dto.ModelSaveDTO;
import com.bilgeadam.rentacar.dto.ModelUpdateDTO;
import com.bilgeadam.rentacar.entities.Brand;
import com.bilgeadam.rentacar.entities.Model;
import com.bilgeadam.rentacar.repository.BrandRepository;
import com.bilgeadam.rentacar.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model getModelByID(Integer id) {
        Optional<Model> optModel = modelRepository.findById(id);
        return optModel.isEmpty() ? null : optModel.get();
    }

    public List<Model> getAllModelsByBrandID(Integer brandId) {
        return modelRepository.findAllByBrand_id(brandId);
    }

    public Model saveModel(ModelSaveDTO dto) throws Exception {
        if (Objects.isNull(dto.getBrandId()))
            throw new Exception("Brand alanı boş olamaz");
        Optional<Brand> optBrand = brandRepository.findById(dto.getBrandId());
        if (optBrand.isEmpty())
            throw new Exception("Brand Bulunuamadı");
        Model model = new Model();
        model.setBrand(optBrand.get());
        model.setName(dto.getName());
        return modelRepository.save(model);
    }

    public Model updateModel(ModelUpdateDTO dto) throws Exception {
        Optional<Model> optModel = modelRepository.findById(dto.getId());
        if (optModel.isEmpty())
            throw new Exception("ID yanlış");
        Model model = optModel.get();
        if (Objects.nonNull(dto.getBrandId())) {
            Optional<Brand> optBrand = brandRepository.findById(dto.getBrandId());
            if (optBrand.isEmpty())
                throw new Exception("Brand ID yanlış");
            model.setBrand(optBrand.get());
        }
        if (Objects.nonNull(dto.getName())) {
            model.setName(dto.getName());
        }
        return modelRepository.save(model);
    }

    public void deleteModel(Integer id) {
        modelRepository.deleteById(id);
    }
}
