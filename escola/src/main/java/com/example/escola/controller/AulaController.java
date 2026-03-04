package com.example.escola.controller;

import com.example.escola.dto.aula.AulaRequisicaoDTO;
import com.example.escola.dto.aula.AulaRespostaDTO;
import com.example.escola.dto.curso.CursoRequisicaoDTO;
import com.example.escola.dto.curso.CursoRespostaDTO;
import com.example.escola.service.AulaService;
import com.example.escola.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/aula")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }


    @PostMapping
    public AulaRespostaDTO create(@RequestBody AulaRequisicaoDTO aulaRequisicaoDTO) throws SQLException {
        return aulaService.create(aulaRequisicaoDTO);

    }


    @GetMapping("/listarTodos")
    public List<AulaRespostaDTO> listAll() throws SQLException {
        return aulaService.listAll();

    }

    @GetMapping("/listarPorId/{id}")
    public AulaRespostaDTO listId(@PathVariable("id") int id) throws SQLException {
        return aulaService.listId(id);

    }

    @PutMapping("/atualizar/{id}")
    public AulaRespostaDTO update(@RequestBody AulaRequisicaoDTO aulaRequisicaoDTO, @PathVariable("id")int id)throws SQLException{
        return aulaService.update(aulaRequisicaoDTO, id);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")int id) throws SQLException {
        aulaService.delete(id);
    }

}
