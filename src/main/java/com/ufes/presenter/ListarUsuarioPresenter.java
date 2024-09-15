/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.observer.ObservavelNotificacao;
import com.ufes.view.ListarUsuarioView;
import com.ufes.view.ManterNotificacaoView;
import com.ufes.view.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author talle
 */
public class ListarUsuarioPresenter {

    private ListarUsuarioView view;
    private UsuarioDAO usuarioDAO;
    private PrincipalView principalView;
    private ObservavelNotificacao observavelNotificacao;
    private ManterNotificacaoView manterNotificacaoView;

    public ListarUsuarioPresenter(ListarUsuarioView view, PrincipalView principalView) {
        this.view = view;
        this.principalView = principalView;
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
                abrirManterNotificacaoView();
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

    public void abrirManterNotificacaoView() {
        if (principalView != null) {
            System.out.println("ENTROU");
            JDesktopPane desktopPane = principalView.getDesktopPane();

            ManterNotificacaoView manterNotificacaoView = new ManterNotificacaoView();
            desktopPane.add(manterNotificacaoView);

            manterNotificacaoView.setVisible(true);

            desktopPane.revalidate();
            desktopPane.repaint();
        } else {
            System.err.println("PrincipalView is null");
        }
    }
    
}
