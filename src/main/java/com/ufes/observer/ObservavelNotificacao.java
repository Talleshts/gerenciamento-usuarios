/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ufes.observer;

import java.util.ArrayList;
import java.util.List;
import com.ufes.model.Notificacao;

/**
 *
 * @author talle
 */
public class ObservavelNotificacao {
    private List<IObserverNotificacao> observadores = new ArrayList<>();
    
    public void adicionarObserver(IObserverNotificacao observer) {
        observadores.add(observer);
    }

    public void removerObserver(IObserverNotificacao observer) {
        observadores.remove(observer);
    }

    public void notificarObservers(Notificacao notificacao) {
        for(IObserverNotificacao observer: observadores) {
            observer.update(notificacao);
        }
    }
}
