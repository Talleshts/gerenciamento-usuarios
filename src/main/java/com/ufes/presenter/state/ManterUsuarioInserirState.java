/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter.state;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.LogSystem.ILogAdapter;
import com.ufes.LogSystem.LogAdapterFactory;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.presenter.BoasVindasPresenter;
import com.ufes.presenter.PrincipalPresenter;
import com.ufes.services.ValidadorEntryService;
import com.ufes.view.BoasVindasView;
import com.ufes.view.ManterUsuarioView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author talle
 */
public class ManterUsuarioInserirState implements ManterUsuarioState{
    private UsuarioDAO usuarioDAO;
    private ManterUsuarioView manterUsuarioView;
    private PrincipalPresenter principalPresenter;

    public ManterUsuarioInserirState(ManterUsuarioView manterUsuarioView, PrincipalPresenter principalPresenter) {
        this.manterUsuarioView = manterUsuarioView;
        this.principalPresenter = principalPresenter; // Inicializando o PrincipalPresenter
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    public void aplicarState(ManterUsuarioView view) {
        // Configura a view para inserção de usuário
        view.setTitulo("Cadastro de Usuário");
        view.setNomeVisible(true);
        view.setEmailEditable(true);
        view.setSenhaEditable(true);
        view.setConfirmaSenhaVisible(true);
        view.setButtonText("Cadastrar");
        view.setCancelarButtonText("Cancelar");
    }
    
    @Override
    public void executarAcao(ManterUsuarioView view) {
        // Lógica de cadastro
        String nome = view.getjTxtFNome().getText();
        String senha = String.valueOf(view.getjPassFSenha().getPassword());
        String email = String.valueOf(view.getjTxtFEmail().getText());

        // Obtém os dados do usuário logado
        Usuario usuarioLogado = UsuarioLogado.getINSTANCE().getDadosUsuarioLogado();

        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(view, "Nenhum usuário está logado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se não houver usuário logado
        }

        String nomeUsuarioLogado = usuarioLogado.getNome(); // Nome do usuário logado
        String operacao = "Cadastro de usuário"; // Descreva a operação

        ValidadorEntryService validadorEntryService = new ValidadorEntryService(view);
        validadorEntryService.validarCadastro(); // Validar entradas

        Usuario usuarioObj = new Usuario(nome, senha, email, false, true);

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // Log do erro de banco de dados
            usuarioDAO.insert(usuarioObj); // Salvando o usuário no banco

            // Log da ação de inserção
            List<ILogAdapter> logAdapters = LogAdapterFactory.getLogAdapters(); // Obtém a lista de adaptadores de log
            for (ILogAdapter logAdapter : logAdapters) {
                logAdapter.logOperacao(operacao, nome, nomeUsuarioLogado);
            }

            // Exibe a mensagem de sucesso
            JOptionPane.showMessageDialog(view, "Cadastro realizado com sucesso!");

            // Verifica se o usuário já está logado
            if (UsuarioLogado.getINSTANCE().getNome() == null) {
                // Se o usuário não estiver logado, fecha a tela e volta para a tela de boas-vindas
                view.setVisible(false); // Esconde a tela de manter usuário

                // Em vez de criar um novo BoasVindasPresenter, reutiliza o PrincipalPresenter
                if (principalPresenter != null) {
                    principalPresenter.voltarParaBoasVindas();
                } else {
                    JOptionPane.showMessageDialog(view, "Erro: Presenter não inicializado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "O cadastro foi realizado.");
                view.setVisible(false);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao realizar cadastro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


}
