/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.observer;

import com.ufes.model.Usuario;

/**
 *
 * @author talle
 */
public interface IObservavelUsuario {
    public void adicionarObserver(IObserverUsuario observer);

    public void removerObserver(IObserverUsuario observer);

    public void notificarObservers(Usuario usuario);
}
