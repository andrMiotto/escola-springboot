package com.example.escola.repository;

import com.example.escola.model.Turma;
import com.example.escola.util.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaRepository {

    public Turma create(Turma turma) throws SQLException {

        String query = "INSERT INTO turma (nome, curso_id, professor_id) VALUES (?,?,?)";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCurso_id());
            stmt.setInt(3, turma.getProfessor_id());


            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                turma.setId(rs.getInt(1));
            }

        }

        return turma;
    }

    public List<Turma> listAll() throws SQLException {
        List<Turma> turmas = new ArrayList<>();

        String query = "SELECT nome, curso_id, professor_id FROM turma";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int curso_id = rs.getInt("curso_id");
                int professor_id = rs.getInt("professor_id");

                Turma turma = new Turma(id, nome, curso_id, professor_id);
                turmas.add(turma);
            }
        }

        return turmas;
    }

    public Turma listId(int id) throws SQLException {

        String query = "SELECT id, nome, curso_id,professor_id FROM turma WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                String nome = rs.getString("nome");
                int curso_id = rs.getInt("curso_id");
                int professor_id = rs.getInt("professor_id");

                return new Turma(id, nome, curso_id,professor_id);
            }
        }
        return null;
    }


    public void delete(int id) throws SQLException {

        String query = "DELETE FROM turma where id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }


    public Turma update(Turma turma, int id) throws SQLException {
        String query = "UPDATE turma SET nome = ?, curso_id = ?, professor_id = ? WHERE id = ?";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCurso_id());
            stmt.setInt(3, turma.getProfessor_id());
            stmt.setInt(4, turma.getId());


            stmt.executeUpdate();
        }


        return turma;
    }





}
