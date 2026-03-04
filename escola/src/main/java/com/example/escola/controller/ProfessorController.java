package com.example.escola.controller;

import com.example.escola.dto.professor.ProfessorRequiscaoDTO;
import com.example.escola.dto.professor.ProfessorRespostaDTO;
import com.example.escola.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;


    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorRespostaDTO create(@RequestBody ProfessorRequiscaoDTO professorRequiscaoDTO) throws SQLException {
        return professorService.create(professorRequiscaoDTO);

    }


    @GetMapping("/listarTodos")
    public List<ProfessorRespostaDTO> listAll() throws SQLException {
        return professorService.listAll();

    }

    @GetMapping("/listarPorId/{id}")
    public ProfessorRespostaDTO listId(@PathVariable("id") int id) throws SQLException {
        return professorService.listId(id);

    }

    @PutMapping("/atualizar/{id}")
    public ProfessorRespostaDTO update(@RequestBody ProfessorRequiscaoDTO professorRequiscaoDTO, @PathVariable("id")int id)throws SQLException{
        return professorService.update(professorRequiscaoDTO, id);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")int id) throws SQLException {
        professorService.delete(id);
    }



}
