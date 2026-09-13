package com.barbearia.gestao_agendamento.dto.agendamento;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoRequestDTO(

        @NotNull(message = "A data é obrigatória")
        @FutureOrPresent(message = "A data deve ser posterior ou no dia de hoje")
        LocalDateTime dataHora,

        @NotNull(message = "O cliente é obrigatório")
        Long clienteId,

        @NotNull(message = "O barbeiro é obrigatório")
        Long barbeiroId,

        @NotEmpty(message = "Informe ao menos um serviço para o agendamento")
        List<Long> servicosIds
) {
}
