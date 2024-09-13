/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import java.io.IOException;

import com.ufes.model.Usuario;
import com.ufes.observer.IObserver;
import com.ufes.view.ListarNotificacaoView;
import com.ufes.view.PrincipalView;

/**
 *
 * @author tallesh
 */
public class PrincipalPresenter implements IObserver {

    private PrincipalView principalView;
    private ListarNotificacaoPresenter listarNotificacaoPresenter;
    private Usuario usuario = null;

    public PrincipalPresenter() throws IOException {
        principalView = new PrincipalView();
        principalView.setVisible(true);

        if (usuario == null) {
            new BoasVindasPresenter(principalView.getDesktopPane());
        }
        listarNotificacaoPresenter = new ListarNotificacaoPresenter(new ListarNotificacaoView());
        principalView.getNotificationButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarNotificacaoPresenter.getView().setVisible(true);
                System.out.println("Funciona");
            }
        });
    }

    @Override
    public void update(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
