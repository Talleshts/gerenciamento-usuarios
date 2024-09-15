package com.ufes.presenter;

import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.sistemalog.ILogAdapter;
import com.ufes.sistemalog.LogAdapterFactory;
import com.ufes.view.ListarUsuarioView;
import com.ufes.view.ManterNotificacaoView;
import com.ufes.view.PrincipalView;

public class ListarUsuarioPresenter {

	private ListarUsuarioView view;
	private UsuarioDAO usuarioDAO;
	private PrincipalView principalView;

	public ListarUsuarioPresenter(ListarUsuarioView view, PrincipalView principalView) {
		this.view = view;
		this.principalView = principalView;
		this.usuarioDAO = new UsuarioDAO();
		carregarUsuarios();
		addActionListeners();
	}

	private void addActionListeners() {
		view.getBtnExcluir().addActionListener(e -> excluirUsuario());
		view.getBtnAutorizar().addActionListener(e -> autorizarUsuario());
		view.getBtnEnviarNotificacao().addActionListener(e -> abrirManterNotificacaoView());
	}

	private void carregarUsuarios() {
		List<Usuario> usuarios = usuarioDAO.findAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nome");
		model.addColumn("Email");
		model.addColumn("Senha");
		model.addColumn("Data de Cadastro");
		model.addColumn("Admin");
		model.addColumn("Autorizado");

		for (Usuario usuario : usuarios) {
			model.addRow(new Object[] {
					usuario.getId(),
					usuario.getNome(),
					usuario.getEmail(),
					usuario.getSenha(),
					usuario.getDataCadastro(),
					usuario.isAdmin() ? "Sim" : "Não",
					usuario.isAutorizado() ? "Sim" : "Não"
			});
		}

		view.getTblUsuarios().setModel(model);
	}

	private void excluirUsuario() {
		int selectedRow = view.getTblUsuarios().getSelectedRow();
		if (selectedRow != -1) {
			int id = (int) view.getTblUsuarios().getValueAt(selectedRow, 0);
			Usuario usuario = usuarioDAO.findByID(id);
			if (usuario != null) {
				usuarioDAO.remove(usuario);
				logOperacao(usuario, "Exclusão de usuário");
				carregarUsuarios();
				JOptionPane.showMessageDialog(view, "Usuário excluído com sucesso.");
			} else {
				JOptionPane.showMessageDialog(view, "Usuário não encontrado.");
			}
		} else {
			JOptionPane.showMessageDialog(view, "Selecione um usuário para excluir.");
		}
	}

	private void autorizarUsuario() {
		int selectedRow = view.getTblUsuarios().getSelectedRow();
		if (selectedRow != -1) {
			int id = (int) view.getTblUsuarios().getValueAt(selectedRow, 0);
			Usuario usuario = usuarioDAO.findByID(id);
			if (usuario != null) {
				usuario.setAutorizado(true);
				usuarioDAO.update(usuario);
				carregarUsuarios();
				JOptionPane.showMessageDialog(view, "Usuário autorizado com sucesso.");
			} else {
				JOptionPane.showMessageDialog(view, "Usuário não encontrado.");
			}
		} else {
			JOptionPane.showMessageDialog(view, "Selecione um usuário para autorizar.");
		}
	}

	private void logOperacao(Usuario usuario, String operacao) {
		String nomeUsuarioLogado = UsuarioLogado.getINSTANCE().getNome();
		List<ILogAdapter> logAdapters = LogAdapterFactory.getLogAdapters();
		logAdapters.forEach(logAdapter -> logAdapter.logOperacao(operacao, usuario.getNome(), nomeUsuarioLogado));
	}

	public void abrirManterNotificacaoView() {
		if (principalView != null) {
			JDesktopPane desktopPane = principalView.getDesktopPane();

			for (java.awt.Component comp : desktopPane.getComponents()) {
				if (comp instanceof ManterNotificacaoView) {
					((ManterNotificacaoView) comp).setVisible(true);
					try {
						((ManterNotificacaoView) comp).setSelected(true);
					} catch (java.beans.PropertyVetoException e) {
						e.printStackTrace();
					}
					return;
				}
			}

			ManterNotificacaoView manterNotificacaoView = new ManterNotificacaoView();
			desktopPane.add(manterNotificacaoView);
			manterNotificacaoView.setVisible(true);

			try {
				manterNotificacaoView.setSelected(true);
			} catch (java.beans.PropertyVetoException e) {
				e.printStackTrace();
			}

			desktopPane.revalidate();
			desktopPane.repaint();
		} else {
			System.err.println("PrincipalView is null");
		}
	}
}