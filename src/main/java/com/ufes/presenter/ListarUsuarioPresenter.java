/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.view.ListarUsuarioView;
import com.ufes.view.ManterNotificacaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
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
        addActionListeners();
    }
    
    private void addActionListeners() {
        view.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirUsuario();
            }
        });

        view.getBtnAutorizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autorizarUsuario();
            }
        });

        view.getBtnEnviarNotificacao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaNotificacao();
            }
        });
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
    
    private void excluirUsuario() {
        int selectedRow = view.getTblUsuarios().getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) view.getTblUsuarios().getValueAt(selectedRow, 0);
            Usuario usuario = usuarioDAO.findByID(id);
            if (usuario != null) {
                usuarioDAO.remove(usuario);
                carregarUsuarios();
                JOptionPane.showMessageDialog(view, "Usuário excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(view, "Usuário não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione um usuário para excluir.");
        }
    }

    private void autorizarUsuario() {
        int selectedRow = view.getTblUsuarios().getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) view.getTblUsuarios().getValueAt(selectedRow, 0);
            Usuario usuario = usuarioDAO.findByID(id);
            if (usuario != null) {
                usuario.setAutorizado(true);
                usuarioDAO.update(usuario);
                carregarUsuarios();
                JOptionPane.showMessageDialog(view, "Usuário autorizado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(view, "Usuário não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione um usuário para autorizar.");
        }
    }

    private void abrirTelaNotificacao() {
        // Supondo que você tem um método para abrir a tela de notificações.
        ManterNotificacaoView notificacaoView = new ManterNotificacaoView();
        notificacaoView.setVisible(true);
    }
    
}
