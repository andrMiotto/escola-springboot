package com.example.escola.mapper;

import com.example.escola.dto.nota.NotaRequisicaoDTO;
import com.example.escola.dto.nota.NotaRespostaDTO;
import com.example.escola.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {


    public Nota paraEntidade(NotaRequisicaoDTO notaRequisicaoDTO) {

        return new Nota(notaRequisicaoDTO.aluno_id(), notaRequisicaoDTO.aula_id(), notaRequisicaoDTO.valor());

    }


    public NotaRespostaDTO paraRespostaDTO(Nota nota) {

        return new NotaRespostaDTO(nota.getId(), nota.getAluno_id(), nota.getAula_id(), nota.getValor());

    }

}
