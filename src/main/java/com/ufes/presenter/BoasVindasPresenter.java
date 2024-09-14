/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.DAO.UsuarioDAO;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JDesktopPane;

import com.ufes.model.Usuario;
import com.ufes.presenter.state.ManterUsuarioInserirState;
import com.ufes.presenter.state.ManterUsuarioLoginState;
import com.ufes.services.ConnectionDBService;
import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;

/**
 *
 * @author talle
 */
public class BoasVindasPresenter {
    private BoasVindasView boasVindasView;
    private ManterUsuarioView manterUsuarioView;
    private JDesktopPane desktopPane;

    public BoasVindasPresenter(JDesktopPane desktopPane) throws IOException {
        this.desktopPane = desktopPane;
        boasVindasView = BoasVindasView.getInstance();
        manterUsuarioView = new ManterUsuarioView();

        desktopPane.add(boasVindasView);
        desktopPane.add(manterUsuarioView);

        boasVindasView.setVisible(true);
        boasVindasView.getLoginBtn().addActionListener(e -> configureUserView(false));
        boasVindasView.getSigninBtn().addActionListener(e -> configureUserView(true));
    }

    private void configureUserView(boolean isSignUp) {
        manterUsuarioView.setVisible(true);

        if (isSignUp) {
            // Aplica o estado de inserção de usuário (cadastro)
            new ManterUsuarioInserirState(manterUsuarioView).aplicarState(manterUsuarioView);
        } else {
            // Aplica o estado de login de usuário
            new ManterUsuarioLoginState().aplicarState(manterUsuarioView);
        }

        // Mostra a tela de ManterUsuarioView
        manterUsuarioView.setVisible(true);
        boasVindasView.setVisible(false); // Oculta a tela de boas-vindas
    }
}