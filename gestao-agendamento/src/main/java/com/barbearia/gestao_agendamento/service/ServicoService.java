package com.barbearia.gestao_agendamento.service;

import com.barbearia.gestao_agendamento.dto.servico.ServicoRequestDTO;
import com.barbearia.gestao_agendamento.dto.servico.ServicoResponseDTO;
import com.barbearia.gestao_agendamento.model.Servico;
import com.barbearia.gestao_agendamento.repository.ServicoRepository;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository){
        this.servicoRepository = servicoRepository;
    }

    public ServicoResponseDTO salvarServico(ServicoRequestDTO servico){

        if(servicoRepository.existsByNome(servico.nome())){
            throw new IllegalArgumentException("Já existe o serviço: "+servico.nome());
        }

        Servico novoServico = new Servico(servico);

        Servico servicoSalvo = servicoRepository.save(novoServico);

        return new ServicoResponseDTO(servicoSalvo);

    }

}
