package com.barbearia.gestao_agendamento.repository;

import com.barbearia.gestao_agendamento.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    public boolean existsByCpf(String cpf);

    public Optional<Barbeiro> findByCpf(String cpf);

    public List<Barbeiro> findByNomeContainingIgnoreCase(String nome);
}
