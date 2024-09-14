/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter.state;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.presenter.BoasVindasPresenter;
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

    private void cadastrarUsuario() throws IOException {
        String nome = manterUsuarioView.getjTxtFNome().getText();
        String senha = String.valueOf(manterUsuarioView.getjPassFSenha().getPassword());
        String confirmaSenha = String.valueOf(manterUsuarioView.getjPassFSenha2().getPassword());

        if (senha.equals(confirmaSenha)) {
            Usuario usuario = new Usuario(nome, senha, false, true);

            usuarioDAO.insert(usuario);
            JOptionPane.showMessageDialog(manterUsuarioView, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Após cadastro, redirecionar para a tela de boas-vindas
            manterUsuarioView.setVisible(false);  // Oculta a tela de cadastro
            new BoasVindasPresenter(manterUsuarioView.getDesktopPane());  // Mostra a tela de boas-vindas
        } else {
            JOptionPane.showMessageDialog(manterUsuarioView, "As senhas não conferem. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Adicionar um listener no botão de cadastro
    public void addCadastrarListener() {
        manterUsuarioView.getjBtnCadastrar().addActionListener(e -> {
            try {
                cadastrarUsuario();
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioInserirState.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Adicionar um listener no botão de cancelar
    public void addCancelarListener() {
        manterUsuarioView.getjBtnCancelar().addActionListener(e -> {
            manterUsuarioView.setVisible(false);
            try {
                new BoasVindasPresenter(manterUsuarioView.getDesktopPane());
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioInserirState.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @Override
    public void entrarNoSistemaState(){
        String nome = manterUsuarioView.getjTxtFNome().getText();
        String senha = String.valueOf(manterUsuarioView.getjPassFSenha().getPassword());

        Usuario usuario = new Usuario(nome, senha, false, true);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insert(usuario);

        JOptionPane.showMessageDialog(manterUsuarioView, "Cadastro realizado com sucesso!");

        manterUsuarioView.setVisible(false); // Esconde a tela de manter usuário
     try {
         // Volta para a tela de boas-vindas
         new BoasVindasPresenter(manterUsuarioView.getDesktopPane());
     } catch (IOException ex) {
         Logger.getLogger(ManterUsuarioInserirState.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
        
}
