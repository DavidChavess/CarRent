package com.shx.locacao.veiculos.controller;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.dto.RentDTO;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.service.CustomerService;
import com.shx.locacao.veiculos.service.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;
    private RentService rentService;

    public CustomerController(CustomerService service, RentService rentService) {
        this.service = service;
        this.rentService = rentService;
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

    // metodo que vai filtrar alugueis de veiculos por cliente
    @GetMapping("/{id}/rents")
    public List<RentDTO> findRentsByCustomer(@PathVariable Integer id){
        return rentService.findByCustomer(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO){
        return service.update(id, customerDTO);
    }
}
