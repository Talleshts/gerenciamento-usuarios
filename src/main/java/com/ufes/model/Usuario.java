/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.model;

import java.time.LocalDateTime;

/**
 *
 * @author tallesh
 */
public class Usuario {
    private String nome;
    private String senha;
    private String email;
    private LocalDateTime dataCadastro;
    private boolean isAdmin;

    public Usuario(String nome, String senha, String email, LocalDateTime dataCadastro, boolean isAdmin) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.isAdmin = isAdmin;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }
    
    
    @Override
    public String toString(){
        return this.getNome();
    }

}
