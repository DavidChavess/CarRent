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
import java.util.Arrays;
import java.util.List;

import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomer;
import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomerDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("Deve deletar um cliente pelo id")
    public void deleteCustomerByIdTest(){
        final Integer id = 1;

        service.deleteById(id);

        verify(repository).deleteById(id);
    }

    @Test
    @DisplayName("Deve retornar todos os clientes da base")
    public void getAllCustomersTest(){
        List<Customer> customers = Arrays.asList(
                createCustomer(1),
                createCustomer(2)
        );

        // busco todos os clientes no banco
        when(repository.findAll()).thenReturn(customers);

        // chamo o metodo do meu service
        List<CustomerDTO> l = service.getAll();

        // verifico se retornou corretamente
        assertThat(l).hasSize(2);
        assertThat(l).isNotEmpty();
    }

    @Test
    @DisplayName("Deve atualizar um cliente da base")
    public void updateCustomersTest(){
        final Integer id = 1;

        // Busco um cliente da base pelo id
        Customer customerReturnedById = new Customer(id, 77898521415L, "teste", LocalDate.now(), true);
        when(repository.findById(id)).thenReturn(customerReturnedById);

        // quando eu chamar o metodo update passando um cliente eu devolve um cliente alterado
        Customer customerUpdate = new Customer(id, 87898521415L, "teste update", LocalDate.now(), true);
        when(repository.update(customerReturnedById)).thenReturn(customerUpdate);

        // converto para dto
        CustomerDTO customerDtoUpdating = new CustomerDTO(id, 87898521415L, "teste update", LocalDate.now(), true);
        when(modelMapper.map(customerUpdate, CustomerDTO.class))
                .thenReturn(customerDtoUpdating);

        // chamo o metodo do meu service para atualizar
        CustomerDTO c = service.update(id, customerDtoUpdating);

        // verifico se retornou corretamente
        assertThat(c.getId()).isEqualTo(id);
        assertThat(c.getName()).isEqualTo(customerUpdate.getName());
        assertThat(c.getCpf()).isEqualTo(customerUpdate.getCpf());
        assertThat(c.getBirthdate()).isEqualTo(customerUpdate.getBirthdate());
        assertThat(c.getStatus()).isEqualTo(customerUpdate.getStatus());
    }

}
