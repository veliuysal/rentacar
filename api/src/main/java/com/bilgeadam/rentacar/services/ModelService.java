package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.dto.brand.BrandDTO;
import com.bilgeadam.rentacar.dto.model.ModelDTO;
import com.bilgeadam.rentacar.dto.model.ModelSaveDTO;
import com.bilgeadam.rentacar.dto.model.ModelUpdateDTO;
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

    public List<ModelDTO> getAllModels() {
        List<Model> models = modelRepository.findAll();
        List<ModelDTO> dtos = models.stream().map(this::getModelDTO).toList();
        return dtos;
    }

    public ModelDTO getModelByID(Integer id) {
        Optional<Model> optModel = modelRepository.findById(id);
        return optModel.isEmpty() ? null : getModelDTO(optModel.get());
    }

    public List<ModelDTO> getAllModelsByBrandID(Integer brandId) {
        List<Model> models = modelRepository.findAllByBrand_id(brandId);
        List<ModelDTO> dtos = models.stream().map(this::getModelDTO).toList();
        return dtos;
    }

    public ModelDTO getModelDTO(Model model) {
        ModelDTO dto = new ModelDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(model.getBrand().getId());
        brandDTO.setName(model.getBrand().getName());
        dto.setBrand(brandDTO);
        return dto;
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
