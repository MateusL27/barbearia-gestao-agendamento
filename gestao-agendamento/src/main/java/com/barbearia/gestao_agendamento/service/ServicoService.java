package com.barbearia.gestao_agendamento.service;

import com.barbearia.gestao_agendamento.dto.servico.ServicoRequestDTO;
import com.barbearia.gestao_agendamento.dto.servico.ServicoResponseDTO;
import com.barbearia.gestao_agendamento.model.Servico;
import com.barbearia.gestao_agendamento.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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

    public ServicoResponseDTO procurarServicoPorId(Long id){

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe serviço para o id: "+id));

        return new ServicoResponseDTO(servico);
    }

    public List<ServicoResponseDTO> procurarServicos(){

        return servicoRepository.findAll()
                .stream()
                .map(servico -> new ServicoResponseDTO(servico))
                .toList();
    }

    public List<ServicoResponseDTO> procurarServicosPorNome(String nome){

        return servicoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(servico -> new ServicoResponseDTO(servico))
                .toList();
    }

    public void deletarServico(Long id){

        if(!servicoRepository.existsById(id)){
            throw new NoSuchElementException("Não existe servico para o id: "+id);
        }

        servicoRepository.deleteById(id);
    }

    @Transactional
    public ServicoResponseDTO atualizarServico(Long id, ServicoRequestDTO requestDTO){

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe serviço para o id: "+id));

        servico.setNome(requestDTO.nome());
        servico.setPreco(requestDTO.preco());
        servico.setDuracao(requestDTO.duracao());

        return new ServicoResponseDTO(servico);

    }

}
