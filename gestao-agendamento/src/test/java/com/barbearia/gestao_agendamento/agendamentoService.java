package com.barbearia.gestao_agendamento;

import com.barbearia.gestao_agendamento.model.*;
import com.barbearia.gestao_agendamento.repository.AgendamentoRepository;
import com.barbearia.gestao_agendamento.repository.BarbeiroRepository;
import com.barbearia.gestao_agendamento.repository.ClienteRepository;
import com.barbearia.gestao_agendamento.repository.ServicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AgendamentoRepositoryTest {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Test
    @DisplayName("Deve salvar um agendamento com sucesso no banco de dados e calcular a dataHoraFim")
    void deveSalvarAgendamentoNoBancoDeDados() {

        // 1. Arrange - Criar e salvar as entidades dependentes sem informar ID
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "11999999999", "12345678900");
        cliente = clienteRepository.save(cliente);

        Barbeiro barbeiro = new Barbeiro("Carlos Barbeiro", "98765432100", Especialidade.COMPLETO, LocalTime.of(8, 0), LocalTime.of(18, 0));
        barbeiro = barbeiroRepository.save(barbeiro);

        Servico corte = new Servico("Corte de Cabelo", new BigDecimal("50.00"), 30);
        Servico barba = new Servico("Barba", new BigDecimal("35.00"), 20);

        corte = servicoRepository.save(corte);
        barba = servicoRepository.save(barba);

        List<Servico> servicos = List.of(corte, barba);

        // Regra de Negócio: Cálculo do tempo total em memória (Etapa 2)
        LocalDateTime dataHoraInicio = LocalDateTime.of(2026, 10, 1, 14, 0);
        int duracaoTotalEmMinutos = servicos.stream()
                .map(Servico::getDuracao)
                .reduce(0, Integer::sum);

        LocalDateTime dataHoraFim = dataHoraInicio.plusMinutes(duracaoTotalEmMinutos);

        // 2. Act - Instanciar o Agendamento (sem ID) e persistir no banco
        Agendamento novoAgendamento = new Agendamento(
                dataHoraInicio,
                dataHoraFim,
                cliente,
                StatusAgendamento.PENDENTE,
                barbeiro,
                servicos
        );

        Agendamento agendamentoSalvo = agendamentoRepository.save(novoAgendamento);

        // 3. Assert - Validações dos dados e do ID gerado pelo banco
        assertNotNull(agendamentoSalvo.getId(), "O ID do agendamento deve ser gerado pelo banco");
        assertEquals(2, agendamentoSalvo.getServicos().size());
        assertEquals(50, duracaoTotalEmMinutos);
        assertEquals(LocalDateTime.of(2026, 10, 1, 14, 0), agendamentoSalvo.getDataHora());
        assertEquals(LocalDateTime.of(2026, 10, 1, 14, 50), agendamentoSalvo.getDataHoraFim());
        assertEquals(StatusAgendamento.PENDENTE, agendamentoSalvo.getStatusAgendamento());
        assertEquals("Carlos Barbeiro", agendamentoSalvo.getBarbeiro().getNome());
        assertEquals("João Silva", agendamentoSalvo.getCliente().getNome());
    }
}