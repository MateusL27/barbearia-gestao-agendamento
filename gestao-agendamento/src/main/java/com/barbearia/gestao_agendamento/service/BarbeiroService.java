
package com.barbearia.gestao_agendamento.service;

import com.barbearia.gestao_agendamento.dto.barbeiro.BarbeiroRequestDTO;
import com.barbearia.gestao_agendamento.dto.barbeiro.BarbeiroResponseDTO;
import com.barbearia.gestao_agendamento.model.Barbeiro;
import com.barbearia.gestao_agendamento.repository.BarbeiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    public BarbeiroService (BarbeiroRepository barbeiroRepository) {
        this.barbeiroRepository = barbeiroRepository;
    }

    public BarbeiroResponseDTO salvarBarbeiro(BarbeiroRequestDTO dto) {

        if (barbeiroRepository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("Já existe um barbeiro cadastrado para o cpf " + dto.cpf());
        }

        Barbeiro barbeiro = new Barbeiro(dto);

        Barbeiro barbeiroSalvo = barbeiroRepository.save(barbeiro);

        return new BarbeiroResponseDTO(barbeiroSalvo);
    }

    public BarbeiroResponseDTO procurarBarbeiroPorId(Long id){

        Barbeiro barbeiro = barbeiroRepository.
                findById(id).orElseThrow(() -> new NoSuchElementException("Não existe um barbeiro para o id: "+id));

        return new BarbeiroResponseDTO(barbeiro);
    }

    public List<BarbeiroResponseDTO> procurarBarbeiros(){

        return barbeiroRepository.findAll()
                .stream()
                .map(barbeiro -> new BarbeiroResponseDTO(barbeiro))
                .toList();
    }

    public BarbeiroResponseDTO procurarPorCpf(String cpf){

        Barbeiro barbeiro = barbeiroRepository.findByCpf(cpf)
                .orElseThrow(() -> new NoSuchElementException("Não possui um barbeiro para o cpf: "+cpf));

        return new BarbeiroResponseDTO(barbeiro);
    }

    public List<BarbeiroResponseDTO> listarBarbeiros(){

        return barbeiroRepository.findAll()
                .stream()
                .map(barbeiro -> new BarbeiroResponseDTO(barbeiro))
                .toList();
    }

    public List<BarbeiroResponseDTO> procurarBarbeirosPorNome(String nome){

        return barbeiroRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(barbeiro -> new BarbeiroResponseDTO(barbeiro))
                .toList();
    }

    public BarbeiroResponseDTO atualizarBarbeiro(Long id, BarbeiroRequestDTO barbeiroRequestDTO){

        Barbeiro barbeiro = barbeiroRepository.
                findById(id).orElseThrow(() -> new NoSuchElementException("Não existe um barbeiro para o id: "+id));

        barbeiro.setNome(barbeiroRequestDTO.nome());
        barbeiro.setEspecialidade(barbeiroRequestDTO.especialidade());
        barbeiro.setHorarioInicioTrabalho(barbeiroRequestDTO.horarioInicioTrabalho());
        barbeiro.setHorarioFimTrabalho(barbeiroRequestDTO.horarioFimTrabalho());

        Barbeiro barbeiroSalvo = barbeiroRepository.save(barbeiro);

        return  new BarbeiroResponseDTO(barbeiroSalvo);
    }

    public void deletarBarbeiro(Long id){
        Barbeiro barbeiro = barbeiroRepository.
                findById(id).orElseThrow(() -> new NoSuchElementException("Não existe um barbeiro para o id: "+id));

        barbeiroRepository.delete(barbeiro);
    }
}
