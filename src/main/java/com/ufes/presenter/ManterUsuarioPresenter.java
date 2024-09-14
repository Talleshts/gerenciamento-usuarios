package com.ufes.presenter;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.presenter.state.ManterUsuarioState;
import com.ufes.services.ValidadorEntryService;
import com.ufes.view.ManterUsuarioView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;

/**
 *
 * @author talle
 */
public class ManterUsuarioPresenter {
    private ManterUsuarioView view;
    private ManterUsuarioState currentState;
    private JDesktopPane desktopPane;

    public ManterUsuarioPresenter(ManterUsuarioView view, JDesktopPane desktopPane) {
        this.view = view;
        this.desktopPane = desktopPane;
        
        this.view.getjBtnCadastrar().addActionListener(e -> {
            try {
                executarAcao();
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.view.getjBtnCancelar().addActionListener(e -> {
            try {
                cancelar();
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Ao clicar em "Cadastrar" ou "Logar"
    private void executarAcao() throws IOException {
        if (currentState == null) {
            throw new IllegalStateException("Estado não definido");
        }
        
        try {
            ValidadorEntryService validadorEntryService = new ValidadorEntryService(view);
            validadorEntryService.validarCadastro(); // Validar entradas

            // Delegar a lógica específica para o estado atual
            currentState.executarAcao(view);
            
        } catch (IllegalArgumentException e) {
            // Exibir a mensagem de erro
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Ao clicar em "Cancelar"
    private void cancelar() throws IOException {
        view.setVisible(false); // Esconde a tela de manter usuário
        // Volta para a tela de boas-vindas
        new BoasVindasPresenter(desktopPane);
    }

    public void setState(ManterUsuarioState state) {
        this.currentState = state;
        this.currentState.aplicarState(view);
    }
}
