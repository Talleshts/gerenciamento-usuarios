/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.DAO;

import com.ufes.model.Notificacao;
import com.ufes.model.Usuario;
import com.ufes.services.ConnectionDBService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author talle
 */
public class NotificacaoDAO {
    public static void createTableNotificacao() throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS Notificacao (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_usuario INTEGER NOT NULL REFERENCES Usuario(id),
                    mensagem VARCHAR NOT NULL,
                    visualizada INT DEFAULT 0,
                    data VARCHAR NOT NULL
                );
                """;
        try (Connection conn = ConnectionDBService.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela notificações: " + e.getMessage());
        }
    }

    public void insert(Notificacao notificacao) throws Exception {
        String SQL = "INSERT INTO Notificacao(mensagem, id_usuario, data) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDBService.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, notificacao.getMensagem());
            ps.setInt(2, notificacao.getUsuario().getId());
            ps.setString(3, LocalDateTime.now().toString());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao inserir");
        }
    }

    public void update(Notificacao notificacao) throws Exception {
        String SQL = "UPDATE Notificacao SET visualizada = ? WHERE id = ?";
        try (Connection conn = ConnectionDBService.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setBoolean(1, notificacao.isVisualizou());
            ps.setInt(2, notificacao.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar");
        }
    }

    public void delete(Notificacao notificacao) throws Exception {
        String SQL = "DELETE FROM Notificacao WHERE id = ? AND id_usuario = ?";
        try (Connection conn = ConnectionDBService.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, notificacao.getId());
            ps.setInt(2, notificacao.getUsuario().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao excluir");
        }
    }

    public void deleteAllByIdUsuario(int idUsuario) throws Exception {
        String SQL = "DELETE FROM Notificacao WHERE id_usuario = ?";
        try (Connection conn = ConnectionDBService.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao excluir");
        }
    }

    public List<Notificacao> getAllByIdUsuario(int idUsuario) throws Exception {
        List<Notificacao> notificacoes = new ArrayList<>();
        String SQL = """
                SELECT n.id, n.mensagem, n.id_usuario, n.visualizada, n.data
                FROM Notificacao n WHERE n.id_usuario = ?;
                """;
        try (Connection conn = ConnectionDBService.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notificacao n = new Notificacao();
                Usuario u = new Usuario();
                n.setId(rs.getInt(1));
                n.setMensagem(rs.getString(2));
                n.setUsuario(u.temId(rs.getInt(3)));
                n.setVisualizou(rs.getBoolean(4));
                n.setDataEnvio(LocalDateTime.parse(rs.getString(5)));
                notificacoes.add(n);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar");
        }
        return notificacoes;
    }

    public Notificacao getById(int idNotificacao) throws Exception {
        String SQL = """
                SELECT n.id, n.mensagem, n.id_usuario
                FROM Notificacao n WHERE n.id = ?;
                """;
        try (Connection conn = ConnectionDBService.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, idNotificacao);
            ResultSet rs = ps.executeQuery();
            Notificacao n = new Notificacao();
            Usuario u = new Usuario();
            if (rs.next()) {
                n.setId(rs.getInt(1));
                n.setMensagem(rs.getString(2));
                n.setUsuario(u.temId(rs.getInt(3)));
            }
            return n;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar");
        }
    }

    public int countNotificacoesPendenteByUsuario(int idUsuario) throws Exception {
        String SQL = """
                SELECT count(1)
                FROM Notificacao n
                WHERE n.id_usuario = ? AND n.visualizada = 0;
                """;
        try (Connection conn = ConnectionDBService.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar");
        }
        return 0;
    }

    public int countNotificacoesVisualizadasByUsuario(int idUsuario) throws Exception {
        String SQL = """
                SELECT count(1)
                FROM Notificacao n
                WHERE n.id_usuario = ? AND n.visualizada = 1;
                """;
        try (Connection conn = ConnectionDBService.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar");
        }
        return 0;
    }
}
