package com.example.escola.dto.aula;

import java.sql.Date;

public record AulaRespostaDTO(
        int id,
        int turma_id,
        Date data_hora,
        String assunto

) {
}
