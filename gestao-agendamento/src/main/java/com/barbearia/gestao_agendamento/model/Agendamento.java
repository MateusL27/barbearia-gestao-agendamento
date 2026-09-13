package com.barbearia.gestao_agendamento.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agendamento_tb")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private LocalDateTime dataHora;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusAgendamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    @ManyToMany
    @JoinTable(
            name = "agendamento_servico_tb",
            joinColumns = @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos = new ArrayList<>();

    public Agendamento(){}

    public Agendamento(Long id, LocalDateTime dataHora, Cliente cliente, StatusAgendamento statusAgendamento, Barbeiro barbeiro) {
        this.id = id;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.statusAgendamento = statusAgendamento;
        this.barbeiro = barbeiro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusAgendamento getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", statusAgendamento=" + statusAgendamento +
                '}';
    }
}
