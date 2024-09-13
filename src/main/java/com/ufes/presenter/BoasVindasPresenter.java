/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import java.io.IOException;

import javax.swing.JDesktopPane;
import java.awt.event.*;

import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;

/**
 *
 * @author talle
 */
public class BoasVindasPresenter {

	private BoasVindasView boasVindasView;
	private ManterUsuarioView manterUsuarioView;

	public BoasVindasPresenter(JDesktopPane desktopPane) throws IOException {
		boasVindasView = BoasVindasView.getInstance();
		manterUsuarioView = new ManterUsuarioView();
		desktopPane.add(boasVindasView);
		desktopPane.add(manterUsuarioView);

		boasVindasView.setVisible(true);
		boasVindasView.getLoginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				boasVindasView.setVisible(false);
                // Code to be executed when the button is clicked
				manterUsuarioView.setVisible(true);
            }
        });

		boasVindasView.getSigninBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				boasVindasView.setVisible(false);
                // Code to be executed when the button is clicked
				manterUsuarioView.setVisible(true);
            }
        });
	}
}
