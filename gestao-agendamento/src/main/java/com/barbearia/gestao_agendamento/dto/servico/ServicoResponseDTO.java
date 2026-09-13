package com.barbearia.gestao_agendamento.dto.servico;

import java.math.BigDecimal;

public record ServicoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer duracao
){
}
