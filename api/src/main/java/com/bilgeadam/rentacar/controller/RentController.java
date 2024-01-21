package com.bilgeadam.rentacar.controller;

import com.bilgeadam.rentacar.dto.rent.RentDTO;
import com.bilgeadam.rentacar.dto.rent.RentSaveDTO;
import com.bilgeadam.rentacar.entities.Brand;
import com.bilgeadam.rentacar.services.RentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RentController {

    //TODO: Verilen tarih aralığında kiralamaları getriren fonksiyon
    // Tüm kiralamaları getiren fonksiyon
    // Verilen yıl ve aya göre tüm kiralamaları getiren fonksiyon
    // Verilen araba için tüm kiralamaları getiren fonksiyon
    // Verilen araba için verilen tarihler arasındaki kiralamaları getiren fonk.
    // verilen ay ve yıla göre kiralama sayısı
    // Verilen ay ve yıla göre toplam kiralama süreleri

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String isRunning() {
        return "API is working";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RentDTO saveRent(@RequestBody RentSaveDTO dto) {
        return rentService.saveRent(dto);
    }

}
