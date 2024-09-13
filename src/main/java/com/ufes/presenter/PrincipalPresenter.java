/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.model.Usuario;
import com.ufes.observer.IObserverUsuario;
import com.ufes.view.PrincipalView;
import java.io.IOException;

/**
 *
 * @author tallesh
 */
public class PrincipalPresenter implements IObserverUsuario{
    
    private PrincipalView principalView;
    private Usuario usuario = null;

    public PrincipalPresenter() throws IOException {
        principalView = new PrincipalView();
        principalView.setVisible(true);
    }
    
    
    
    @Override
    public void update(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
