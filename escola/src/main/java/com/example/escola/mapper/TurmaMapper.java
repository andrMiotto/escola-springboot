package com.example.escola.mapper;

import com.example.escola.dto.turma.TurmaRequisicaoDTO;
import com.example.escola.dto.turma.TurmaRespostaDTO;
import com.example.escola.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public Turma paraEntidade(TurmaRequisicaoDTO turmaRequisicaoDTO) {
        return new Turma(turmaRequisicaoDTO.nome(), turmaRequisicaoDTO.curso_id(), turmaRequisicaoDTO.professor_id());
    }


    public TurmaRespostaDTO paraRespostaDTO(Turma turma) {
        return new TurmaRespostaDTO(turma.getId(), turma.getNome(), turma.getCurso_id(), turma.getProfessor_id());
    }


}
