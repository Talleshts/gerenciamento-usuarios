/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.LogSystem;

/**
 *
 * @author talle
 */
public class LogConfig {
    private static String tipoLogSelecionado = "JSON"; // Valor padrão

    public static void setTipoLogSelecionado(String tipo) {
        tipoLogSelecionado = tipo;
    }

    public static String getTipoLogSelecionado() {
        return tipoLogSelecionado;
    }
}
