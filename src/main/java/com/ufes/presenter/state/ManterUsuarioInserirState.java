/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter.state;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JDesktopPane;

/**
 *
 * @author talle
 */
public class ManterUsuarioInserirState implements ManterUsuarioState{
    private BoasVindasView boasVindasView;
    private UsuarioDAO usuarioDAO;
    private boolean showSignUpFields;
    private ManterUsuarioView manterUsuarioView;

    public ManterUsuarioInserirState(ManterUsuarioView manterUsuarioView) {
            this.manterUsuarioView = manterUsuarioView;
    }

    @Override
    public void aplicarState(ManterUsuarioView view) {
    }
    




    private void logar(boolean shouldSaveUser) throws SQLException {
            String nome = manterUsuarioView.getjTxtFNome().getText();
            String senha = String.valueOf(manterUsuarioView.getjPassFSenha().getPassword());

            Usuario usuario = new Usuario(nome, senha, false, true);

            if (shouldSaveUser) {
                    usuarioDAO.insert(usuario);
            }
            // Lógica login
    }
        
        
}
