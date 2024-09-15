/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ufes.view;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.ufes.sistemalog.LogConfig;

/**
 *
 * @author tallesh
 */
public class PrincipalView extends javax.swing.JFrame {
	/**
	 * Creates new form PrincipalView
	 */
	public PrincipalView() {
		initComponents();

		jCheckBoxMenuItemJSON.addActionListener(e -> atualizarTipoLog("JSON"));
		jCheckBoxMenuItemCSV.addActionListener(e -> atualizarTipoLog("CSV"));
	}

	private void atualizarTipoLog(String tipo) {
		// Atualiza o tipo de log no LogConfig
		LogConfig.setTipoLogSelecionado(tipo);
	}

	public JCheckBoxMenuItem getjCheckBoxMenuItemCSV() {
		return jCheckBoxMenuItemCSV;
	}

	public JCheckBoxMenuItem getjCheckBoxMenuItemJSON() {
		return jCheckBoxMenuItemJSON;
	}

	public javax.swing.JDesktopPane getDesktopPane() {
		return jDesktopPane1;
	}

	public JButton getNotificationButton() {
		return btnNotificacao;
	}

	public JLabel getNotifcacoesLbl() {
		return NotifcacoesLbl;
	}

	public JLabel getUsuarioNome() {
		return UsuarioNome;
	}

	public JLabel getUsuarioNomeLbl() {
		return UsuarioNomeLbl;
	}

	public JMenuItem getjMenuItemDeslogar() {
		return jMenuItemDeslogar;
	}

	public JMenuItem getjMenuItemEditarUsuario() {
		return jMenuItemEditarUsuario;
	}

	public JMenuItem getjMenuItemInserirUsuario() {
		return jMenuItemInserirUsuario;
	}

	public JMenuItem getjMenuItemListarUsuario() {
		return jMenuItemListarUsuario;
	}

	public void setNotifcacoesLbl(String NotifcacoesLbl) {
		this.btnNotificacao.setText(NotifcacoesLbl);
	}

	public void setUsuarioNome(String UsuarioNome) {
		this.UsuarioNome.setText(UsuarioNome);
	}

	public JMenu getjMenuUsuarios() {
		return jMenuUsuarios;
	}

	public void setUsuarioNomeLbl(JLabel UsuarioNomeLbl) {
		this.UsuarioNomeLbl = UsuarioNomeLbl;
	}

	public void setBtnNotificacao(JButton btnNotificacao) {
		this.btnNotificacao = btnNotificacao;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jDesktopPane1 = new javax.swing.JDesktopPane();
		UsuarioNome = new javax.swing.JLabel();
		btnNotificacao = new javax.swing.JButton();
		NotifcacoesLbl = new javax.swing.JLabel();
		UsuarioNomeLbl = new javax.swing.JLabel();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenuUsuarios = new javax.swing.JMenu();
		jMenuItemInserirUsuario = new javax.swing.JMenuItem();
		jMenuItemListarUsuario = new javax.swing.JMenuItem();
		jMenuConfigurações = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();
		jCheckBoxMenuItemJSON = new javax.swing.JCheckBoxMenuItem();
		jCheckBoxMenuItemCSV = new javax.swing.JCheckBoxMenuItem();
		jMenuConta = new javax.swing.JMenu();
		jMenuItemDeslogar = new javax.swing.JMenuItem();
		jMenuItemEditarUsuario = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Gerenciamento Usuarios");

		jDesktopPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
		jDesktopPane1.setLayout(jDesktopPane1Layout);
		jDesktopPane1Layout.setHorizontalGroup(
				jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 0, Short.MAX_VALUE));
		jDesktopPane1Layout.setVerticalGroup(
				jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 694, Short.MAX_VALUE));

		btnNotificacao.setBackground(new java.awt.Color(204, 255, 255));
		btnNotificacao.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNotificacaoActionPerformed(evt);
			}
		});

		NotifcacoesLbl.setText("Notificações");

		UsuarioNomeLbl.setText("Usuário Logado");

		jMenuBar1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

		jMenuUsuarios.setText("Usuários");

		jMenuItemInserirUsuario.setText("Cadastrar Usuário");

		jMenuUsuarios.add(jMenuItemInserirUsuario);

		jMenuItemListarUsuario.setText("Buscar Usuário");
		jMenuUsuarios.add(jMenuItemListarUsuario);

		jMenuBar1.add(jMenuUsuarios);

		jMenuConfigurações.setText("Configurações");

		jMenu2.setText("Alterar Log");

		jCheckBoxMenuItemJSON.setText("JSON");
		jMenu2.add(jCheckBoxMenuItemJSON);

		jCheckBoxMenuItemCSV.setText("CSV");
		jMenu2.add(jCheckBoxMenuItemCSV);

		jMenuConfigurações.add(jMenu2);

		jMenuBar1.add(jMenuConfigurações);

		jMenuConta.setText("Conta");

		jMenuItemDeslogar.setText("Deslogar");
		jMenuConta.add(jMenuItemDeslogar);

		jMenuItemEditarUsuario.setText("Alterar Dados");
		jMenuItemEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemEditarUsuarioActionPerformed(evt);
			}
		});
		jMenuConta.add(jMenuItemEditarUsuario);

		jMenuBar1.add(jMenuConta);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(UsuarioNomeLbl)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(UsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 265,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 968,
										Short.MAX_VALUE)
								.addComponent(NotifcacoesLbl)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(20, 20, 20))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(jDesktopPane1)
								.addContainerGap()));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jDesktopPane1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(UsuarioNome, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(btnNotificacao,
																javax.swing.GroupLayout.PREFERRED_SIZE, 34,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createSequentialGroup()
																.addGap(10, 10, 10)
																.addComponent(UsuarioNomeLbl))
														.addGroup(layout.createSequentialGroup()
																.addGap(9, 9, 9)
																.addComponent(NotifcacoesLbl)))
												.addGap(0, 0, Short.MAX_VALUE)))
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jMenuItemEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemEditarUsuarioActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jMenuItemEditarUsuarioActionPerformed

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jMenuItem1ActionPerformed

	private void btnNotificacaoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNotificacaoActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnNotificacaoActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel.
		 * For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PrincipalView().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel NotifcacoesLbl;
	private javax.swing.JLabel UsuarioNome;
	private javax.swing.JLabel UsuarioNomeLbl;
	private javax.swing.JButton btnNotificacao;
	private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemCSV;
	private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemJSON;
	private javax.swing.JDesktopPane jDesktopPane1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenu jMenuConfigurações;
	private javax.swing.JMenu jMenuConta;
	private javax.swing.JMenuItem jMenuItemDeslogar;
	private javax.swing.JMenuItem jMenuItemEditarUsuario;
	private javax.swing.JMenuItem jMenuItemInserirUsuario;
	private javax.swing.JMenuItem jMenuItemListarUsuario;
	private javax.swing.JMenu jMenuUsuarios;
	// End of variables declaration//GEN-END:variables
}
