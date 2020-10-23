package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.RentDTO;
import com.shx.locacao.veiculos.dto.SaveRentDTO;

public interface RentService {

    RentDTO save(SaveRentDTO dto);

}
