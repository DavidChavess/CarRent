package com.shx.locacao.veiculos.model;

// Essa interface vai ser implementada por todas as entidades do sistema
// ela existe pra forçar o meu Repository na hora de instanciar a aceitar somente entidades como tipo genérico
public interface BaseEntity {
    Integer getId();
}
