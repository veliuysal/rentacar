package com.bilgeadam.rentacar.controller;

import com.bilgeadam.rentacar.dto.BrandSaveDTO;
import com.bilgeadam.rentacar.dto.BrandUpdateDTO;
import com.bilgeadam.rentacar.dto.ModelSaveDTO;
import com.bilgeadam.rentacar.dto.ModelUpdateDTO;
import com.bilgeadam.rentacar.entities.Brand;
import com.bilgeadam.rentacar.entities.Model;
import com.bilgeadam.rentacar.services.ModelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String isRunning() {
        return "API is working";
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Model> getAllModels() {
        return modelService.getAllModels();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Model getModelByID(@PathVariable("id") Integer id) {
        return modelService.getModelByID(id);
    }

    @GetMapping(path = "/brand/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Model> getAllModelsByBrandID(@PathVariable("id") Integer brandId) {
        return modelService.getAllModelsByBrandID(brandId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Model saveModel(@RequestBody ModelSaveDTO dto) throws Exception {
        return modelService.saveModel(dto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Model updateModel(@RequestBody ModelUpdateDTO dto) throws Exception {
        return modelService.updateModel(dto);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteModel(@PathVariable("id") Integer id) throws Exception {
        modelService.deleteModel(id);
        return "Silme İşlemi Başarılı.";
    }
}
