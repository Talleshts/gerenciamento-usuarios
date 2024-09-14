/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.ufes.view;

import com.ufes.model.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
        
        public void setUsuario(Usuario usuario) {
            if (usuario != null) {
                jTxtFNome.setText(usuario.getNome());
                jTxtFEmail.setText(usuario.getEmail());
                jPassFSenha.setText(usuario.getSenha());
                jPassFSenha2.setText(usuario.getSenha());
            }
        }


        public void setjPassFSenha(JPasswordField jPassFSenha) {
            this.jPassFSenha = jPassFSenha;
        }

        public void setjPassFSenha1(JPasswordField jPassFSenha1) {
            this.jPassFSenha1 = jPassFSenha1;
        }

        public void setjTxtFEmail(JTextField jTxtFEmail) {
            this.jTxtFEmail = jTxtFEmail;
        }

        public void setjTxtFNome(JTextField jTxtFNome) {
            this.jTxtFNome = jTxtFNome;
        }
        
        public JPasswordField getjPassFSenha() {
            return jPassFSenha;
        }
        
        public JPasswordField getjPassFSenha2() {
            return jPassFSenha2;
        }

        public JTextField getjTxtFEmail() {
            return jTxtFEmail;
        }

        public JTextField getjTxtFNome() {
            return jTxtFNome;
        }
        
        public void limparCampos() {
            jTxtFNome.setText("");
            jTxtFEmail.setText("");
            jPassFSenha.setText("");
            jPassFSenha2.setText("");
        }

        public void setSalvarButtonEnabled(boolean enabled) {
            jBtnCadastrar.setEnabled(enabled);
        }

        public void setNomeVisible(boolean visible) {
            nomeLbl.setVisible(visible);
            jTxtFNome.setVisible(visible);
        }

        public void setConfirmaSenhaVisible(boolean visible) {
            confirmarSenhaLbl.setVisible(visible);
            jPassFSenha2.setVisible(visible);
        }

        public void setTitulo(String titulo) {
            tituloLbl.setText(titulo);
        }

        public void setButtonText(String text) {
            jBtnCadastrar.setText(text);
        }

        public void setCancelarButtonText(String text) {
            jBtnCancelar.setText(text);
        }

        public void setNomeEditable(boolean editable) {
            jTxtFNome.setEditable(editable);
        }

        public void setEmailEditable(boolean editable) {
            jTxtFEmail.setEditable(editable);
        }

        public void setSenhaEditable(boolean editable) {
            jPassFSenha.setEditable(editable);
        }

        public JButton getjBtnCadastrar() {
            return jBtnCadastrar;
        }

        public JButton getjBtnCancelar() {
            return jBtnCancelar;
        }

        public void setConfirmaSenhaEditable(boolean editable) {
            jPassFSenha2.setEditable(editable);
        }
        

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPassFSenha1 = new javax.swing.JPasswordField();
        jBtnCadastrar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        nomeLbl = new javax.swing.JLabel();
        tituloLbl = new javax.swing.JLabel();
        jTxtFNome = new javax.swing.JTextField();
        emailLbl = new javax.swing.JLabel();
        jTxtFEmail = new javax.swing.JTextField();
        senhaLbl = new javax.swing.JLabel();
        jPassFSenha = new javax.swing.JPasswordField();
        confirmarSenhaLbl = new javax.swing.JLabel();
        jPassFSenha2 = new javax.swing.JPasswordField();

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("Senha");

        jPassFSenha1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnCadastrar.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        jBtnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnCadastrar.setText("Salvar");

        jBtnCancelar.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        jBtnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.setMaximumSize(new java.awt.Dimension(93, 22));
        jBtnCancelar.setMinimumSize(new java.awt.Dimension(93, 22));

        nomeLbl.setText("Nome");

        tituloLbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tituloLbl.setText("Cadastrar Usuário");
        tituloLbl.setToolTipText("");

        jTxtFNome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        emailLbl.setText("E-mail");

        jTxtFEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        senhaLbl.setText("Senha");

        jPassFSenha.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        confirmarSenhaLbl.setText("Confirme sua Senha");

        jPassFSenha2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPassFSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTxtFNome)
                                    .addComponent(nomeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtFEmail)
                                    .addComponent(senhaLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPassFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(confirmarSenhaLbl))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tituloLbl)
                        .addGap(91, 91, 91))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(tituloLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(nomeLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailLbl)
                .addGap(4, 4, 4)
                .addComponent(jTxtFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhaLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPassFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(confirmarSenhaLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPassFSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel confirmarSenhaLbl;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JButton jBtnCadastrar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPassFSenha;
    private javax.swing.JPasswordField jPassFSenha1;
    private javax.swing.JPasswordField jPassFSenha2;
    private javax.swing.JTextField jTxtFEmail;
    private javax.swing.JTextField jTxtFNome;
    private javax.swing.JLabel nomeLbl;
    private javax.swing.JLabel senhaLbl;
    private javax.swing.JLabel tituloLbl;
    // End of variables declaration//GEN-END:variables
}
