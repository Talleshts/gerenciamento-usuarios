/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.presenter.state;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.model.UsuarioLogado;
import com.ufes.presenter.ManterUsuarioPresenter;
import com.ufes.view.ManterUsuarioView;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author talle
 */
public class ManterUsuarioEditarState implements ManterUsuarioState {

    Usuario usuarioLogado = UsuarioLogado.getINSTANCE().getDadosUsuarioLogado();
    private ManterUsuarioPresenter manterUsuarioPresenter;

    public ManterUsuarioEditarState(ManterUsuarioView view, Usuario usuario) {
        aplicarState(view);
        preencherCampos(view, this.usuarioLogado);
    }

    private void preencherCampos(ManterUsuarioView view, Usuario usuario) {
        view.setUsuario(usuario);
    }

    @Override
    public void aplicarState(ManterUsuarioView view) {
        // Mudar o título da tela
        view.setTitulo("Editar Usuário");

        // Tornar os campos editáveis
        view.setNomeEditable(true);
        view.setEmailEditable(true);
        view.setSenhaEditable(true);
        view.setConfirmaSenhaEditable(true);

        // Ativar e mudar o texto do botão de salvar para "Atualizar"
        view.setButtonText("Atualizar");
        view.setSalvarButtonEnabled(true);

        // Configurar o texto do botão de cancelar para "Cancelar"
        view.setCancelarButtonText("Cancelar");
        inicializarCamposDaView(view);
    }

    @Override
    public void executarAcao(ManterUsuarioView view) {
        // Cria um objeto Usuario com os dados atualizados da view
        Usuario usuarioAtualizado = new Usuario(
                usuarioLogado.getId(),
                view.getjTxtFNome().getText(),
                new String(view.getjPassFSenha().getPassword()),
                view.getjTxtFEmail().getText(),
                usuarioLogado.getDataCadastro(),  // A data de cadastro original permanece
                usuarioLogado.isAdmin(),
                usuarioLogado.isAutorizado()
        );

        // Atualiza o usuário no banco de dados
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.update(usuarioAtualizado);

        // Atualiza o Singleton UsuarioLogado
        UsuarioLogado.getINSTANCE().setDadosUsuarioLogado(usuarioAtualizado);

        // Mensagem de sucesso ou qualquer outra ação após a atualização
        javax.swing.JOptionPane.showMessageDialog(view,
            "Usuário atualizado com sucesso.",
            "Atualização",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }


    public void inicializarCamposDaView(ManterUsuarioView view) {
        Usuario usuario = UsuarioLogado.getINSTANCE().getDadosUsuarioLogado();

        if (usuario != null) {
            // Usar o método setUsuario para centralizar a lógica de preenchimento
            view.setUsuario(usuario);
        } else {
            // Limpa os campos se o usuário for nulo
            view.limparCampos();

            // Exibe uma mensagem para o usuário (opcional)
            javax.swing.JOptionPane.showMessageDialog(view,
                "Usuário não encontrado ou os dados não foram carregados corretamente.",
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }


}
