package com.barbearia.gestao_agendamento.repository;

import com.barbearia.gestao_agendamento.model.Agendamento;
import com.barbearia.gestao_agendamento.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {

    @Query("""
    SELECT COUNT(a) > 0 
    FROM Agendamento a 
    WHERE a.barbeiro.id = :barbeiroId 
      AND a.statusAgendamento != com.barbearia.gestao_agendamento.model.StatusAgendamento.CANCELADO
      AND a.dataHora < :novoFim 
      AND a.dataHoraFim > :novoInicio
""")
    boolean existeConflitoDeHorario(
            @Param("barbeiroId") Long barbeiroId,
            @Param("novoInicio") LocalDateTime novoInicio,
            @Param("novoFim") LocalDateTime novoFim
    );

    public List<Agendamento> findByBarbeiroIdAndDataHoraBetween(Long barbeiroId, LocalDateTime inicio, LocalDateTime fim);

    public List<Agendamento> findByClienteId(Long clienteId);

    public List<Agendamento> findByStatusAgendamento(StatusAgendamento status);

    public List<Agendamento> findByStatusAgendamentoAndClienteId(StatusAgendamento status, Long clienteId);
}
