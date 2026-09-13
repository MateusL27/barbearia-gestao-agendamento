package com.barbearia.gestao_agendamento.repository;

import com.barbearia.gestao_agendamento.model.Agendamento;
import com.barbearia.gestao_agendamento.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {

    public boolean existsByBarbeiroIdAndDataHoraBetween(Long barbeiroId, LocalDateTime inicio, LocalDateTime fim);

    public List<Agendamento> findByBarbeiroIdAndDataHoraBetween(Long barbeiroId, LocalDateTime inicio, LocalDateTime fim);

    public List<Agendamento> findByClienteId(Long clienteId);

    public List<Agendamento> findByStatusAgendamento(StatusAgendamento status);

    public List<Agendamento> findByStatusAgendamentoAndClienteId(StatusAgendamento status, Long clienteId);
}
