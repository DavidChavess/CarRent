package com.shx.locacao.veiculos.repository.impl;

import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.repository.CustomerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Customer save(Customer c) {
        entityManager.persist(c);
        return c;
    }

    @Override
    public Customer findById(Integer id) {
        return entityManager.find(Customer.class, id);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        entityManager.remove( findById(id) );
    }

    @Override
    public List<Customer> findAll(){
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Transactional
    @Override
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }
}
