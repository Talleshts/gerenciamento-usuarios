/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter.state;

import com.ufes.view.ManterUsuarioView;

/**
 *
 * @author talle
 */
public class ManterUsuarioVisualizarState implements ManterUsuarioState{
     @Override
    public void aplicarState(ManterUsuarioView view) {
        // Mudar o título da tela
        view.setTitulo("Visualizar Usuário");

        // Desabilitar campos para torná-los somente leitura
        view.setNomeEditable(false);
        view.setEmailEditable(false);
        view.setSenhaEditable(false);
        view.setConfirmaSenhaEditable(false);

        // Ocultar ou desativar botão de salvar
        view.setSalvarButtonEnabled(false);

        // Configura os botões
        view.setCancelarButtonText("Fechar");
    }

    @Override
    public void entrarNoSistemaState() {
    }
}
