package com.barbearia.gestao_agendamento.dto.agendamento;

import com.barbearia.gestao_agendamento.dto.barbeiro.BarbeiroResponseDTO;
import com.barbearia.gestao_agendamento.dto.cliente.ClienteResponseDTO;
import com.barbearia.gestao_agendamento.dto.servico.ServicoResponseDTO;
import com.barbearia.gestao_agendamento.model.StatusAgendamento;

import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoResponseDTO(

        Long id,
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento,
        ClienteResponseDTO cliente,
        BarbeiroResponseDTO barbeiro,
        List<ServicoResponseDTO> servicosIdsç
) {
}
