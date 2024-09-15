/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter;

import com.ufes.DAO.NotificacaoDAO;
import com.ufes.model.Notificacao;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.observer.IObserverNotificacao;
import com.ufes.view.ListarNotificacaoView;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author talle
 */
public class ListarNotificacaoPresenter implements IObserverNotificacao {
    private ListarNotificacaoView listarNotificacaoView;
    private NotificacaoDAO notificacaoDAO;

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
    
    private void carregarNotificoes() throws Exception{
        List<Notificacao> notificacoes = notificacaoDAO.getAllByIdUsuario(UsuarioLogado.getINSTANCE().getId());

        // Cria um DefaultTableModel para a tabela
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("titulo");
        model.addColumn("mensagem");
        model.addColumn("id_usuario");
        model.addColumn("Data de Envio");
        model.addColumn("Foi visualizada");

        // Adiciona cada usuário ao modelo da tabela
        for (Notificacao notificacao : notificacoes) {
            model.addRow(new Object[]{
                notificacao.getId(),
                notificacao.getTitulo(),
                notificacao.getMensagem(),
                notificacao.getUsuario(),
                notificacao.getDataEnvio(),
                notificacao.isVisualizou()? "Sim" : "Não"
            });
        }

        // Define o modelo da tabela na view
        listarNotificacaoView.getTblNotificacoes().setModel(model);        
    }
}
