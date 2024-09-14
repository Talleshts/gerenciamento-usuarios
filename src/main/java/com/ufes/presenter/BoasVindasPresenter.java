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
    private static BoasVindasView boasVindasView;
    private ManterUsuarioView manterUsuarioView;
    private JDesktopPane desktopPane;
    private PrincipalPresenter principalPresenter;

    public BoasVindasPresenter(JDesktopPane desktopPane, PrincipalPresenter principalPresenter) throws IOException {
        this.desktopPane = desktopPane;
        this.principalPresenter = principalPresenter;

        if (boasVindasView == null) {
            boasVindasView = BoasVindasView.getInstance();
            desktopPane.add(boasVindasView);
        }

        boasVindasView.setVisible(true);

        boasVindasView.getLoginBtn().addActionListener(e -> configureUserView(false));
        boasVindasView.getSigninBtn().addActionListener(e -> configureUserView(true));
    }

    private void configureUserView(boolean isSignUp) {
        if (manterUsuarioView != null) {
            desktopPane.remove(manterUsuarioView); // Remove a tela de manter usuário se ela já estiver no desktopPane
        }

        manterUsuarioView = new ManterUsuarioView();
        desktopPane.add(manterUsuarioView);
        manterUsuarioView.setVisible(true);
        boasVindasView.setVisible(false); // Oculta a tela de boas-vindas

        ManterUsuarioPresenter manterUsuarioPresenter = new ManterUsuarioPresenter(manterUsuarioView, desktopPane, principalPresenter);

        if (isSignUp) {
            manterUsuarioPresenter.setState(new ManterUsuarioInserirState(manterUsuarioView, principalPresenter));
        } else {
            // Passando o PrincipalPresenter necessário para o estado de login
            manterUsuarioPresenter.setState(new ManterUsuarioLoginState(principalPresenter));
        }
    }

}