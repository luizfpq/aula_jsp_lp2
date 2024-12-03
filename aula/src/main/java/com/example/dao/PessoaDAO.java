package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.pessoa.Pessoa;
import com.example.util.ConexaoFactory;

public class PessoaDAO {

    // Método para inserir uma pessoa no banco
    public void inserir(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, email) VALUES (?, ?)";

        try (Connection connection = ConexaoFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            
            stmt.executeUpdate();
        }
    }

    // Método para listar todas as pessoas
    public List<Pessoa> listar() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT idPessoa, nome, email FROM pessoa";

        try (Connection connection = ConexaoFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }

    // Método para atualizar uma pessoa
    public void atualizar(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, email = ? WHERE idPessoa = ?";

        try (Connection connection = ConexaoFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setInt(3, pessoa.getIdPessoa());
            
            stmt.executeUpdate();
        }
    }

    // Método para excluir uma pessoa
    public void excluir(int idPessoa) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE idPessoa = ?";

        try (Connection connection = ConexaoFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, idPessoa);
            stmt.executeUpdate();
        }
    }

    // Método salvar, que chama inserir ou atualizar conforme necessário
    public void salvar(Pessoa pessoa) throws SQLException {
        if (pessoa.getIdPessoa() == 0) {
            inserir(pessoa);  // Se o ID não estiver setado, insere a pessoa
        } else {
            atualizar(pessoa);  // Caso contrário, atualiza a pessoa
        }
    }
}
