package com.ufes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufes.LogSystem.ILogAdapter;
import com.ufes.LogSystem.LogAdapterFactory;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.services.ConnectionDBService;

public class UsuarioDAO {

	public UsuarioDAO() {
	}

	public void createTableUsuario() {
		String sql = "CREATE TABLE IF NOT EXISTS USUARIOS"
				+ "( ID INTEGER PRIMARY KEY AUTOINCREMENT"
				+ ", NOME VARCHAR(20)"
				+ ", EMAIL VARCHAR(20)"
				+ ", SENHA VARCHAR(20)"
				+ ", IS_ADMIN INTEGER"
				+ ", IS_AUTORIZADO INTEGER"
				+ ", DATA_CADASTRO VARCHAR(20))";
		try (Connection conn = ConnectionDBService.getConnection();
				Statement stt = conn.createStatement()) {

			stt.execute(sql);
			System.out.println("TABELA USUARIO CRIADA");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> findAll() {
		List<Usuario> usuarios = new ArrayList<>();

		try (Connection conn = ConnectionDBService.getConnection();
				Statement stt = conn.createStatement();
				ResultSet resultSet = stt.executeQuery("SELECT * FROM USUARIOS")) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			while (resultSet.next()) {
				Date dataCadastro = sdf.parse(resultSet.getString("DATA_CADASTRO"));
				boolean isAdmin = resultSet.getBoolean("IS_ADMIN");
				boolean isAutorizado = resultSet.getBoolean("IS_AUTORIZADO");

				usuarios.add(new Usuario(
						resultSet.getInt("ID"),
						resultSet.getString("NOME"),
						resultSet.getString("SENHA"),
						resultSet.getString("EMAIL"),
						dataCadastro,
						isAdmin,
						isAutorizado));
			}

		} catch (SQLException | ParseException e) {
			throw new RuntimeException(e);
		}
		return usuarios;
	}

	public void update(Usuario usuario) {
		String sql = "UPDATE USUARIOS SET NOME = ?, SENHA = ?,EMAIL = ?, IS_ADMIN = ?, IS_AUTORIZADO = ? WHERE ID = ?";

		try (Connection conn = ConnectionDBService.getConnection();
				PreparedStatement stt = conn.prepareStatement(sql)) {

			stt.setString(1, usuario.getNome());
			stt.setString(2, usuario.getSenha());
			stt.setString(2, usuario.getEmail());
			stt.setInt(3, usuario.isAdmin() ? 1 : 0);
			stt.setInt(4, usuario.isAutorizado() ? 1 : 0);
			stt.setInt(5, usuario.getId());

			stt.executeUpdate();

		} catch (SQLException e) {
			executeLog("Alteração", usuario, e);
			throw new RuntimeException(e);
		}
	}

	public void insert(Usuario usuario) {
		String sql = "INSERT INTO USUARIOS (NOME, SENHA,EMAIL, IS_ADMIN, IS_AUTORIZADO, DATA_CADASTRO) VALUES (?, ?, ?, ?, ?, ?)";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dataCadastro = formatter.format(usuario.getDataCadastro());

		try (Connection conn = ConnectionDBService.getConnection();
				PreparedStatement stt = conn.prepareStatement(sql)) {

			stt.setString(1, usuario.getNome());
			stt.setString(2, usuario.getSenha());
			stt.setString(3, usuario.getEmail());
			stt.setInt(4, usuario.isAdmin() ? 1 : 0);
			stt.setInt(5, usuario.isAutorizado() ? 1 : 0);
			stt.setString(6, dataCadastro);

			stt.executeUpdate();

        } catch (SQLException e) {
            executeLog("Inclusão", usuario, e);
            throw new RuntimeException(e);
        }
    }

	public Usuario findByID(int id) {
		Usuario usuario = null;
		String sql = "SELECT * FROM USUARIOS WHERE ID = ?";

		try (Connection conn = ConnectionDBService.getConnection();
				PreparedStatement stt = conn.prepareStatement(sql)) {

			stt.setInt(1, id);
			try (ResultSet resultSet = stt.executeQuery()) {
				if (resultSet.next()) {
					Date dataCadastro = new SimpleDateFormat("dd/MM/yyyy").parse(resultSet.getString("DATA_CADASTRO"));
					usuario = new Usuario(
							resultSet.getInt("ID"),
							resultSet.getString("NOME"),
							resultSet.getString("SENHA"),
							resultSet.getString("EMAIL"),
							dataCadastro,
							resultSet.getBoolean("IS_ADMIN"),
							resultSet.getBoolean("IS_AUTORIZADO"));
				}
			}

		} catch (SQLException | ParseException e) {
			throw new RuntimeException(e);
		}
		return usuario;
	}

	public Usuario findByEmailESenha(String email, String senha) {
		Usuario usuario = null;
		String sql = "SELECT * FROM USUARIOS WHERE EMAIL = ? AND SENHA = ?";

		try (Connection conn = ConnectionDBService.getConnection();
				PreparedStatement stt = conn.prepareStatement(sql)) {

			stt.setString(1, email);
			stt.setString(2, senha);
			try (ResultSet resultSet = stt.executeQuery()) {
				if (resultSet.next()) {
					Date dataCadastro = new SimpleDateFormat("dd/MM/yyyy").parse(resultSet.getString("DATA_CADASTRO"));
					usuario = new Usuario(
							resultSet.getInt("ID"),
							resultSet.getString("NOME"),
							resultSet.getString("SENHA"),
							resultSet.getString("EMAIL"),
							dataCadastro,
							resultSet.getBoolean("IS_ADMIN"),
							resultSet.getBoolean("IS_AUTORIZADO")

					);
				}
			}

		} catch (SQLException | ParseException e) {
			throw new RuntimeException(e);
		}
		return usuario;
	}

	public void remove(Usuario usuario) {
		String sql = "DELETE FROM USUARIOS WHERE ID = ?";

		try (Connection conn = ConnectionDBService.getConnection();
				PreparedStatement stt = conn.prepareStatement(sql)) {

			stt.setInt(1, usuario.getId());
			stt.executeUpdate();

		} catch (SQLException e) {
			executeLog("Exclusão", usuario, e);

			throw new RuntimeException(e);

		}

	}

	private void executeLog(String operacao, Usuario usuario, SQLException e) {
		List<ILogAdapter> logAdapters = LogAdapterFactory.getLogAdapters(); // Obtém a lista de adaptadores de log
		for (ILogAdapter logAdapter : logAdapters) {
			logAdapter.logFalhaOperacao(operacao, UsuarioLogado.getINSTANCE().getNome(), usuario.getNome(),
					e.getMessage());
		}
	}
}
