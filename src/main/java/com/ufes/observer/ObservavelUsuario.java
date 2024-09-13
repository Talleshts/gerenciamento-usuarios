/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.observer;

import java.util.ArrayList;
import java.util.List;
import com.ufes.model.Usuario;

/**
 *
 * @author talle
 */
public class ObservavelUsuario {
    private List<IObserver> observadores = new ArrayList<>();
    
    public void adicionarObserver(IObserver observer) {
        observadores.add(observer);
    }

    public void removerObserver(IObserver observer) {
        observadores.remove(observer);
    }

    public void notificarObservers(Usuario usuario) {
        for(IObserver observer:observadores) {
            observer.update(usuario);
        }
    }
}
