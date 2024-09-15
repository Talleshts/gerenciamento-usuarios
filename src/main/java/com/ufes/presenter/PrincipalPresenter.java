/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;

import com.ufes.DAO.NotificacaoDAO;
import com.ufes.model.Notificacao;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.observer.IObserverNotificacao;
import com.ufes.observer.IObserverUsuario;
import com.ufes.observer.ObservavelNotificacao;
import com.ufes.presenter.state.ManterUsuarioEditarState;
import com.ufes.presenter.state.ManterUsuarioInserirState;
import com.ufes.view.ListarNotificacaoView;
import com.ufes.view.ListarUsuarioView;
import com.ufes.view.ManterUsuarioView;
import com.ufes.view.PrincipalView;

/**
 *
 * @author tallesh
 */
public class PrincipalPresenter implements IObserverUsuario, IObserverNotificacao {

	private PrincipalView principalView;
	private ListarNotificacaoView listarNotificacaoView;
	private ListarNotificacaoPresenter listarNotificacaoPresenter;
	private ObservavelNotificacao observavelNotificacao;
	private Usuario usuario;

	public PrincipalPresenter() throws IOException {
		principalView = new PrincipalView();
		principalView.setVisible(true);
		observavelNotificacao = new ObservavelNotificacao();
		observavelNotificacao.adicionarObserver(this);

		// Inicia a tela de boas-vindas ou a tela principal
		atualizarEstadoUsuario(usuario);

		listarNotificacaoView = new ListarNotificacaoView();
		listarNotificacaoPresenter = new ListarNotificacaoPresenter(listarNotificacaoView);

		// Adiciona listener para o botão de notificações
		principalView.getNotificationButton().addActionListener(evt -> {
                    JDesktopPane desktopPane = principalView.getDesktopPane();
                    desktopPane.removeAll(); // Remove todos os componentes atuais

                    listarNotificacaoView = new ListarNotificacaoView();
                    listarNotificacaoPresenter = new ListarNotificacaoPresenter(listarNotificacaoView);

                    // Adiciona a view de notificações no JDesktopPane
                    desktopPane.add(listarNotificacaoView);

                    listarNotificacaoView.setVisible(true);

                    // Revalida e repinta o desktopPane para garantir que ele seja atualizado
                    desktopPane.revalidate();
                    desktopPane.repaint();
                });

		// Adiciona ActionListeners para os itens de menu
		principalView.getjMenuItemDeslogar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltarParaBoasVindas();
			}
		});

		principalView.getjMenuItemEditarUsuario().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirTelaEditarUsuario();
			}
		});

		principalView.getjMenuItemInserirUsuario().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirTelaInserirUsuario();
			}
		});

		principalView.getjMenuItemListarUsuario().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirTelaListarUsuario();
			}
		});
	}

	public void atualizarEstadoUsuario(Usuario usuario) throws IOException {
		if (usuario == null) {
			// Se não estiver logado, exibe apenas o JDesktopPane e oculta outros
			// componentes
			principalView.getDesktopPane().setVisible(true);
			principalView.getNotificationButton().setVisible(false);
			principalView.getJMenuBar().setVisible(false);
			principalView.getNotifcacoesLbl().setVisible(false);
			principalView.getUsuarioNomeLbl().setVisible(false);
			principalView.getUsuarioNome().setVisible(false);

			// Mostra a tela de boas-vindas
			BoasVindasPresenter boasVindasPresenter = new BoasVindasPresenter(principalView.getDesktopPane(), this);
		} else {
			// Se estiver logado, exibe todos os componentes
			principalView.getDesktopPane().setVisible(true);
			principalView.getNotificationButton().setVisible(true);
			principalView.getJMenuBar().setVisible(true);
			principalView.getNotifcacoesLbl().setVisible(true);
			principalView.getUsuarioNomeLbl().setVisible(true);
			principalView.getUsuarioNome().setVisible(true);

			// Atualiza o nome do usuário na interface
			principalView.setUsuarioNome(new JLabel(UsuarioLogado.getINSTANCE().getNome()));
		}
	}

	@Override
	public void update(Usuario usuario) {
		this.usuario = usuario;
		try {
			atualizarEstadoUsuario(usuario); // Atualiza a view ao receber novo estado do usuário
		} catch (IOException ex) {
			Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void update(Notificacao notificacao) {
        try {
            NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
			int number = notificacaoDAO.countNotificacoesPendenteByUsuario(usuario.getId());
			String labelText = principalView.getNotifcacoesLbl().getText();

			principalView.setNotifcacoesLbl(new JLabel(String.valueOf(number)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	// Método para voltar para a tela de boas-vindas
	public void voltarParaBoasVindas() {
		try {
			usuario = null;
			atualizarEstadoUsuario(usuario); // Redefine o estado para mostrar a tela de boas-vindas
		} catch (IOException ex) {
			Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void abrirTelaEditarUsuario() {
		// Implemente a lógica para abrir a tela de editar usuário
		JDesktopPane desktopPane = principalView.getDesktopPane();
		desktopPane.removeAll();

		ManterUsuarioView view = new ManterUsuarioView();
		desktopPane.add(view);

		view.setVisible(true);

		ManterUsuarioPresenter presenter = new ManterUsuarioPresenter(view, desktopPane, this, usuario);
		presenter.setState(new ManterUsuarioEditarState(view, usuario));

		// Garantir que o desktopPane seja atualizado
		desktopPane.revalidate();
		desktopPane.repaint();
	}

	private void abrirTelaInserirUsuario() {
		// Implemente a lógica para abrir a tela de inserir usuário
		JDesktopPane desktopPane = principalView.getDesktopPane();
		desktopPane.removeAll();

		ManterUsuarioView view = new ManterUsuarioView();
		desktopPane.add(view);

		view.setVisible(true);

		ManterUsuarioPresenter presenter = new ManterUsuarioPresenter(view, desktopPane, this, usuario);
		presenter.setState(new ManterUsuarioInserirState(view, this));

		// Garantir que o desktopPane seja atualizado
		desktopPane.revalidate();
		desktopPane.repaint();
	}

	private void abrirTelaListarUsuario() {
		// Implemente a lógica para abrir a tela de listar usuários
		JDesktopPane desktopPane = principalView.getDesktopPane();
		desktopPane.removeAll();

		ListarUsuarioView view = new ListarUsuarioView();
		desktopPane.add(view);

		view.setVisible(true);

		ListarUsuarioPresenter presenter = new ListarUsuarioPresenter(view);

		// Garantir que o desktopPane seja atualizado
		desktopPane.revalidate();
		desktopPane.repaint();
	}
}
