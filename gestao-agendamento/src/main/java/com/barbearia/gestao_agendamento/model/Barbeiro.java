package com.barbearia.gestao_agendamento.model;

import com.barbearia.gestao_agendamento.dto.barbeiro.BarbeiroRequestDTO;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "barbeiro_tb")
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "especialidade")
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Column(name = "horario_inicio")
    private LocalTime horarioInicioTrabalho;

    @Column(name = "horario_fim")
    private LocalTime horarioFimTrabalho;

    @OneToMany(mappedBy = "barbeiro")
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Barbeiro() {
    }

    public Barbeiro(String nome, String cpf, Especialidade especialidade, LocalTime horarioInicioTrabalho, LocalTime horarioFimTrabalho) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.especialidade = especialidade;
        this.horarioInicioTrabalho = horarioInicioTrabalho;
        this.horarioFimTrabalho = horarioFimTrabalho;
    }

    public Barbeiro(BarbeiroRequestDTO requestDto){
        this.nome = requestDto.nome();
        this.cpf = requestDto.cpf();
        this.especialidade = requestDto.especialidade();
        this.horarioInicioTrabalho = requestDto.horarioInicioTrabalho();
        this.horarioFimTrabalho = requestDto.horarioFimTrabalho();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public LocalTime getHorarioInicioTrabalho() {
        return horarioInicioTrabalho;
    }

    public void setHorarioInicioTrabalho(LocalTime horarioInicioTrabalho) {
        this.horarioInicioTrabalho = horarioInicioTrabalho;
    }

    public LocalTime getHorarioFimTrabalho() {
        return horarioFimTrabalho;
    }

    public void setHorarioFimTrabalho(LocalTime horarioFimTrabalho) {
        this.horarioFimTrabalho = horarioFimTrabalho;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @Override
    public String toString() {
        return "Barbeiro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", especialidade=" + especialidade +
                ", horarioInicioTrabalho=" + horarioInicioTrabalho +
                ", horarioFimTrabalho=" + horarioFimTrabalho +
                '}';
    }
}