/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.ufes.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author talle
 */
public class ManterUsuarioView extends javax.swing.JInternalFrame {

	/**
	 * Creates new form ManterUsuarioView
	 */
	public ManterUsuarioView() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel6 = new javax.swing.JLabel();
		jPassFSenha1 = new javax.swing.JPasswordField();
		jBtnCadastrar = new javax.swing.JButton();
		jBtnCancelar = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jTxtFNome = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTxtFEmail = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jPassFSenha = new javax.swing.JPasswordField();
		jLabel7 = new javax.swing.JLabel();
		jPassFSenha2 = new javax.swing.JPasswordField();

		jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
		jLabel6.setText("Senha");

		jPassFSenha1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jBtnCadastrar.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
		jBtnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jBtnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
		jBtnCadastrar.setText("Salvar");

		jBtnCancelar.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
		jBtnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jBtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
		jBtnCancelar.setText("Cancelar");
		jBtnCancelar.setMaximumSize(new java.awt.Dimension(93, 22));
		jBtnCancelar.setMinimumSize(new java.awt.Dimension(93, 22));

		jLabel1.setText("Nome");

		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
		jLabel5.setText("Cadastrar Usuário");
		jLabel5.setToolTipText("");

		jTxtFNome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel3.setText("E-mail");

		jTxtFEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel4.setText("Senha");

		jPassFSenha.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel7.setText("Confirme sua Senha");

		jPassFSenha2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap(72, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
												.createSequentialGroup()
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jPassFSenha2,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 270,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(jTxtFNome)
																				.addComponent(jLabel1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						125,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(jLabel3,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						129,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(jTxtFEmail)
																				.addComponent(jLabel4,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						87,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(jPassFSenha,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						270,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addComponent(jLabel7))
														.addGroup(layout.createSequentialGroup()
																.addComponent(jBtnCancelar,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 93,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(jBtnCadastrar,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 90,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(42, 42, 42)))
												.addGap(52, 52, 52))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup()
														.addComponent(jLabel5)
														.addGap(91, 91, 91)))));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addComponent(jLabel5)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26,
										Short.MAX_VALUE)
								.addComponent(jLabel1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jTxtFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel3)
								.addGap(4, 4, 4)
								.addComponent(jTxtFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel4)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPassFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(12, 12, 12)
								.addComponent(jLabel7)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPassFSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(28, 28, 28)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jBtnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(60, 60, 60)));
		jBtnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// boasVindasView.dispose();
				// Code to be executed when the button is clicked
				BoasVindasView.getInstance().setVisible(true);
				setVisible(false);
			}
		});
		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jBtnCadastrar;
	private javax.swing.JButton jBtnCancelar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPasswordField jPassFSenha;
	private javax.swing.JPasswordField jPassFSenha1;
	private javax.swing.JPasswordField jPassFSenha2;
	private javax.swing.JTextField jTxtFEmail;
	private javax.swing.JTextField jTxtFNome;
	// End of variables declaration//GEN-END:variables
}
