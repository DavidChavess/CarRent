package com.shx.locacao.veiculos.controller;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerDTO save( @RequestBody CustomerDTO customerDTO){
        return service.save(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        service.deleteById(id);
    }

    @GetMapping
    public List<CustomerDTO> getAll(){
        return service.getAll();
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO){
        return service.update(id, customerDTO);
    }
}
