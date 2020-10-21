package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO save(CustomerDTO dto);

    CustomerDTO getById(Integer id);
}
