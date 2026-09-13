package com.barbearia.gestao_agendamento.dto.barbeiro;

import com.barbearia.gestao_agendamento.model.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalTime;

public record BarbeiroRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O cpf é obrigatório")
        @CPF(message = "Cpf inválido")
        String cpf,

        @NotNull(message = "A especialidade é obrigatória")
        Especialidade especialidade,

        @NotNull(message = "O horário de início é obrigatório")
        LocalTime horarioInicioTrabalho,

        @NotNull(message = "O horário de fim é obrigatório")
        LocalTime horarioFimTrabalho
) {
}
