package com.ufes.presenter.state;

import java.util.List;

import javax.swing.JOptionPane;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.presenter.PrincipalPresenter;
import com.ufes.services.ValidadorEntryService;
import com.ufes.sistemalog.ILogAdapter;
import com.ufes.sistemalog.LogAdapterFactory;
import com.ufes.view.ManterUsuarioView;

public class ManterUsuarioInserirState implements ManterUsuarioState {
	private UsuarioDAO usuarioDAO;
	private ManterUsuarioView manterUsuarioView;
	private PrincipalPresenter principalPresenter;

	public ManterUsuarioInserirState(ManterUsuarioView manterUsuarioView, PrincipalPresenter principalPresenter) {
		this.manterUsuarioView = manterUsuarioView;
		this.principalPresenter = principalPresenter;
		this.usuarioDAO = new UsuarioDAO();
	}

	@Override
	public void aplicarState(ManterUsuarioView view) {
		view.setTitulo("Cadastro de Usuário");
		view.setNomeVisible(true);
		view.setEmailEditable(true);
		view.setSenhaEditable(true);
		view.setConfirmaSenhaVisible(true);
		view.setButtonText("Cadastrar");
		view.setCancelarButtonText("Cancelar");
	}

	@Override
	public void executarAcao(ManterUsuarioView view) {
		String nome = view.getjTxtFNome().getText();
		String senha = String.valueOf(view.getjPassFSenha().getPassword());
		String email = String.valueOf(view.getjTxtFEmail().getText());

		Usuario usuarioLogado = UsuarioLogado.getINSTANCE().getDadosUsuarioLogado();

		ValidadorEntryService validadorEntryService = new ValidadorEntryService(view);
		validadorEntryService.validarCadastro();

		boolean isAutorizado = usuarioLogado != null && usuarioLogado.isAdmin();
		Usuario usuarioObj = new Usuario(nome, senha, email, false, isAutorizado);

		try {
			usuarioDAO.insert(usuarioObj);
			JOptionPane.showMessageDialog(view, "Cadastro realizado com sucesso!");
			view.setVisible(false);

			if (usuarioLogado != null) {
				logOperacao(usuarioLogado.getNome(), nome, "Cadastro de usuário");
			}

			if (principalPresenter != null) {
				if (usuarioLogado == null) {
					principalPresenter.voltarParaBoasVindas();
				} else {
					principalPresenter.atualizarEstadoUsuario(usuarioLogado);
				}
			} else {
				JOptionPane.showMessageDialog(view, "Erro: Presenter não inicializado.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Erro ao realizar cadastro: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void logOperacao(String nomeUsuarioLogado, String nome, String operacao) {
		List<ILogAdapter> logAdapters = LogAdapterFactory.getLogAdapters();
		logAdapters.forEach(logAdapter -> logAdapter.logOperacao(operacao, nome, nomeUsuarioLogado));
	}
}