package com.ufes.presenter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import com.ufes.presenter.state.ManterUsuarioInserirState;
import com.ufes.presenter.state.ManterUsuarioState;
import com.ufes.services.ValidadorEntryService;
import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;

/**
 *
 * @author talle
 */
public class ManterUsuarioPresenter {
	private ManterUsuarioView view;
	private ManterUsuarioState currentState;
	private JDesktopPane desktopPane;
	private BoasVindasView boasVindasView;

	public ManterUsuarioPresenter(ManterUsuarioView view, JDesktopPane desktopPane) {
		this.view = view;
		this.desktopPane = desktopPane;

		this.boasVindasView = BoasVindasView.getInstance();

		this.view.getjBtnCadastrar().addActionListener(e -> {
			try {
				executarAcao();
			} catch (IOException ex) {
				Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		this.view.getjBtnCancelar().addActionListener(e -> {
			try {
				cancelar();
			} catch (IOException ex) {
				Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

	}

	// Ao clicar em "Cadastrar" ou "Logar"
	private void executarAcao() throws IOException {
		System.out.println("Botão Cadastrar clicado");
		if (currentState == null) {
			throw new IllegalStateException("Estado não definido");
		}

		try {
			// Verifica se o estado atual é o de cadastro
			if (currentState instanceof ManterUsuarioInserirState) {
				ValidadorEntryService validadorEntryService = new ValidadorEntryService(view);
				validadorEntryService.validarCadastro(); // Validar entradas
			}

			// Delegar a lógica específica para o estado atual
			currentState.executarAcao(view);

		} catch (IllegalArgumentException e) {
			// Exibir a mensagem de erro
			JOptionPane.showMessageDialog(view, e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cancelar() throws IOException {
		System.out.println("Botão Cancelar clicado");

		// Esconde a tela de manter usuário
		view.setVisible(false);

		// Remove a tela de manter usuário do desktopPane
		desktopPane.remove(view);

		// Verifica se a tela de boas-vindas já está no desktopPane
		if (desktopPane.getComponentZOrder(boasVindasView) == -1) {
			desktopPane.add(boasVindasView); // Adiciona a tela de boas-vindas ao desktopPane
		}

		boasVindasView.setVisible(true); // Garante que a tela de boas-vindas está visível

		// Atualiza o layout do desktopPane para refletir as mudanças
		desktopPane.revalidate();
		desktopPane.repaint();
	}

	public void setState(ManterUsuarioState state) {
		this.currentState = state;
		this.currentState.aplicarState(view);
	}
}
