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
public class ManterUsuarioEditarState implements ManterUsuarioState {
    @Override
    public void aplicarState(ManterUsuarioView view) {
        // Mudar o título da tela
        view.setTitulo("Editar Usuário");

        // Tornar os campos editáveis
        view.setNomeEditable(true);
        view.setEmailEditable(true);
        view.setSenhaEditable(true);
        view.setConfirmaSenhaEditable(true);

        // Ativar e mudar o texto do botão de salvar para "Atualizar"
        view.setButtonText("Atualizar");
        view.setSalvarButtonEnabled(true);

        // Configurar o texto do botão de cancelar para "Cancelar"
        view.setCancelarButtonText("Cancelar");
    }
}