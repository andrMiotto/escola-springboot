package com.example.escola.mapper;

import com.example.escola.dto.curso.CursoRequisicaoDTO;
import com.example.escola.dto.curso.CursoRespostaDTO;
import com.example.escola.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {


    public Curso paraEntidade(CursoRequisicaoDTO cursoRequisicaoDTO) {
        return new Curso(cursoRequisicaoDTO.nome(), cursoRequisicaoDTO.codigo());

    }


    public CursoRespostaDTO paraRespostaDTO(Curso curso) {
        return new CursoRespostaDTO(curso.getId(), curso.getNome(), curso.getCodigo());

    }

}
