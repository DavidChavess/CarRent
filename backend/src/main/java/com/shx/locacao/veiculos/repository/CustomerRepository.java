package com.shx.locacao.veiculos.repository;

import com.shx.locacao.veiculos.model.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer c);

    Customer findById(Integer id);

    void deleteById(Integer id);
}
