/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter.state;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.presenter.BoasVindasPresenter;
import com.ufes.services.ValidadorEntryService;
import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author talle
 */
public class ManterUsuarioInserirState implements ManterUsuarioState{
    private UsuarioDAO usuarioDAO;
    private ManterUsuarioView manterUsuarioView;

    public ManterUsuarioInserirState(ManterUsuarioView manterUsuarioView) {
        this.manterUsuarioView = manterUsuarioView;
        this.usuarioDAO = new UsuarioDAO(); // Inicializar o DAO aqui
    }

    @Override
    public void aplicarState(ManterUsuarioView view) {
        // Configura a view para inserção de usuário
        view.setTitulo("Cadastro de Usuário");
        view.setNomeVisible(true);
        view.setEmailEditable(true);
        view.setSenhaEditable(true);
        view.setConfirmaSenhaVisible(true);
        view.setButtonText("Cadastrar");
        view.setCancelarButtonText("Cancelar");
    }
    
    @Override
    public void executarAcao(ManterUsuarioView view){
        // Lógica de cadastro
        String nome = view.getjTxtFNome().getText();
        String senha = String.valueOf(view.getjPassFSenha().getPassword());
        String email = String.valueOf(view.getjTxtFEmail().getText());     //olha isso aqui pra ver se ta certo
        ValidadorEntryService validadorEntryService = new ValidadorEntryService(view);
        validadorEntryService.validarCadastro(); // Validar entradas
        Usuario usuario = new Usuario(nome, senha, email, false, true);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insert(usuario); // Salvando o usuário no banco
        
        JOptionPane.showMessageDialog(view, "Cadastro realizado com sucesso!");
        view.setVisible(false); // Esconde a tela de manter usuário
        try {
            new BoasVindasPresenter(view.getDesktopPane());
        } catch (IOException ex) {
            Logger.getLogger(ManterUsuarioInserirState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
