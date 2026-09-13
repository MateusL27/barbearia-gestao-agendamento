package com.barbearia.gestao_agendamento.repository;

import com.barbearia.gestao_agendamento.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    public Optional<Cliente> findByCpf(String cpf);

    public Optional<Cliente> findByEmail(String email);

    public List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
