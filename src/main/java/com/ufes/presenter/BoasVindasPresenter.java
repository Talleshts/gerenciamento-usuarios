/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import java.io.IOException;

import javax.swing.JDesktopPane;

import com.ufes.view.BoasVindasView;

/**
 *
 * @author talle
 */
public class BoasVindasPresenter {

	private BoasVindasView boasVindasView;

	public BoasVindasPresenter(JDesktopPane desktopPane) throws IOException {
		boasVindasView = new BoasVindasView();
		desktopPane.add(boasVindasView);
		boasVindasView.setVisible(true);
	}
}
