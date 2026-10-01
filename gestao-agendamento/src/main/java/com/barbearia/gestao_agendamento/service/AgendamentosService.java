package com.barbearia.gestao_agendamento.service;

import com.barbearia.gestao_agendamento.dto.agendamento.AgendamentoRequestDTO;
import com.barbearia.gestao_agendamento.dto.agendamento.AgendamentoResponseDTO;
import com.barbearia.gestao_agendamento.model.Barbeiro;
import com.barbearia.gestao_agendamento.model.Cliente;
import com.barbearia.gestao_agendamento.model.Servico;
import com.barbearia.gestao_agendamento.repository.AgendamentoRepository;
import com.barbearia.gestao_agendamento.repository.BarbeiroRepository;
import com.barbearia.gestao_agendamento.repository.ClienteRepository;
import com.barbearia.gestao_agendamento.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AgendamentosService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentosService(AgendamentoRepository agendamentoRepository,
                               ClienteRepository clienteRepository,
                               BarbeiroRepository barbeiroRepository,
                               ServicoRepository servicoRepository){
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.barbeiroRepository = barbeiroRepository;
        this.servicoRepository = servicoRepository;
    }

    public AgendamentoResponseDTO marcarAgendamento(AgendamentoRequestDTO agendamentoRequestDTO){
        Cliente cliente = clienteRepository.findById(agendamentoRequestDTO.clienteId())
                .orElseThrow(() -> new NoSuchElementException("Não existe cliente para o id: "+agendamentoRequestDTO.clienteId()));

        Barbeiro barbeiro = barbeiroRepository.findById(agendamentoRequestDTO.barbeiroId())
                .orElseThrow(() -> new NoSuchElementException("Não existe barbeiro para o id: "+agendamentoRequestDTO.barbeiroId()));

        List<Servico> servicos = servicoRepository.findAllById(agendamentoRequestDTO.servicosIds());

        if(servicos.size() != agendamentoRequestDTO.servicosIds().size()){
            throw new IllegalArgumentException("Um ou mais serviços não foram encontrados.");
        }

        int duracaoTotalEmMinutos = servicos.stream()
                .map(Servico::getDuracao)
                .reduce(0, Integer::sum);

        LocalDateTime dataHoraFim = agendamentoRequestDTO.dataHora().plusMinutes(duracaoTotalEmMinutos);

        if(agendamentoRequestDTO.dataHora().isBefore(LocalDateTime.now().plusMinutes(30))){
            throw new IllegalArgumentException("O agendamento deve ser feito 30 minutos antecedencia!");
        }

        LocalTime inicio = agendamentoRequestDTO.dataHora().toLocalTime();
        LocalTime fim = dataHoraFim.toLocalTime();

        if(inicio.isBefore(barbeiro.getHorarioInicioTrabalho()) || fim.isAfter(barbeiro.getHorarioFimTrabalho())){
            throw new IllegalArgumentException("O agendamento está fora do expediente do barbeiro.");
        }

    }
}
