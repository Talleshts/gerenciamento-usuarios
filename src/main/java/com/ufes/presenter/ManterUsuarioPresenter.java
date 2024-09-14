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
                System.out.println("ENTRAAAAA");
                entrarNoSistemaUsuario();
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.view.getjBtnCancelar().addActionListener(e -> {
            try {
                System.out.println("CANCELAAAAAA");
                cancelar();
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Ao clicar em "Cadastrar" ou "Logar"
    private void entrarNoSistemaUsuario() throws IOException {
        // Instanciar o serviço de validação
        ValidadorEntryService validadorEntryService = new ValidadorEntryService(view);
        
        try {
            // Validar entradas
            validadorEntryService.validarCadastro();
            
            // Criar o usuário com base nos dados da view
            String nome = view.getjTxtFNome().getText();
            String senha = String.valueOf(view.getjPassFSenha().getPassword());
            Usuario usuario = new Usuario(nome, senha, true, true);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.insert(usuario); // Salvando o usuário no banco
            
            JOptionPane.showMessageDialog(view, "Cadastro realizado com sucesso!");
            
            view.setVisible(false); // Esconde a tela de manter usuário
            
            // Volta para a tela de boas-vindas
            new BoasVindasPresenter(desktopPane);
            
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
