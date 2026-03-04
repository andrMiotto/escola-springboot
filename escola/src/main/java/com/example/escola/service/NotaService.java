package com.example.escola.service;

import com.example.escola.dto.nota.NotaRequisicaoDTO;
import com.example.escola.dto.nota.NotaRespostaDTO;
import com.example.escola.mapper.NotaMapper;
import com.example.escola.model.Nota;
import com.example.escola.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    private final NotaMapper notaMapper;

    public NotaService(NotaRepository notaRepository, NotaMapper notaMapper) {
        this.notaRepository = notaRepository;
        this.notaMapper = notaMapper;
    }


    public NotaRespostaDTO create(NotaRequisicaoDTO notaRequisicaoDTO) throws SQLException {

        Nota nota = notaMapper.paraEntidade(notaRequisicaoDTO);
        Nota salvo = notaRepository.create(nota);

        return notaMapper.paraRespostaDTO(salvo);


    }

    public List<NotaRespostaDTO> listAll() throws SQLException {
        List<Nota> notas = notaRepository.listAll();

        return notas.stream()
                .map(notaMapper::paraRespostaDTO)
                .toList();
    }


    public NotaRespostaDTO listId(int id) throws SQLException {
        Nota nota = notaRepository.listId(id);
        return notaMapper.paraRespostaDTO(nota);
    }

    public void delete(int id) throws SQLException {
        notaRepository.delete(id);

    }

    public NotaRespostaDTO update(NotaRequisicaoDTO notaRequisicaoDTO, int id) throws SQLException {
        Nota nota = notaMapper.paraEntidade(notaRequisicaoDTO);
        nota.setId(id);

        Nota salvo = notaRepository.update(nota, nota.getId());
        return notaMapper.paraRespostaDTO(salvo);
    }

}
