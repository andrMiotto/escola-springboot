package com.example.escola.mapper;

import com.example.escola.dto.aula.AulaRequisicaoDTO;
import com.example.escola.dto.aula.AulaRespostaDTO;
import com.example.escola.model.Aula;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class AulaMapper {

    public Aula paraEntidade(AulaRequisicaoDTO aulaRequisicaoDTO){
        return new Aula(aulaRequisicaoDTO.turma_id(),aulaRequisicaoDTO.data_hora(),aulaRequisicaoDTO.assunto());

    }

    public AulaRespostaDTO paraRespostaDTO(Aula aula){
        return new AulaRespostaDTO(aula.getId(), aula.getTurma_id(),aula.getData_hora(), aula.getAssunto());
    }


}
