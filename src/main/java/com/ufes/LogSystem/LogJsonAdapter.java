package com.ufes.LogSystem;

import org.joda.time.LocalDateTime;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogJsonAdapter implements ILogAdapter {
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
        try (FileWriter fw = new FileWriter(LOG_DIRECTORY + "/" + "log.json", true)) {
                fw.write(mensagem + "\n");  // Grava cada objeto JSON em uma linha separada
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void logOperacao(String operacao, String nomeContato, String usuario) {
        String dataHora = formatarDataHora();
        JSONObject json = new JSONObject();
        json.put("operacao", operacao);
        json.put("nomeContato", nomeContato);
        json.put("dataHora", dataHora);
        json.put("usuario", usuario);

        escreverNoArquivo(json.toString());
    }

    @Override
    public void logFalhaOperacao(String operacao, String nomeContato, String usuario, String mensagemErro) {
        String dataHora = formatarDataHora();
        JSONObject json = new JSONObject();
        json.put("tipo", "Falha");
        json.put("mensagemErro", mensagemErro);
        json.put("operacao", operacao);
        json.put("nomeContato", nomeContato);
        json.put("dataHora", dataHora);
        json.put("usuario", usuario);

        escreverNoArquivo(json.toString());
    }
}