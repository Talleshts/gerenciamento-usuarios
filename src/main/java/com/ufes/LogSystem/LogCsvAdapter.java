package com.ufes.LogSystem;

import org.joda.time.LocalDateTime;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogCsvAdapter implements ILogAdapter {
    private static final String LOG_DIRECTORY = "logs";
    private String formatarDataHora() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.toString("dd/MM/yyyy, HH:mm:ss");
    }

    private void escreverNoArquivo(String mensagem) {
        File directory = new File(LOG_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir(); // Cria o diretório se ele não existir
        }
        try (FileWriter fw = new FileWriter(LOG_DIRECTORY + "/" + "log.csv", true)) {
            fw.write(mensagem + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logOperacao(String operacao, String nomeContato, String usuario) {
        String dataHora = formatarDataHora();
        String mensagem = operacao + "," + nomeContato + "," + dataHora + "," + usuario;
        escreverNoArquivo(mensagem);
    }

    @Override
    public void logFalhaOperacao(String operacao, String nomeContato, String usuario, String mensagemErro) {
        String dataHora = formatarDataHora();
        String mensagem = "Falha," + mensagemErro + "," + operacao + "," + nomeContato + "," + dataHora + "," + usuario;
        escreverNoArquivo(mensagem);
    }
}
