package com.barbearia.gestao_agendamento.dto.cliente;

import com.barbearia.gestao_agendamento.model.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf

) {
    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getTelefone());
    }
}
