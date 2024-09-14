package com.ufes.presenter.state;

import com.ufes.view.ManterUsuarioView;

public class ManterUsuarioLoginState implements ManterUsuarioState{
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
    public void entrarNoSistemaState() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
