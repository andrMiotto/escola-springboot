package com.example.escola.dto.aula;

import java.sql.Date;

public record AulaRequisicaoDTO(

        int turma_id,
        Date data_hora,
        String assunto
) {
}
