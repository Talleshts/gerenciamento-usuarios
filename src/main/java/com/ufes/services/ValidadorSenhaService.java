/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.services;

import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author talle
 */
public class ValidadorSenhaService {
    public static void validar(String senha) throws Exception{
        List<String> lista = new ValidadorSenha().validar(senha);
         if(!lista.isEmpty()){
             String senhaRecusada = "";
             for (String erroSenha: lista) {
                 senhaRecusada = senhaRecusada + erroSenha + "\n";
             }
             JOptionPane.showInternalMessageDialog(null, senhaRecusada,"Senha inválida", JOptionPane.WARNING_MESSAGE);
             throw new Exception(senhaRecusada);
        }
    }  
}
