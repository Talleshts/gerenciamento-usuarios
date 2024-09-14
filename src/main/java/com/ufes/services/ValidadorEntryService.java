/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.services;

import com.ufes.view.ManterUsuarioView;
import javax.swing.JOptionPane;

/**
 *
 * @author talle
 */
public class ValidadorEntryService {

    private ManterUsuarioView view;

    public ValidadorEntryService(ManterUsuarioView view) {
        this.view = view;
    }

    public void validarCadastro() throws IllegalArgumentException {
        String nome = view.getjTxtFNome().getText();
        String email = view.getjTxtFEmail().getText();
        String senha = String.valueOf(view.getjPassFSenha1().getPassword());

       // UserValidator.validate(nome, email, senha);
    }
}
