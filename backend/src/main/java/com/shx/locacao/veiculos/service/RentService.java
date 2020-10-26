package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.RentDTO;
import com.shx.locacao.veiculos.dto.ReturnedRentDTO;
import com.shx.locacao.veiculos.dto.SaveRentDTO;

import java.util.List;

public interface RentService {

    RentDTO save(SaveRentDTO dto);

    // metodo para devolver um aluguel
    RentDTO returnedRent(Integer id, ReturnedRentDTO dto);

    List<RentDTO> getAll();

    List<RentDTO> findByCustomer(Integer id);
}
