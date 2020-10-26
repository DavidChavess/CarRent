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
        entityManager.close();
        return t;
    }

    @Override
    public T findById(Class<T> clazz, Integer id) {
        T find = entityManager.find(clazz, id);
        entityManager.close();
        return find;
    }

    @Transactional
    @Override
    public void deleteById(Class<T> clazz, Integer id) {
        T find = findById(clazz, id);
        entityManager.remove( find );
        entityManager.close();
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        List<T> list = entityManager.createQuery("FROM " + clazz.getName(), clazz).getResultList();
        entityManager.close();
        return list;
    }

    @Transactional
    @Override
    public T update(T t) {
        T merge = entityManager.merge(t);
        entityManager.close();
        return merge;
    }
}
