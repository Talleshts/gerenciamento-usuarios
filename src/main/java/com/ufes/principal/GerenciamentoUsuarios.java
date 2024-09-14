/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ufes.principal;

import com.ufes.DAO.IniciadorDataBase;
import com.ufes.DAO.NotificacaoDAO;
import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.presenter.PrincipalPresenter;

/**
 *
 * @author tallesh
 */
public class GerenciamentoUsuarios {

    public static void main(String[] args) throws Exception{
        try{
            IniciadorDataBase.initialize();
            new PrincipalPresenter();
        }catch (Exception e){
            System.out.println("Erro ao iniciar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
