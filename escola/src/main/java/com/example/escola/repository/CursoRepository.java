package com.example.escola.repository;

import com.example.escola.dto.curso.CursoRespostaDTO;
import com.example.escola.model.Aluno;
import com.example.escola.model.Curso;
import com.example.escola.util.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {


    public Curso create(Curso curso) throws SQLException {

        String query = "INSERT INTO curso (nome, codigo) VALUES (?,?)";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());


            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                curso.setId(rs.getInt(1));
            }

        }

        return curso;
    }

    public List<Curso> listAll() throws SQLException {
        List<Curso> cursos = new ArrayList<>();

        String query = "SELECT id,nome, codigo FROM curso";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");


                Curso curso = new Curso(id, nome, codigo);
                cursos.add(curso);
            }
        }

        return cursos;
    }

    public Curso listId(int id) throws SQLException {

        String query = "SELECT id,nome,codigo FROM curso WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");


                return new Curso(id, nome, codigo);
            }
        }
        return null;
    }


    public void delete(int id) throws SQLException {

        String query = "DELETE FROM curso where id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }


    public Curso update(Curso curso, int id) throws SQLException {
        String query = "UPDATE curso SET nome = ?, codigo = ? WHERE id = ?";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setInt(3, curso.getId());


            stmt.executeUpdate();
        }


        return curso;
    }





}
