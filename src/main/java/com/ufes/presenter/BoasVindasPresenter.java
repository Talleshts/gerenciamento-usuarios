/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import java.io.IOException;

import javax.swing.JDesktopPane;
import java.awt.event.*;

import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;

/**
 *
 * @author talle
 */
public class BoasVindasPresenter {

	private BoasVindasView boasVindasView;
	private ManterUsuarioView manterUsuarioView;

	public BoasVindasPresenter(JDesktopPane desktopPane) throws IOException {
		boasVindasView = BoasVindasView.getInstance();
		manterUsuarioView = new ManterUsuarioView();
		desktopPane.add(boasVindasView);
		desktopPane.add(manterUsuarioView);

		boasVindasView.setVisible(true);
		boasVindasView.getLoginBtn().addActionListener(e -> configureUserView(false));
        boasVindasView.getSigninBtn().addActionListener(e -> configureUserView(true));
	}

	private void configureUserView(boolean showSignUpFields) {
		String buttonText = showSignUpFields ? "Salvar" : "Login";
		String mainLabelText = showSignUpFields ? "Cadastrar Usuário" : "Logar Usuário";
        boasVindasView.setVisible(false);
        manterUsuarioView.getMainLabel().setText(mainLabelText);
        manterUsuarioView.getPrimaryButton().setText(buttonText);

        // Toggle visibility of sign-up specific fields
        manterUsuarioView.getNameLabel().setVisible(showSignUpFields);
        manterUsuarioView.getNameField().setVisible(showSignUpFields);
        manterUsuarioView.getConfirmPasswordLabel().setVisible(showSignUpFields);
        manterUsuarioView.getConfirmPasswordField().setVisible(showSignUpFields);

        manterUsuarioView.setVisible(true);
    }
}
