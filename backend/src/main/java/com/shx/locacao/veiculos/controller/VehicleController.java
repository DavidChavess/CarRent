package com.shx.locacao.veiculos.controller;

import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService service;

    public VehicleController( VehicleService service){
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VehicleDTO create(@RequestBody VehicleDTO vehicleDTO){
        return service.save(vehicleDTO);
    }
}
