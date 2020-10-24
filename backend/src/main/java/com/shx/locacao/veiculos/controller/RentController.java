package com.shx.locacao.veiculos.controller;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.dto.RentDTO;
import com.shx.locacao.veiculos.dto.ReturnedRentDTO;
import com.shx.locacao.veiculos.dto.SaveRentDTO;
import com.shx.locacao.veiculos.service.CustomerService;
import com.shx.locacao.veiculos.service.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rents")
public class RentController {

    private RentService service;

    public RentController(RentService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RentDTO save(@RequestBody SaveRentDTO saveRentDTO){
        return service.save(saveRentDTO);
    }

    @PatchMapping("/{id}")
    public RentDTO returnedRent(@PathVariable Integer id, @RequestBody ReturnedRentDTO dto){
        return service.returnedRent(id, dto);
    }

    @GetMapping
    public List<RentDTO> getAll(){
        return service.getAll();
    }

}
