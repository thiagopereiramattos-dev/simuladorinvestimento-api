package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.Instant;

public record SimulacaoInvestimento(Long clientId, Produto produto, ResultadoSimulacao resultado, Instant dataSimulacao) {
    public SimulacaoInvestimento(Long clientId, Produto produto, ResultadoSimulacao resultado) {
        this(clientId, produto, resultado, Instant.now());
    }
}
