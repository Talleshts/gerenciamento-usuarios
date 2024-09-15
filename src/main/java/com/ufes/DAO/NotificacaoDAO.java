/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ufes.model.Notificacao;
import com.ufes.services.ConnectionDBService;

/**
 *
 * @author talle
 */
public class NotificacaoDAO {
	private Connection connection;

	public NotificacaoDAO() throws SQLException {
	}

	public void createTableNotificacao() {
		String sql = "CREATE TABLE IF NOT EXISTS Notificacao "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "mensagem TEXT, "
				+ "titulo TEXT, "
				+ "id_usuario INTEGER, "
				+ "dataEnvio TEXT, "
				+ "visualizada INTEGER)";
		try (Connection conn = ConnectionDBService.getConnection();
				Statement stt = conn.createStatement()) {

			stt.execute(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insert(Notificacao notificacao) throws Exception {
		String SQL = "INSERT INTO Notificacao(mensagem, titulo, id_usuario, dataEnvio) VALUES (?, ?, ?, ?)";
		try (Connection conn = ConnectionDBService.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL)) {
			ps.setString(1, notificacao.getMensagem());
			ps.setString(2, notificacao.getTitulo());
			ps.setInt(3, notificacao.getUsuario());
			ps.setString(4, LocalDateTime.now().toString());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception("Erro ao inserir");
		}
	}

	public void update(Notificacao notificacao) throws Exception {
		String SQL = "UPDATE Notificacao SET visualizou = ? WHERE id = ?";
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
			ps.setInt(2, notificacao.getUsuario());
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
				n.setId(rs.getInt(1));
				n.setMensagem(rs.getString(2));
				n.setTitulo(rs.getString(3));
				// n.setUsuario(u.temId(rs.getInt(4)));
				n.setVisualizou(rs.getBoolean(5));
				n.setDataEnvio(LocalDateTime.parse(rs.getString(6)));
				notificacoes.add(n);
			}
		} catch (Exception e) {
			throw new Exception("Erro ao buscar");
		}
		return notificacoes;
	}

	public Notificacao getById(int idNotificacao) throws Exception {
		String SQL = """
				SELECT n.id, n.mensagem, n.titulo, n.id_usuario
				FROM Notificacao n WHERE n.id = ?;
				""";
		try (Connection conn = ConnectionDBService.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL)) {
			ps.setInt(1, idNotificacao);
			ResultSet rs = ps.executeQuery();
			Notificacao n = new Notificacao();
			if (rs.next()) {
				n.setId(rs.getInt(1));
				n.setMensagem(rs.getString(2));
				n.setUsuarioId(rs.getInt(3));
				n.setTitulo(rs.getString(4));

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
			System.err.println(e);
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
