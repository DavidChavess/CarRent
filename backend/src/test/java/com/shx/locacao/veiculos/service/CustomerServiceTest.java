package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.exception.ObjectNotFoundException;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.repository.CustomerRepository;
import com.shx.locacao.veiculos.service.impl.CustomerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomer;
import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomerDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    CustomerService service;

    @MockBean
    CustomerRepository repository;

    @MockBean
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp(){
        service = new CustomerServiceImpl(repository,modelMapper);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveCustomerTest(){
        CustomerDTO dto = createCustomerDTO(null, LocalDate.now());
        Customer customerSaved = createCustomer(1);
        Customer customerSaving = createCustomer(null);

        // converto dto para um customer a ser salvo, por isso o id esta null
        when(modelMapper.map(dto, Customer.class)).thenReturn(customerSaving);

        // salvo a entidade que eu acabei de converter e retorno ela com o id populado
        when(repository.save(customerSaving)).thenReturn(customerSaved);

        // converto a entidade que eu salvei para dto com o id populado
        when(modelMapper.map(customerSaved, CustomerDTO.class)).thenReturn(createCustomerDTO(1, LocalDate.now()));

        // chamo o metodo do meu service
        CustomerDTO c = service.save(dto);

        // verificação se foi salvo
        assertThat(c.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve pegar um cliente pelo id")
    public void getCustomerByIdTest(){
        final Integer id = 1;
        Customer customer = createCustomer(id);

        // busco um cliente no banco pelo id
        when(repository.findById(id)).thenReturn(customer);

        // converto esse cliente para um dto
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(createCustomerDTO(id, LocalDate.now()));

        // chamo o metodo do meu service
        CustomerDTO c = service.getById(id);

        // verifico se retornou corretamente
        assertThat(c.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve lançar exceção caso não encontre o cliente pelo id")
    public void dontGetCustomerByIdTest(){
        final Integer id = 1;

        // busco um cliente inexistente e então retorno uma exceção
        when(repository.findById(id)).thenThrow(IllegalArgumentException.class);

        // capturo a execeção do metodo
        Throwable c = Assertions.catchThrowable(() -> service.getById(id));

        // verifico se retornou a mensagem corretamente
        assertThat(c).hasMessage("Cliente não encontrado para o id informado");
    }
}
