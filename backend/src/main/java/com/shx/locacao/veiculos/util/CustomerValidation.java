package com.shx.locacao.veiculos.util;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.exception.BusinessException;

public abstract class CustomerValidation {


    public static void validCustomer(CustomerDTO dto){

        isNull(dto, "Você deve enviar um cliente valido");
        isNull(dto.getBirthdate(), "O campo data é obrigatório");
        isNotEquals(dto.getCpf().toString(), 11, "O cpf deve ter 11 numeros inteiros");
        isNotBetween(dto.getName(), 1, 100, "O nome deve ter até 100 caracteres");

    }

    // valida se um campo não ultrapassou o limite de caracteres permitido e lança erro
    public static void isNotBetween(String field, Integer min, Integer max,  String desc){
        isNull(field, "O campo é obrigatório");

        if (field.length() < min || field.length() > max){
            throw new BusinessException(desc);
        }
    }

    // valida se a quantidade de caracteres do campo é diferente da que eu passei
    public static void isNotEquals(String field, Integer max,  String desc){
        isNull(field, "O campo é obrigatório");

        if (field.length() != max){
            throw new BusinessException(desc);
        }
    }

    // valida se um obj é nulo e lança exceção
    public static void isNull(Object obj, String desc){
        if (obj == null){
            throw new BusinessException(desc);
        }
    }
}
