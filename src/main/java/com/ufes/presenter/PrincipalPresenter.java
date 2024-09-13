/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import java.io.IOException;

import com.ufes.model.Usuario;
import com.ufes.observer.IObserverUsuario;
import com.ufes.view.PrincipalView;

/**
 *
 * @author tallesh
 */
public class PrincipalPresenter implements IObserverUsuario {

	private PrincipalView principalView;
	private Usuario usuario = null;

	public PrincipalPresenter() throws IOException {
		principalView = new PrincipalView();
		principalView.setVisible(true);

		if (usuario == null) {
			new BoasVindasPresenter(principalView.getDesktopPane());
		}
	}

	@Override
	public void update(Usuario usuario) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
