package com.ufes.presenter;

import java.sql.SQLException;

import com.ufes.DAO.NotificacaoDAO;
import com.ufes.model.Notificacao;
import com.ufes.observer.ObservavelNotificacao;
import com.ufes.view.ManterNotificacaoView;

public class ManterNotificacaoPresenter {
    private ManterNotificacaoView view;

    public ManterNotificacaoPresenter(ManterNotificacaoView view) throws SQLException {
        this.view = view;
        NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
        this.view.getSendButton().addActionListener(e -> {
            // Logic to execute when the button is clicked
            String titulo = view.getTitulo();
            String mensagem = view.getMessage();
            Notificacao notificacao = new Notificacao(1, titulo, mensagem);
            try {
                notificacaoDAO.insert(notificacao);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // Add your desired logic here
        });
    }
}
