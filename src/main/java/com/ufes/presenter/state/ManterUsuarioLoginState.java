package com.ufes.presenter.state;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.presenter.PrincipalPresenter;
import com.ufes.sistemalog.ILogAdapter;
import com.ufes.sistemalog.LogAdapterFactory;
import com.ufes.view.ManterUsuarioView;

public class ManterUsuarioLoginState implements ManterUsuarioState {
	private final PrincipalPresenter principalPresenter;

	public ManterUsuarioLoginState(PrincipalPresenter principalPresenter) {
		this.principalPresenter = principalPresenter;
	}

	@Override
	public void aplicarState(ManterUsuarioView view) {
		view.setTitulo("Login de Usuário");
		view.setNomeVisible(false);
		view.setEmailEditable(true);
		view.setSenhaEditable(true);
		view.setConfirmaSenhaVisible(false);
		view.setButtonText("Entrar");
		view.setCancelarButtonText("Cancelar");
	}

	@Override
	public void executarAcao(ManterUsuarioView view) {
		String email = view.getjTxtFEmail().getText();
		String senha = String.valueOf(view.getjPassFSenha().getPassword());

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.findByEmailESenha(email, senha);

		if (usuario != null) {
			if (!usuario.isAutorizado()) {
				JOptionPane.showMessageDialog(view, "Você não está autorizado ainda. Por favor, aguarde a autorização.",
						"Acesso Negado", JOptionPane.WARNING_MESSAGE);
				return;
			}

			JOptionPane.showMessageDialog(view, "Login realizado com sucesso!");
			view.setVisible(false);

			UsuarioLogado usuarioLogado = UsuarioLogado.getINSTANCE();
			usuarioLogado.setDadosUsuarioLogado(usuario);

			logOperacao(usuario);

			try {
				principalPresenter.atualizarEstadoUsuario(usuario);
				atualizarDesktopPane();
			} catch (IOException ex) {
				Logger.getLogger(ManterUsuarioLoginState.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			JOptionPane.showMessageDialog(view, "Email ou senha inválidos", "Erro de Login", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void logOperacao(Usuario usuario) {
		String nomeUsuarioLogado = UsuarioLogado.getINSTANCE().getNome();
		String operacao = "Edição de usuário";
		List<ILogAdapter> logAdapters = LogAdapterFactory.getLogAdapters();
		logAdapters.forEach(logAdapter -> logAdapter.logOperacao(operacao, usuario.getNome(), nomeUsuarioLogado));
	}

	private void atualizarDesktopPane() {
		JDesktopPane desktopPane = principalPresenter.getPrincipalView().getDesktopPane();
		desktopPane.removeAll();
		desktopPane.revalidate();
		desktopPane.repaint();
	}
}