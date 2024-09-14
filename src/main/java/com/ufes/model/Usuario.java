/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author tallesh
 */
public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private Date dataCadastro;
    private boolean isAdmin;
    private boolean isAutorizado;

    public Usuario(int id, String nome, String senha,String email, Date dataCadastro, boolean isAdmin, boolean isAutorizado) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.isAdmin = isAdmin;
        this.isAutorizado = isAutorizado;
    }
    
    public Usuario(String nome, String senha,String email, boolean isAdmin, boolean isAutorizado) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataCadastro = Calendar.getInstance().getTime();
        this.isAdmin = isAdmin;
        this.isAutorizado = isAutorizado;

    }

    public Usuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataCadastro(Date dataCadastro) {
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

    public Usuario temId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isAutorizado() {
        return isAutorizado;
    }

    public void setAutorizado(boolean autorizado) {
        isAutorizado = autorizado;
    }

    @Override
    public String toString(){
        return this.getNome();
    }

}
