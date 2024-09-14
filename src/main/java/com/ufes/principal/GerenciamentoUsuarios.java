/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ufes.principal;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;
import com.ufes.presenter.PrincipalPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tallesh
 */
public class GerenciamentoUsuarios {

    public static void main(String[] args) throws Exception{
        try{
            new PrincipalPresenter();
        }catch (Exception e){
              System.out.println("Hello World!");
        }
    }
}
