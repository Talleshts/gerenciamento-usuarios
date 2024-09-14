/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import java.io.IOException;

import com.ufes.model.Usuario;
import com.ufes.observer.IObserver;
import com.ufes.view.ListarNotificacaoView;
import com.ufes.view.PrincipalView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author tallesh
 */
public class PrincipalPresenter implements IObserver {

    private PrincipalView principalView;
    private ListarNotificacaoView listarNotificacaoView;
    private ListarNotificacaoPresenter listarNotificacaoPresenter;
    private Usuario usuario = null;

    public PrincipalPresenter() throws IOException {
        principalView = new PrincipalView();
        principalView.setVisible(true);

        // Realiza a validação do usuário logado
        atualizarEstadoUsuario();

        listarNotificacaoView = new ListarNotificacaoView();
        listarNotificacaoPresenter = new ListarNotificacaoPresenter(listarNotificacaoView);
        
        principalView.getNotificationButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            listarNotificacaoPresenter.getView().setVisible(true);
        });
    }

    private void atualizarEstadoUsuario() throws IOException {
        if (usuario == null) {
            // Se não estiver logado, exibe apenas o JDesktopPane
            principalView.getDesktopPane().setVisible(true);
            principalView.getNotificationButton().setVisible(false);
            principalView.getJMenuBar().setVisible(false);
            principalView.getNotifcacoesLbl().setVisible(false);
            principalView.getUsuarioNomeLbl().setVisible(false);
            principalView.getUsuarioNome().setVisible(false);
            
            new BoasVindasPresenter(principalView.getDesktopPane());            

        } else {
            // Se estiver logado, exibe todos os componentes
            principalView.getDesktopPane().setVisible(true);
            principalView.getNotificationButton().setVisible(true);
            principalView.getJMenuBar().setVisible(true);
            // Atualiza o nome do usuário na view
            principalView.setUsuarioNome( new JLabel(usuario.getNome()));
        }
    }

    @Override
    public void update(Usuario usuario) {
        this.usuario = usuario;
        try {
            atualizarEstadoUsuario(); // Atualiza a view ao receber novo estado do usuário
        } catch (IOException ex) {
            Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
