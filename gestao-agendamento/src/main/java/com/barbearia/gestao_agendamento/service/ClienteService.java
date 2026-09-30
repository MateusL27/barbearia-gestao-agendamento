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

    public ClienteResponseDTO procurarPorCpf(String cpf){

        Cliente cliente = clienteRepository
                .findByCpf(cpf).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado por CPF "+cpf));


        return new ClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO buscarPorEmail(String email){

        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado pelo email: "+email));

        return new ClienteResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> procurarPorNome (String nome){

        return clienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(cliente -> new ClienteResponseDTO(cliente))
                .toList();
    }

    public void deletarCliente(long id){

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado para o id "+ id));

        clienteRepository.delete(cliente);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO clienteRequest){

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não enconcrado para o id "+ id));

        clienteExistente.setEmail(clienteRequest.email());
        clienteExistente.setTelefone(clienteRequest.telefone());
        clienteExistente.setNome(clienteRequest.nome());

        Cliente clienteSalvo = clienteRepository.save(clienteExistente);

        return new ClienteResponseDTO(clienteSalvo);
    }
}