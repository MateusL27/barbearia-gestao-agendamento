package com.barbearia.gestao_agendamento.model;

import com.barbearia.gestao_agendamento.dto.servico.ServicoRequestDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servico_tb")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "duracao")
    private Integer duracao;

    @ManyToMany(mappedBy = "servicos")
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Servico(){}

    public Servico(Long id, String nome, BigDecimal preco, Integer duracao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracao = duracao;
    }

    public Servico(ServicoRequestDTO servicoRequestDTO){
        this.nome = servicoRequestDTO.nome();
        this.preco = servicoRequestDTO.preco();
        this.duracao = servicoRequestDTO.duracao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", duracao=" + duracao +
                '}';
    }
}
