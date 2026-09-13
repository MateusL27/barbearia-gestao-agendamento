package com.barbearia.gestao_agendamento.dto.servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ServicoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

         @NotNull(message = "O preço é obrigatório")
         @Positive(message = "O preço deve ser positivo")
         BigDecimal preco,

        @NotNull(message = "A duração é obrigatória")
        @Positive(message = "A duração deve ser maior que zero")
        Integer duracao
){}
