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
public class Notificacao {
    private int id;
    private int id_usuario;
    private String titulo;
    private String mensagem;
    private boolean visualizou;
    private LocalDateTime dataEnvio;

    public Notificacao(int id_usuario, String titulo, String mensagem) {
        this.id_usuario = id_usuario;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.visualizou = false;
        this.dataEnvio = LocalDateTime.now();
    }

    public Notificacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsuarioId(int id) {
        this.id_usuario = id;
    }

    public int getUsuario() {
        return id_usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isVisualizou() {
        return visualizou;
    }

    public void setVisualizou(boolean visualizou) {
        this.visualizou = visualizou;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
