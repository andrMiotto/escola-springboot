package com.example.escola.service;

import com.example.escola.dto.professor.ProfessorRequiscaoDTO;
import com.example.escola.dto.professor.ProfessorRespostaDTO;
import com.example.escola.dto.turma.TurmaRequisicaoDTO;
import com.example.escola.dto.turma.TurmaRespostaDTO;
import com.example.escola.mapper.TurmaMapper;
import com.example.escola.model.Professor;
import com.example.escola.model.Turma;
import com.example.escola.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    private final TurmaMapper turmaMapper;


    public TurmaService(TurmaRepository turmaRepository, TurmaMapper turmaMapper) {
        this.turmaRepository = turmaRepository;
        this.turmaMapper = turmaMapper;
    }

    public TurmaRespostaDTO create(TurmaRequisicaoDTO turmaRequisicaoDTO) throws SQLException {

        Turma turma = turmaMapper.paraEntidade(turmaRequisicaoDTO);
        Turma salvo = turmaRepository.create(turma);

        return turmaMapper.paraRespostaDTO(salvo);


    }

    public List<TurmaRespostaDTO> listAll() throws SQLException {
        List<Turma> turmas = turmaRepository.listAll();

        return turmas.stream()
                .map(turmaMapper::paraRespostaDTO)
                .toList();
    }


    public TurmaRespostaDTO listId(int id) throws SQLException {
        Turma turma = turmaRepository.listId(id);
        return turmaMapper.paraRespostaDTO(turma);
    }

    public void delete(int id) throws SQLException {
        turmaRepository.delete(id);

    }

    public TurmaRespostaDTO update(TurmaRequisicaoDTO turmaRequisicaoDTO, int id) throws SQLException {
        Turma turma = turmaMapper.paraEntidade(turmaRequisicaoDTO);
        turma.setId(id);

        Turma salvo = turmaRepository.update(turma, turma.getId());
        return turmaMapper.paraRespostaDTO(salvo);
    }

}
