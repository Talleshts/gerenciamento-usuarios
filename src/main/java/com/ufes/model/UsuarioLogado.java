package com.ufes.model;

import com.ufes.services.ConnectionDBService;

import java.util.Date;

public class UsuarioLogado {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private boolean isAdmin;
    private boolean isAutorizado;

    private static UsuarioLogado INSTANCE;

    private UsuarioLogado(){

    }
    public static UsuarioLogado getINSTANCE(){
        if (INSTANCE == null) {
            INSTANCE = new UsuarioLogado();
        }
        return INSTANCE;
    }
    public void setDadosUsuarioLogado(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.senha = usuario.getSenha();
        this.email = usuario.getEmail();
        this.isAdmin = usuario.isAdmin();
        this.isAutorizado = usuario.isAutorizado();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isAutorizado() {
        return isAutorizado;
    }
}
