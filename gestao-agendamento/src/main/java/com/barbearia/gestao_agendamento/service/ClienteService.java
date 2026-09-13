package com.barbearia.gestao_agendamento.service;

import com.barbearia.gestao_agendamento.dto.cliente.ClienteRequestDTO;
import com.barbearia.gestao_agendamento.dto.cliente.ClienteResponseDTO;
import com.barbearia.gestao_agendamento.model.Cliente;
import com.barbearia.gestao_agendamento.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto){
        if(clienteRepository.existsByCpf(dto.cpf())){
            throw new IllegalArgumentException("Já existe um registro com este cpf");
        }

        if(clienteRepository.existsByEmail(dto.email())){
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este email");
        }

        Cliente cliente = new Cliente(dto);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponseDTO(clienteSalvo);
    }

    public ClienteResponseDTO procurarPorId(Long id){

        Cliente cliente = clienteRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Cliente não encontrado para o Id "+id));

        return new ClienteResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> listarClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> new ClienteResponseDTO(cliente))
                .toList();
    }
}
