package com.example.escola.controller;

import com.example.escola.dto.nota.NotaRequisicaoDTO;
import com.example.escola.dto.nota.NotaRespostaDTO;
import com.example.escola.dto.professor.ProfessorRequiscaoDTO;
import com.example.escola.dto.professor.ProfessorRespostaDTO;
import com.example.escola.service.NotaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/nota")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }


    @PostMapping
    public NotaRespostaDTO create(@RequestBody NotaRequisicaoDTO notaRequisicaoDTO) throws SQLException {
        return notaService.create(notaRequisicaoDTO);

    }


    @GetMapping("/listarTodos")
    public List<NotaRespostaDTO> listAll() throws SQLException {
        return notaService.listAll();

    }

    @GetMapping("/listarPorId/{id}")
    public NotaRespostaDTO listId(@PathVariable("id") int id) throws SQLException {
        return notaService.listId(id);

    }

    @PutMapping("/atualizar/{id}")
    public NotaRespostaDTO update(@RequestBody NotaRequisicaoDTO notaRequisicaoDTO, @PathVariable("id")int id)throws SQLException{
        return notaService.update(notaRequisicaoDTO, id);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")int id) throws SQLException {
        notaService.delete(id);
    }

}
