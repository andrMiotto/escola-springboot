package com.example.escola.repository;

import com.example.escola.model.Aluno;
import com.example.escola.util.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepository {

    public Aluno createAluno(Aluno aluno) throws SQLException {

        String query = "INSERT INTO aluno (nome, email, matricula, data_nascimento) VALUES (?,?,?,?)";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setInt(3, aluno.getMatricula());
            stmt.setDate(4, aluno.getDataNascimento());


            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                aluno.setId(rs.getInt(1));
            }

        }

        return aluno;
    }

    public List<Aluno> listAllAluno() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();

        String query = "SELECT id,nome, email, matricula, data_nascimento FROM aluno";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int matricula = rs.getInt("matricula");
                Date data_nascimento = rs.getDate("data_nascimento");

                Aluno aluno = new Aluno(id, nome, email, matricula, data_nascimento);
                alunos.add(aluno);
            }
        }

        return alunos;
    }

    public Aluno listAlunoId(int id) throws SQLException {

        String query = "SELECT id,nome, email, matricula, data_nascimento FROM aluno WHERE id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int matricula = rs.getInt("matricula");
                Date data_nascimento = rs.getDate("data_nascimento");

                return new Aluno(id, nome, email, matricula, data_nascimento);
            }
        }
        return null;
    }


    public void deleteAluno(int id) throws SQLException {

        String query = "DELETE FROM aluno where id = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }


    public Aluno updateAluno(Aluno aluno, int id) throws SQLException {
        String query = "UPDATE aluno SET nome = ?, email = ?, matricula = ? ,data_nascimento = ? WHERE id = ?";


        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setInt(3, aluno.getMatricula());
            stmt.setDate(4, (Date) aluno.getDataNascimento());
            stmt.setInt(5, aluno.getId());


            stmt.executeUpdate();
        }


        return aluno;
    }

}
