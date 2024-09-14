/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.DAO;

import java.sql.SQLException;

/**
 *
 * @author talle
 */
public class IniciadorDataBase {
      public static void initialize() throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.createTableUsuario();

        NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
        notificacaoDAO.createTableNotificacao();
    }  
}
