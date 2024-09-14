/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter.state;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.services.ValidadorEntryService;
import com.ufes.services.ValidadorSenhaService;
import com.ufes.view.ManterUsuarioView;
import javax.swing.JOptionPane;

/**
 *
 * @author talle
 */
public class ManterUsuarioInserirState implements ManterUsuarioState {
	
    private ValidadorEntryService validadorEntryService;
    private ValidadorSenhaService validadorSenhaService;
    private UsuarioDAO usuarioDAO;

    public ManterUsuarioInserirState(ManterUsuarioView view) {
        this.validadorEntryService = new ValidadorEntryService(view);
        this.validadorSenhaService = new ValidadorSenhaService();
        this.usuarioDAO = new UsuarioDAO();
        aplicarState(view);
    }

    
    
    
    @Override
	public void aplicarState(ManterUsuarioView view) {
            // Configura a tela para cadastro
            view.setButtonText("Cadastrar");
            view.setNomeEditable(true);
            view.setEmailEditable(true);
            view.setSenhaEditable(true);
            view.setConfirmaSenhaEditable(true);
	}
        
        
        public void cadastrarUsuario() {
        ManterUsuarioView view = new ManterUsuarioView();
        String nome = view.getjTxtFNome().getText();
        String email = view.getjTxtFEmail().getText();
        String senha = String.valueOf(view.getjPassFSenha1().getPassword());

        try {
            // Valida entradas básicas
            validadorEntryService.validarCadastro();
            // Valida senha
            validadorSenhaService.validar(senha);

            // Se todas as validações passarem, cria o usuário
            //Usuario usuario = new Usuario(nome, email, senha, isAdmin, isAutorizado);
            //usuarioDAO.insert(usuario);

            JOptionPane.showMessageDialog(view, "Cadastro realizado com sucesso!");
            view.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
        
        
}
