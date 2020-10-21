package com.shx.locacao.veiculos.service.impl;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.exception.ObjectNotFoundException;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.repository.CustomerRepository;
import com.shx.locacao.veiculos.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;
    private ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO dto) {
        // converto o dto que foi enviado na requisição para entidade, para gravar no banco
        Customer customer = modelMapper.map(dto, Customer.class);

        // salvo no banco de dados e retorno o obj
        customer = repository.save(customer);

        // converto novamente para dto e retorno o dto
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO getById(Integer id) {
        try {
            return modelMapper.map(repository.findById(id), CustomerDTO.class);

        }catch (IllegalArgumentException e){
            throw new ObjectNotFoundException("Cliente não encontrado para o id informado");
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            repository.deleteById(id);

        }catch (IllegalArgumentException e){
            throw new ObjectNotFoundException("Cliente não encontrado para o id informado");
        }
    }

    @Override
    public List<CustomerDTO> getAll() {
        return repository.findAll().stream()
            .map(c -> modelMapper.map(c, CustomerDTO.class))
            .collect(Collectors.toList());
}

    @Override
    public CustomerDTO update(Integer id, CustomerDTO customerDTO) {
        try {
            Customer customer = repository.findById(id);

            customer.setStatus(customerDTO.getStatus());
            customer.setName(customerDTO.getName());
            customer.setCpf(customerDTO.getCpf());
            customer.setBirthdate(customerDTO.getBirthdate());

            return modelMapper.map( repository.update(customer), CustomerDTO.class);

        } catch ( NullPointerException e ){
            throw new ObjectNotFoundException("Cliente não encontrado para o id informado");
        }
    }

}
