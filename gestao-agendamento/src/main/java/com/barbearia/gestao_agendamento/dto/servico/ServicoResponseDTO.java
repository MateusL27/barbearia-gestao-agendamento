package com.barbearia.gestao_agendamento.dto.servico;

import com.barbearia.gestao_agendamento.model.Servico;

import java.math.BigDecimal;

public record ServicoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer duracao


){
    public ServicoResponseDTO(Servico servico){
        this(servico.getId(), servico.getNome(), servico.getPreco(), servico.getDuracao());

    }
}
