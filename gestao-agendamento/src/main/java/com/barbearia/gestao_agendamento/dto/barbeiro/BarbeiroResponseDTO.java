package com.barbearia.gestao_agendamento.dto.barbeiro;

import com.barbearia.gestao_agendamento.model.Especialidade;

import java.time.LocalTime;

public record BarbeiroResponseDTO(
        Long id,
        String nome,
        String cpf,
        Especialidade especialidade,
        LocalTime horarioInicioTrabalho,
        LocalTime horarioFimTrabalho
) {
}
