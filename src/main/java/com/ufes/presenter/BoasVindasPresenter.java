package com.ufes.presenter;

import java.io.IOException;

import javax.swing.JDesktopPane;

import com.ufes.model.Usuario;
import com.ufes.presenter.state.ManterUsuarioInserirState;
import com.ufes.presenter.state.ManterUsuarioLoginState;
import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;

/**
 *
 * @autor talle
 */
public class BoasVindasPresenter {
	private static BoasVindasView boasVindasView;
	private ManterUsuarioView manterUsuarioView;
	private JDesktopPane desktopPane;
	private PrincipalPresenter principalPresenter;
	private Usuario usuario;

	public BoasVindasPresenter(JDesktopPane desktopPane, PrincipalPresenter principalPresenter) throws IOException {
		this.desktopPane = desktopPane;
		this.principalPresenter = principalPresenter;

		if (boasVindasView == null) {
			boasVindasView = BoasVindasView.getInstance();
		}

		boasVindasView.getLoginBtn().addActionListener(e -> configureUserView(false));
		boasVindasView.getSigninBtn().addActionListener(e -> configureUserView(true));
	}

	public void mostrarTela() {
		desktopPane.removeAll(); // Remove todas as janelas internas
		desktopPane.add(boasVindasView);
		boasVindasView.setVisible(true);
		desktopPane.revalidate();
		desktopPane.repaint();
	}

	private void configureUserView(boolean isSignUp) {
		if (manterUsuarioView != null) {
			desktopPane.remove(manterUsuarioView); // Remove a tela de manter usuário se ela já estiver no desktopPane
		}

		manterUsuarioView = new ManterUsuarioView();
		desktopPane.add(manterUsuarioView);
		manterUsuarioView.setVisible(true);
		boasVindasView.setVisible(false); // Oculta a tela de boas-vindas

		ManterUsuarioPresenter manterUsuarioPresenter = new ManterUsuarioPresenter(manterUsuarioView, desktopPane,
				principalPresenter, usuario);

		if (isSignUp) {
			manterUsuarioPresenter.setState(new ManterUsuarioInserirState(manterUsuarioView, principalPresenter));
		} else {
			// Passando o PrincipalPresenter necessário para o estado de login
			manterUsuarioPresenter.setState(new ManterUsuarioLoginState(principalPresenter));
		}

		desktopPane.revalidate();
		desktopPane.repaint();
	}
}