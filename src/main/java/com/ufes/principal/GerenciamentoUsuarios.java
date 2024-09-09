/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ufes.principal;

import com.ufes.DAO.UsuarioDAO;
import com.ufes.model.Usuario;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tallesh
 */
public class GerenciamentoUsuarios {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario("João","123",true,true );
        //usuarioDAO.insert(usuario);
        usuario.setNome("Talles");
        usuario.setId(2);

        usuarioDAO.update(usuario);

        Usuario usuario2 = usuarioDAO.findByID(1);
        System.out.println(usuario2.toString());
        usuario2 = usuarioDAO.findByID(2);
        System.out.println(usuario2.toString());


        List<Usuario> usuarios= usuarioDAO.findAll();
        System.out.println(usuarios.toString());

        usuarioDAO.remove(2);
        usuarios= usuarioDAO.findAll();
        System.out.println(usuarios.toString());
    }
}
