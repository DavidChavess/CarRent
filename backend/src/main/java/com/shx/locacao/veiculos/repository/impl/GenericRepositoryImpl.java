package com.shx.locacao.veiculos.repository.impl;

import com.shx.locacao.veiculos.model.BaseEntity;
import com.shx.locacao.veiculos.model.Rent;
import com.shx.locacao.veiculos.repository.GenericRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GenericRepositoryImpl<T extends BaseEntity> implements GenericRepository<T> {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenericRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public T save(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public T findById(Class<T> clazz, Integer id) {
        return entityManager.find(clazz, id);
    }

    @Transactional
    @Override
    public void deleteById(Class<T> clazz, Integer id) {
        entityManager.remove( findById(clazz, id) );
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        return entityManager.createQuery("FROM " + clazz.getName(), clazz).getResultList();
    }

    @Transactional
    @Override
    public T update(T t) {
        return entityManager.merge(t);
    }
}
