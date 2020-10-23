package com.shx.locacao.veiculos.util;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.exception.BusinessException;

public abstract class Validation {

    public static void validVehicle(VehicleDTO dto){

        isNull(dto, "Você deve enviar um veiculo valido");
        isBetween(dto.getName(), 1, 100, "O campo nome deve ter até 100 caracteres");
        isBetween(dto.getBrand(), 1, 30, "O campo marca deve ser entre 1 e 30 caracteres");
        isBetween(dto.getYear(), 2000, 2100, "O campo ano deve ser entre o ano 2000 e 2100");
        isNull(dto.getValuePerDay(), "O valor da diaria é obrigatório");

        if (dto.getModel() >= dto.getYear()) {
            isBetween(dto.getModel(), 2000, 2100, "O campo modelo deve ser entre o ano 2000 e 2100");
        }else{
            throw new BusinessException("O modelo deve ser maior ou igual ao ano");
        }


    }

    public static void validCustomer(CustomerDTO dto){

        isNull(dto, "Você deve enviar um cliente valido");
        isNull(dto.getBirthdate(), "O campo data é obrigatório");
        isEquals(dto.getCpf().toString(), 11, "O cpf deve ter 11 numeros inteiros");
        isBetween(dto.getName(), 1, 100,  "O nome deve ter até 100 caracteres");

    }

    // valida se um campo não ultrapassou o limite de caracteres permitido e lança erro
    public static void isBetween(String field, Integer min, Integer max,  String desc){
        isNull(field, "O campo é obrigatório");

        if (field.length() < min || field.length() > max){
            throw new BusinessException(desc);
        }
    }

    // valida se um campo não ultrapassou o limite de caracteres permitido e lança erro
    public static void isBetween(Integer field, Integer min, Integer max,  String desc){
        isNull(field, "O campo é obrigatório");

        if (field < min || field > max){
            throw new BusinessException(desc);
        }
    }

    // valida se a quantidade de caracteres do campo é diferente da que eu passei
    public static void isEquals(String field, Integer max,  String desc){
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
