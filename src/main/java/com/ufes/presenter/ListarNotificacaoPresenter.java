/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.model.Notificacao;
import com.ufes.observer.IObserverNotificacao;
import com.ufes.view.ListarNotificacaoView;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author talle
 */
public class ListarNotificacaoPresenter implements IObserverNotificacao {
    private ListarNotificacaoView listarNotificacaoView;

    public ListarNotificacaoPresenter(ListarNotificacaoView listarNotificacaoView) {
        this.listarNotificacaoView = listarNotificacaoView;
    }

    public JInternalFrame getView() {
        return listarNotificacaoView;
    }

    @Override
    public void update(Notificacao notificacao) {
        DefaultTableModel model = (DefaultTableModel) listarNotificacaoView.getTable().getModel();
        Object[] notificationData = {
                notificacao.getId(),
                notificacao.getTitulo(),
                notificacao.getMensagem(),
                notificacao.getDataEnvio()
        };

        // Add the new notification data to the table model
        model.addRow(notificationData);
    }
}
