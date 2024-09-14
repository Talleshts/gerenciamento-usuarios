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

        // Inicia a tela de boas-vindas ou a tela principal
        atualizarEstadoUsuario();

        listarNotificacaoView = new ListarNotificacaoView();
        listarNotificacaoPresenter = new ListarNotificacaoPresenter(listarNotificacaoView);
        
        // Adiciona listener para o botão de notificações
        principalView.getNotificationButton().addActionListener(evt -> listarNotificacaoPresenter.getView().setVisible(true));
    }

    private void atualizarEstadoUsuario() throws IOException {
        if (usuario == null) {
            // Se não estiver logado, exibe apenas o JDesktopPane e oculta outros componentes
            principalView.getDesktopPane().setVisible(true);
            principalView.getNotificationButton().setVisible(false);
            principalView.getJMenuBar().setVisible(false);
            principalView.getNotifcacoesLbl().setVisible(false);
            principalView.getUsuarioNomeLbl().setVisible(false);
            principalView.getUsuarioNome().setVisible(false);
            
            // Mostra a tela de boas-vindas
            new BoasVindasPresenter(principalView.getDesktopPane());            

        } else {
            // Se estiver logado, exibe todos os componentes
            principalView.getDesktopPane().setVisible(true);
            principalView.getNotificationButton().setVisible(true);
            principalView.getJMenuBar().setVisible(true);
            principalView.getNotifcacoesLbl().setVisible(true);
            principalView.getUsuarioNomeLbl().setVisible(true);
            principalView.getUsuarioNome().setVisible(true);
            
            // Atualiza o nome do usuário na interface
            principalView.setUsuarioNome(new JLabel(usuario.getNome()));
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

    // Método para voltar para a tela de boas-vindas
    public void voltarParaBoasVindas() {
        try {
            usuario = null;
            atualizarEstadoUsuario(); // Redefine o estado para mostrar a tela de boas-vindas
        } catch (IOException ex) {
            Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
