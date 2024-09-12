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
	    private Usuario usuario;
	    private String mensagem;
        private boolean visualizou;
        private LocalDateTime dataEnvio;

    public Notificacao(int id, Usuario usuario, String mensagem, boolean visualizou, LocalDateTime dataEnvio) {
        this.id = id;
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.visualizou = visualizou;
        this.dataEnvio = dataEnvio;
    }

    public Notificacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
