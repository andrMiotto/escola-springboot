package com.example.escola.controller;

import com.example.escola.dto.curso.CursoRequisicaoDTO;
import com.example.escola.dto.curso.CursoRespostaDTO;
import com.example.escola.dto.professor.ProfessorRequiscaoDTO;
import com.example.escola.dto.professor.ProfessorRespostaDTO;
import com.example.escola.service.CursoService;
import com.example.escola.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }


    @PostMapping
    public CursoRespostaDTO create(@RequestBody CursoRequisicaoDTO cursoRequisicaoDTO) throws SQLException {
        return cursoService.create(cursoRequisicaoDTO);

    }


    @GetMapping("/listarTodos")
    public List<CursoRespostaDTO> listAll() throws SQLException {
        return cursoService.listAll();

    }

    @GetMapping("/listarPorId/{id}")
    public CursoRespostaDTO listId(@PathVariable("id") int id) throws SQLException {
        return cursoService.listId(id);

    }

    @PutMapping("/atualizar/{id}")
    public CursoRespostaDTO update(@RequestBody CursoRequisicaoDTO cursoRequisicaoDTO, @PathVariable("id")int id)throws SQLException{
        return cursoService.update(cursoRequisicaoDTO, id);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")int id) throws SQLException {
        cursoService.delete(id);
    }

}
