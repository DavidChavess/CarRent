package com.shx.locacao.veiculos.repository;

import com.shx.locacao.veiculos.model.BaseEntity;

import java.util.List;

public interface GenericRepository<T extends BaseEntity> {
    T save(T t);

    T findById(Class<T> clazz ,Integer id);

    void deleteById(Class<T> clazz, Integer id);

    List<T> findAll(Class<T> clazz);

    T update(T t);
}
