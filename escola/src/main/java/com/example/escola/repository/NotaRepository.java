package com.example.escola.repository;

import com.example.escola.model.Aula;
import com.example.escola.model.Nota;
import com.example.escola.util.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class NotaRepository {


    public Nota create(Nota nota) throws SQLException {

        String query = "INSERT INTO nota (aluno_id, aula_id, valor) VALUES (?,?,?)";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1,nota.getAluno_id());
            stmt.setInt(2,nota.getAula_id());
            stmt.setDouble(3,nota.getValor());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                nota.setId(rs.getInt(1));
            }

        }

        return nota;
    }

    public List<Nota> listAll() throws SQLException {
        List<Nota> notas = new ArrayList<>();

        String query = "SELECT id,aluno_id, aula_id, valor FROM nota";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int aluno_id = rs.getInt("aluno_id");
                int aula_id = rs.getInt("aula_id");
                Double valor = rs.getDouble("valor");


                Nota nota = new Nota(id, aluno_id,aula_id,valor);
                notas.add(nota);
            }
        }

        return notas;
    }

    public Nota listId(int id) throws SQLException {

        String query = "SELECT id,aluno_id, aula_id, valor FROM nota WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int aluno_id = rs.getInt("aluno_id");
                int aula_id = rs.getInt("aula_id");
                Double valor = rs.getDouble("valor");
                id = rs.getInt("id");


                return new Nota(id, aluno_id,aula_id,valor);
            }
        }
        return null;
    }


    public void delete(int id) throws SQLException {

        String query = "DELETE FROM nota where id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }


    public Nota update(Nota nota, int id) throws SQLException {
        String query = "UPDATE nota SET aluno_id = ? , aula_id = ?, valor = ? WHERE id = ?";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1,nota.getAluno_id());
            stmt.setInt(2,nota.getAula_id());
            stmt.setDouble(3,nota.getValor());


            stmt.executeUpdate();
        }


        return nota;
    }
}
