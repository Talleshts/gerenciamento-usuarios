package com.ufes.presenter.state;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.presenter.PrincipalPresenter;
import com.ufes.view.ManterUsuarioView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ManterUsuarioLoginState implements ManterUsuarioState{
    private final PrincipalPresenter principalPresenter;

    public ManterUsuarioLoginState(PrincipalPresenter principalPresenter) {
        this.principalPresenter = principalPresenter;
    }
    
    @Override
    public void aplicarState(ManterUsuarioView view) {
        // Alterar o título da tela
        view.setTitulo("Login de Usuário");

        // Configura os campos relevantes para login
        view.setNomeVisible(false);
        view.setEmailEditable(true);
        view.setSenhaEditable(true);

        // Ocultar campo de confirmação de senha
        view.setConfirmaSenhaVisible(false);

        // Configura os botões
        view.setButtonText("Entrar");
        view.setCancelarButtonText("Cancelar");
    }

    @Override
    public void executarAcao(ManterUsuarioView view) {
        String email = view.getjTxtFEmail().getText();
        String senha = String.valueOf(view.getjPassFSenha().getPassword());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.findByEmailESenha(email, senha);


        if (usuario != null) {
            JOptionPane.showMessageDialog(view, "Login realizado com sucesso!");
            view.setVisible(false); // Esconde a tela de login

            UsuarioLogado usuarioLogado = UsuarioLogado.getINSTANCE();
            usuarioLogado.setDadosUsuarioLogado(usuario);
            try {
                principalPresenter.atualizarEstadoUsuario(usuario);
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioLoginState.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(view, "Email ou senha inválidos", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
