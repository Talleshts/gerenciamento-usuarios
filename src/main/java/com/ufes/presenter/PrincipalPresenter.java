package com.ufes.presenter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import com.ufes.DAO.NotificacaoDAO;
import com.ufes.model.Notificacao;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.observer.IObserverNotificacao;
import com.ufes.observer.IObserverUsuario;
import com.ufes.observer.ObservavelNotificacao;
import com.ufes.presenter.state.ManterUsuarioEditarState;
import com.ufes.presenter.state.ManterUsuarioInserirState;
import com.ufes.presenter.state.ManterUsuarioState;
import com.ufes.view.ListarNotificacaoView;
import com.ufes.view.ListarUsuarioView;
import com.ufes.view.ManterUsuarioView;
import com.ufes.view.PrincipalView;

public class PrincipalPresenter implements IObserverUsuario, IObserverNotificacao {

	private PrincipalView principalView;
	private ListarNotificacaoView listarNotificacaoView;
	private ListarNotificacaoPresenter listarNotificacaoPresenter;
	private ObservavelNotificacao observavelNotificacao;
	private Usuario usuario;

	public PrincipalView getPrincipalView() {
		return principalView;
	}

	public PrincipalPresenter() throws IOException {
		principalView = new PrincipalView();
		principalView.setVisible(true);
		observavelNotificacao = new ObservavelNotificacao();
		observavelNotificacao.adicionarObserver(this);

		atualizarEstadoUsuario(usuario);

		listarNotificacaoView = new ListarNotificacaoView();
		listarNotificacaoPresenter = new ListarNotificacaoPresenter(listarNotificacaoView);
		setNotificationNumber();

		principalView.getNotificationButton().addActionListener(evt -> abrirTelaNotificacoes());

		principalView.getjMenuItemDeslogar().addActionListener(evt -> voltarParaBoasVindas());
		principalView.getjMenuItemEditarUsuario().addActionListener(evt -> abrirTelaEditarUsuario());
		principalView.getjMenuItemInserirUsuario().addActionListener(evt -> abrirTelaInserirUsuario());
		principalView.getjMenuItemListarUsuario().addActionListener(evt -> {
			try {
				abrirTelaListarUsuario();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	public void atualizarEstadoUsuario(Usuario usuario) throws IOException {
		System.out
				.println("Atualizando estado do usuário: " + (usuario != null ? usuario.getNome() : "Nenhum usuário"));

		if (usuario == null) {
			exibirTelaBoasVindas();
		} else {
			exibirTelaPrincipal(usuario);
		}
	}

	private void exibirTelaBoasVindas() throws IOException {
		System.out.println("Usuário não logado. Exibindo tela de boas-vindas.");
		principalView.getDesktopPane().removeAll();
		principalView.getDesktopPane().setVisible(true);
		principalView.getNotificationButton().setVisible(false);
		principalView.getJMenuBar().setVisible(false);
		principalView.getNotifcacoesLbl().setVisible(false);
		principalView.getUsuarioNomeLbl().setVisible(false);
		principalView.getUsuarioNome().setVisible(false);
		principalView.getjMenuUsuarios().setVisible(false);

		BoasVindasPresenter boasVindasPresenter = new BoasVindasPresenter(principalView.getDesktopPane(), this);
		boasVindasPresenter.mostrarTela();
	}

	private void exibirTelaPrincipal(Usuario usuario) throws IOException {
		System.out.println("Usuário logado: " + usuario.getNome());
		principalView.getDesktopPane().setVisible(true);

		if (usuario.isAutorizado()) {
			principalView.getNotificationButton().setVisible(true);
			principalView.getJMenuBar().setVisible(true);
			principalView.getNotifcacoesLbl().setVisible(true);
			principalView.getUsuarioNomeLbl().setVisible(true);
			principalView.getUsuarioNome().setVisible(true);
			principalView.getjMenuUsuarios().setVisible(false);
		} else {
			JOptionPane.showMessageDialog(principalView,
					"Você não está autorizado ainda. Por favor, aguarde a autorização.", "Acesso Negado",
					JOptionPane.WARNING_MESSAGE);
			voltarParaBoasVindas();
			return;
		}

		if (usuario.isAdmin()) {
			principalView.getjMenuUsuarios().setVisible(true);
			principalView.setUsuarioTipo("Administrador");
		} else {
			principalView.setUsuarioTipo("Usuário");
		}

		principalView.setUsuarioNome(usuario.getNome());

	}

	@Override
	public void update(Usuario usuario) {
		this.usuario = usuario;
		try {
			atualizarEstadoUsuario(usuario);
		} catch (IOException ex) {
			Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setNotificationNumber() {
		try {
			NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
			int number = notificacaoDAO.countNotificacoesPendenteByUsuario(UsuarioLogado.getINSTANCE().getId());
			principalView.setNotifcacoesLbl(String.valueOf(number));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Notificacao notificacao) {
		try {
			setNotificationNumber();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void voltarParaBoasVindas() {
		try {
			usuario = null;
			UsuarioLogado.getINSTANCE().reset();
			atualizarEstadoUsuario(usuario);
		} catch (IOException ex) {
			Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void abrirTelaEditarUsuario() {
		abrirTelaUsuario(new ManterUsuarioEditarState(new ManterUsuarioView(), usuario));
	}

	private void abrirTelaInserirUsuario() {
		abrirTelaUsuario(new ManterUsuarioInserirState(new ManterUsuarioView(), this));
	}

	private void abrirTelaUsuario(ManterUsuarioState state) {
		JDesktopPane desktopPane = principalView.getDesktopPane();
		desktopPane.removeAll();

		ManterUsuarioView view = new ManterUsuarioView();
		desktopPane.add(view);
		view.setVisible(true);

		ManterUsuarioPresenter presenter = new ManterUsuarioPresenter(view, desktopPane, this, usuario);
		presenter.setState(state);

		desktopPane.revalidate();
		desktopPane.repaint();
	}

	private void abrirTelaListarUsuario() throws SQLException {
		JDesktopPane desktopPane = principalView.getDesktopPane();

		for (java.awt.Component comp : desktopPane.getComponents()) {
			if (comp instanceof ListarUsuarioView) {
				((ListarUsuarioView) comp).setVisible(true);
				try {
					((ListarUsuarioView) comp).setSelected(true);
				} catch (java.beans.PropertyVetoException e) {
					e.printStackTrace();
				}
				return;
			}
		}

		ListarUsuarioView view = new ListarUsuarioView();
		desktopPane.add(view);
		view.setVisible(true);

		new ListarUsuarioPresenter(view, principalView);

		desktopPane.revalidate();
		desktopPane.repaint();
	}

	private void abrirTelaNotificacoes() {
		JDesktopPane desktopPane = principalView.getDesktopPane();
		desktopPane.removeAll();

		listarNotificacaoView = new ListarNotificacaoView();
		listarNotificacaoPresenter = new ListarNotificacaoPresenter(listarNotificacaoView);

		desktopPane.add(listarNotificacaoView);
		listarNotificacaoView.setVisible(true);

		desktopPane.revalidate();
		desktopPane.repaint();
	}
}