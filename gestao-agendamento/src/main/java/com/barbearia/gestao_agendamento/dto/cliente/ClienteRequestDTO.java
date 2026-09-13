package com.barbearia.gestao_agendamento.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,

        @NotBlank(message = "O cpf é obrigatório")
        @CPF(message = "Cpf inválido")
        String cpf
){}
