package com.shx.locacao.veiculos.controller;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService service;

    public VehicleController( VehicleService service){
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VehicleDTO save(@RequestBody VehicleDTO vehicleDTO){
        return service.save(vehicleDTO);
    }

    @GetMapping("/{id}")
    public VehicleDTO getById( @PathVariable Integer id){
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById( @PathVariable Integer id){
        service.deleteById(id);
    }

    @GetMapping
    public List<VehicleDTO> getAll(){
        return service.getAll();
    }

    @PutMapping("/{id}")
    public VehicleDTO update(@PathVariable Integer id, @RequestBody VehicleDTO vehicleDTO){
        return service.update(id, vehicleDTO);
    }

}
