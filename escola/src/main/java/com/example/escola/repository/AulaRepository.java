package com.example.escola.repository;

import com.example.escola.model.Aula;
import com.example.escola.model.Curso;
import com.example.escola.util.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaRepository {

    public Aula create(Aula aula) throws SQLException {

        String query = "INSERT INTO aula (turma_id, data_hora, assunto) VALUES (?,?,?)";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, aula.getTurma_id());
            stmt.setDate(2, aula.getData_hora());
            stmt.setString(3, aula.getAssunto());


            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                aula.setId(rs.getInt(1));
            }

        }

        return aula;
    }

    public List<Aula> listAll() throws SQLException {
        List<Aula> aulas = new ArrayList<>();

        String query = "SELECT id,turma_id, data_hora, assunto FROM aula";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int turma_id = rs.getInt("turma_id");
                Date data_hora = rs.getDate("data_hora");
                String assunto = rs.getString("assunto");


                Aula aula = new Aula(id, turma_id, data_hora,assunto);
                aulas.add(aula);
            }
        }

        return aulas;
    }

    public Aula listId(int id) throws SQLException {

        String query = "SELECT id,turma_id, data_hora, assunto FROM aula WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int turma_id = rs.getInt("turma_id");
                Date data_hora = rs.getDate("data_hora");
                String assunto = rs.getString("assunto");
                id = rs.getInt("id");


                return new Aula(id, turma_id, data_hora,assunto);
            }
        }
        return null;
    }


    public void delete(int id) throws SQLException {

        String query = "DELETE FROM aula where id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }


    public Aula update(Aula aula, int id) throws SQLException {
        String query = "UPDATE aula SET turma_id = ?, data_hora = ?, assunto = ? WHERE id = ?";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, aula.getTurma_id());
            stmt.setDate(2, aula.getData_hora());
            stmt.setString(3, aula.getAssunto());


            stmt.executeUpdate();
        }


        return aula;
    }


}
