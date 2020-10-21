package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerDTO dto);

    CustomerDTO getById(Integer id);

    void deleteById(Integer id);

    List<CustomerDTO> getAll();
}
