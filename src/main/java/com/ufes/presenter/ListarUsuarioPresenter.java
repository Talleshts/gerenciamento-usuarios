/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.view.ListarUsuarioView;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author talle
 */
public class ListarUsuarioPresenter {

 private ListarUsuarioView view;
    private UsuarioDAO usuarioDAO;

    public ListarUsuarioPresenter(ListarUsuarioView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        carregarUsuarios();
    }

    private void carregarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.findAll();

        // Cria um DefaultTableModel para a tabela
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Email");
        model.addColumn("Senha");
        model.addColumn("Data de Cadastro");
        model.addColumn("Admin");
        model.addColumn("Autorizado");

        // Adiciona cada usuário ao modelo da tabela
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDataCadastro(),
                usuario.isAdmin() ? "Sim" : "Não",
                usuario.isAutorizado() ? "Sim" : "Não"
            });
        }

        // Define o modelo da tabela na view
        view.getTblUsuarios().setModel(model);
    }
    
}
