package com.bilgeadam.rentacar.controller;

import com.bilgeadam.rentacar.dto.common.EnumDTO;
import com.bilgeadam.rentacar.dto.common.SelectDataDTO;
import com.bilgeadam.rentacar.services.CommonServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    private final CommonServices commonServices;

    public CommonController(CommonServices commonServices) {
        this.commonServices = commonServices;
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String isRunning() {
        return "API is working";
    }

    @GetMapping(path = "/years", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SelectDataDTO> getAllYears() {
        return commonServices.getAllYears();
    }

    @GetMapping(path = "/colors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnumDTO> getAllColors() {
        return commonServices.getAllColors();
    }

    @GetMapping(path = "/fuel-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnumDTO> getAllFuelTypes() {
        return commonServices.getAllFuelTypes();
    }

    @GetMapping(path = "/body-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnumDTO> getAllBodyTypes() {
        return commonServices.getAllBodyTypes();
    }

    @GetMapping(path = "/fuel-tanks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnumDTO> getAllFuelTanks() {
        return commonServices.getAllColors();
    }
}
