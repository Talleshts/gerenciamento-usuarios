package com.ufes.DAO;


import com.ufes.model.Usuario;
import com.ufes.services.ConnectionDBService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioDAO {


    public UsuarioDAO() {
        createTableUsuario();
    }
    public void createTableUsuario() {
        Connection conn;
        conn = ConnectionDBService.getInstance().getConnection();
        Statement stt;
        try {
            stt = conn.createStatement();
            String sql = "CREATE TABLE  IF NOT EXISTS USUARIOS"
                    + "( ID INTEGER PRIMARY KEY AUTOINCREMENT"
                    + ", NOME VARCHAR(20)"
                    + ", SENHA VARCHAR(20)"
                    + ", IS_ADMIN INTEGER"
                    + ", IS_AUTORIZADO INTEGER"
                    + ", DATA_CADASTRO VARCHAR(20))";

            stt.execute(sql);
            conn.close();
            stt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn;
        conn = ConnectionDBService.getInstance().getConnection();
        try {
            Statement stt = conn.createStatement();
            String sql = "SELECT * FROM USUARIOS";
            ResultSet resultSet = stt.executeQuery(sql);
            while (resultSet.next()) {
                Date dataCadastro = new SimpleDateFormat("dd/MM/yyyy").parse(resultSet.getString("DATA_CADASTRO"));
                boolean isAdmin = resultSet.getBoolean("IS_ADMIN");
                boolean isAutorizado = resultSet.getBoolean("IS_AUTORIZADO");
                usuarios.add(
                        new Usuario(resultSet.getInt("ID"),
                                resultSet.getString("NOME"),
                                resultSet.getString("SENHA"),
                                dataCadastro,
                                isAdmin,
                                isAutorizado
                                ));
                conn.close();
                stt.close();
                resultSet.close();
            }
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    public void update(Usuario usuario) {
        Connection conn;
        conn = ConnectionDBService.getInstance().getConnection();
        try {
            Statement stt = conn.createStatement();
            String sql = String.format("UPDATE USUARIOS SET NOME = '%s', SENHA = '%s', IS_ADMIN = %d, IS_AUTORIZADO = %d WHERE USUARIOS.ID = '%d'",
                    usuario.getNome(), usuario.getSenha(), (usuario.isAdmin()?1:0), (usuario.isAutorizado()?1:0), usuario.getId());
            stt.execute(sql);

            conn.close();
            stt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Usuario usuario) {
        Connection conn;
        conn = ConnectionDBService.getInstance().getConnection();

        try {
            Statement stt = conn.createStatement();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dataCadastro = formatter.format(usuario.getDataCadastro());
            String sql = String.format("INSERT INTO USUARIOS ( NOME, SENHA, IS_ADMIN, IS_AUTORIZADO, DATA_CADASTRO) VALUES  ('%s', '%s', %d, %d, '%s')",
                    usuario.getNome(),
                    usuario.getSenha(),
                    (usuario.isAdmin()?1:0),
                    (usuario.isAutorizado()?1:0),
                    dataCadastro);
            stt.execute(sql);

            conn.close();
            stt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Usuario findByID(int id) {
        Usuario usuario = null;
        Connection conn;
        conn = ConnectionDBService.getInstance().getConnection();
        try {
            Statement stt = conn.createStatement();
            String sql = "SELECT * FROM USUARIOS WHERE USUARIOS.ID = " + id;

            ResultSet resultSet = stt.executeQuery(sql);
            Date dataCadastro = new SimpleDateFormat("dd/MM/yyyy").parse(resultSet.getString("DATA_CADASTRO"));
            usuario = new Usuario(resultSet.getInt("ID"),
                    resultSet.getString("NOME"),
                    resultSet.getString("SENHA"),
                    dataCadastro,
                    resultSet.getBoolean("IS_ADMIN"),
                    resultSet.getBoolean("IS_AUTORIZADO")
                    );
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
