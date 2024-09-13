/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.view.ListarNotificacaoView;
import javax.swing.JInternalFrame;

/**
 *
 * @author talle
 */
public class ListarNotificacaoPresenter {
    private ListarNotificacaoView listarNotificacaoView;
    
    public ListarNotificacaoPresenter(ListarNotificacaoView listarNotificacaoView){
        this.listarNotificacaoView = listarNotificacaoView;
    }
    
    public JInternalFrame getView(){
        return listarNotificacaoView;
    }
}
