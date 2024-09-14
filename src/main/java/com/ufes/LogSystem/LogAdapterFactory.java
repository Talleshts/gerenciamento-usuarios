package com.ufes.LogSystem;

import java.util.HashMap;
import java.util.Map;

public class LogAdapterFactory {
    private static final Map<String, ILogAdapter> logMap = new HashMap<>();

    static {
        // Inicializa o mapa com as implementações
        logMap.put("JSON", new LogJsonAdapter());
        logMap.put("CSV", new LogCsvAdapter());
    }

    public static ILogAdapter getLogAdapter(String type) {
        // Retorna a implementação com base na chave, padrão para JSON se não encontrar
        return logMap.getOrDefault(type.toUpperCase(), new LogJsonAdapter());
    }
}
//esse tipo log ai vai vir do combox que o usuário clicar
//ILogAdapter logAdapter = LogAdapterFactory.getLogAdapter(tipoLog);
//logAdapter.logOperacao("Inclusão", "João Silva", "Caio");
//logAdapter.logFalhaOperacao("Alteração", "Maria Souza", "admin", "Erro de conexão com o banco de dados");