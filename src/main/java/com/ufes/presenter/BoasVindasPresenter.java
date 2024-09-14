/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JDesktopPane;

import com.ufes.model.Usuario;
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
	private boolean showSignUpFields;

	public BoasVindasPresenter(JDesktopPane desktopPane) throws IOException {
		boasVindasView = BoasVindasView.getInstance();
		manterUsuarioView = new ManterUsuarioView();
		desktopPane.add(boasVindasView);
		desktopPane.add(manterUsuarioView);

		boasVindasView.setVisible(true);
		boasVindasView.getLoginBtn().addActionListener(e -> configureUserView(false));
		boasVindasView.getSigninBtn().addActionListener(e -> configureUserView(true));
		
		manterUsuarioView.getPrimaryButton().addActionListener(e -> {
			try {
				logar(showSignUpFields);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	private void configureUserView(boolean showSignUpFields) {
		this.showSignUpFields = showSignUpFields;
		String buttonText = showSignUpFields ? "Salvar" : "Login";
		String mainLabelText = showSignUpFields ? "Cadastrar Usuário" : "Logar Usuário";

		boasVindasView.setVisible(false);
		manterUsuarioView.getMainLabel().setText(mainLabelText);
		manterUsuarioView.getPrimaryButton().setText(buttonText);

		manterUsuarioView.getNameLabel().setVisible(showSignUpFields);
		manterUsuarioView.getNameField().setVisible(showSignUpFields);
		manterUsuarioView.getConfirmPasswordLabel().setVisible(showSignUpFields);
		manterUsuarioView.getConfirmPasswordField().setVisible(showSignUpFields);

		manterUsuarioView.setVisible(true);
	}

	private void logar(boolean shouldSaveUser) throws SQLException {
		String nome = manterUsuarioView.getNameField().getText();
		String senha = manterUsuarioView.getConfirmPasswordField().getText();

		Usuario usuario = new Usuario(nome, senha, false, true);

		if (shouldSaveUser) {
			saveUser(usuario);
		}
		// Lógica login
	}

	// Não sei se esse SQL tá certo, testem se puderem
	private void saveUser(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO USUARIOS (NOME, SENHA, IS_ADMIN, IS_AUTORIZADO, DATA_CADASTRO) VALUES (?, ?, ?, ?, ?)";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try (PreparedStatement pstmt = ConnectionDBService.getConnection().prepareStatement(sql)) {
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getSenha());
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 1);
			pstmt.setString(5, dateFormat.format(usuario.getDataCadastro()));

			pstmt.executeUpdate();
		}
	}
}
