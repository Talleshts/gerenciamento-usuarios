package com.ufes.services;

import com.ufes.view.ManterUsuarioView;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class ValidadorEntryService {

    private ManterUsuarioView view;
    private ValidadorSenhaService validadorSenhaService;

    public ValidadorEntryService(ManterUsuarioView view) {
        this.view = view;
        this.validadorSenhaService = new ValidadorSenhaService();
    }

    public void validarCadastro() throws IllegalArgumentException {
        String nome = view.getjTxtFNome().getText();
        String email = view.getjTxtFEmail().getText();
        String senha = String.valueOf(view.getjPassFSenha().getPassword());
        String senhaConfirmada = String.valueOf(view.getjPassFSenha2().getPassword());

        // Verificar se o nome está vazio
        if (nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nome não pode ser vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }

        // Validar o email
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(view, "Email inválido.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Email inválido.");
        }

        // Validar senhas
        if (!senha.equals(senhaConfirmada)) {
            JOptionPane.showMessageDialog(view, "As senhas não coincidem.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("As senhas não coincidem.");
        }

        // Usar ValidadorSenhaService para validar a senha
        try {
            validadorSenhaService.validar(senha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Senha inválida.");
        }
    }

    private boolean isValidEmail(String email) {
        // Regex para validar o formato do email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
