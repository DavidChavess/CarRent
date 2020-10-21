package com.shx.locacao.veiculos.repository;

import com.shx.locacao.veiculos.model.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer c);

    Customer findById(Integer id);

    void deleteById(Integer id);

    List<Customer> findAll();

    Customer update(Customer customer);
}
