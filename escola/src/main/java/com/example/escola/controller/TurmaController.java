package com.example.escola.controller;

import com.example.escola.dto.professor.ProfessorRequiscaoDTO;
import com.example.escola.dto.professor.ProfessorRespostaDTO;
import com.example.escola.dto.turma.TurmaRequisicaoDTO;
import com.example.escola.dto.turma.TurmaRespostaDTO;
import com.example.escola.service.TurmaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;


    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public TurmaRespostaDTO create(@RequestBody TurmaRequisicaoDTO turmaRequisicaoDTO) throws SQLException {
        return turmaService.create(turmaRequisicaoDTO);

    }


    @GetMapping("/listarTodos")
    public List<TurmaRespostaDTO> listAll() throws SQLException {
        return turmaService.listAll();

    }

    @GetMapping("/listarPorId/{id}")
    public TurmaRespostaDTO listId(@PathVariable("id") int id) throws SQLException {
        return turmaService.listId(id);

    }

    @PutMapping("/atualizar/{id}")
    public TurmaRespostaDTO update(@RequestBody TurmaRequisicaoDTO turmaRequisicaoDTO, @PathVariable("id")int id)throws SQLException{
        return turmaService.update(turmaRequisicaoDTO, id);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")int id) throws SQLException {
        turmaService.delete(id);
    }


}
