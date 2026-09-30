package com.barbearia.gestao_agendamento.repository;

import com.barbearia.gestao_agendamento.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    public boolean existsByNome(String nome);

    public List<Servico> findByNomeContainingIgnoreCase(String nome);
}
