package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.Instant;

public record SimulacaoInvestimento(Integer clientId, Produto produto, ResultadoSimulacao resultado, Instant dataSimulacao) {
    public SimulacaoInvestimento(Integer clientId, Produto produto, ResultadoSimulacao resultado) {
        this(clientId, produto, resultado, Instant.now());
    }
}
