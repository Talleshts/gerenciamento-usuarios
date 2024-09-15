package com.ufes.presenter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.presenter.state.ManterUsuarioInserirState;
import com.ufes.presenter.state.ManterUsuarioState;
import com.ufes.services.ValidadorEntryService;
import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;

public class ManterUsuarioPresenter {
	private ManterUsuarioView view;
	private ManterUsuarioState currentState;
	private JDesktopPane desktopPane;
	private BoasVindasView boasVindasView;
	private PrincipalPresenter principalPresenter;
	private Usuario usuario;

	public ManterUsuarioPresenter(ManterUsuarioView view, JDesktopPane desktopPane,
			PrincipalPresenter principalPresenter, Usuario usuario) {
		this.view = view;
		this.desktopPane = desktopPane;
		this.usuario = usuario;
		this.principalPresenter = principalPresenter;
		this.boasVindasView = BoasVindasView.getInstance();

		this.view.getjBtnCadastrar().addActionListener(e -> executarAcaoComTratamento());
		this.view.getjBtnCancelar().addActionListener(e -> cancelarComTratamento());
	}

	public void setState(ManterUsuarioState state) {
		this.currentState = state;
		this.currentState.aplicarState(view);
	}

	private void executarAcaoComTratamento() {
		try {
			executarAcao();
		} catch (IOException ex) {
			Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void cancelarComTratamento() {
		try {
			cancelar();
		} catch (IOException ex) {
			Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void executarAcao() throws IOException {
		if (currentState == null) {
			throw new IllegalStateException("Estado não definido");
		}

		try {
			if (currentState instanceof ManterUsuarioInserirState) {
				ValidadorEntryService validadorEntryService = new ValidadorEntryService(view);
				validadorEntryService.validarCadastro();
			}

			currentState.executarAcao(view);
			UsuarioLogado usuarioLogado = UsuarioLogado.getINSTANCE();
			principalPresenter.atualizarEstadoUsuario(usuarioLogado.getDadosUsuarioLogado());
			desktopPane.revalidate();
			desktopPane.repaint();

		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cancelar() throws IOException {
		view.setVisible(false);
		desktopPane.remove(view);

		UsuarioLogado usuarioLogado = UsuarioLogado.getINSTANCE();
		if (usuarioLogado.getDadosUsuarioLogado() == null) {
			if (desktopPane.getComponentCount() == 0 || desktopPane.getComponent(0) != boasVindasView) {
				desktopPane.add(boasVindasView);
			}
			boasVindasView.setVisible(true);
		} else if (principalPresenter != null) {
			principalPresenter.atualizarEstadoUsuario(usuarioLogado.getDadosUsuarioLogado());
		}

		desktopPane.revalidate();
		desktopPane.repaint();
	}
}