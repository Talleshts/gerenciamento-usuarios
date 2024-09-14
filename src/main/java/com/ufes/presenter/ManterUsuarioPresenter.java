/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.presenter.state.ManterUsuarioState;
import com.ufes.view.ManterUsuarioView;

/**
 *
 * @author talle
 */
public class ManterUsuarioPresenter {
    private ManterUsuarioView view;
    private ManterUsuarioState currentState;

    public ManterUsuarioPresenter(ManterUsuarioView view) {
        this.view = view;
        this.view.addCadastrarListener(e -> cadastrarUsuario());
        this.view.addCancelarListener(e -> cancelar());
    }

    private void cadastrarUsuario() {
        // lógica para cadastrar usuário
        System.out.println("Cadastrando usuário...");
    }

    private void cancelar() {
        // lógica para cancelar
        System.out.println("Cancelando operação...");
    }

    public void setState(ManterUsuarioState state) {
        this.currentState = state;
        this.currentState.aplicarState(view);
    }
}
