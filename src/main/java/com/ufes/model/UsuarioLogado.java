package com.ufes.model;

public class UsuarioLogado {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private boolean isAdmin;
    private boolean isAutorizado;

    private static UsuarioLogado INSTANCE;
    private Usuario dadosUsuarioLogado;

    private UsuarioLogado(){

    }
    public static UsuarioLogado getINSTANCE(){
        if (INSTANCE == null) {
            INSTANCE = new UsuarioLogado();
        }
        return INSTANCE;
    }
    
    public void setDadosUsuarioLogado(Usuario usuario){
        this.dadosUsuarioLogado = usuario;
    }
    
    public Usuario getDadosUsuarioLogado() {
        return dadosUsuarioLogado;
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
