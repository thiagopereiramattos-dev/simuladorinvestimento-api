package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.Instant;

public record SimulacaoInvestimento(Integer idSimulacao, Integer clientId, Produto produto, ResultadoSimulacao resultado, Instant dataSimulacao) {
    public SimulacaoInvestimento(Integer idSimulacao, Integer clientId, Produto produto, ResultadoSimulacao resultado) {
        this(idSimulacao, clientId, produto, resultado, Instant.now());
    }
}
