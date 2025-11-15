package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.Instant;

public record Simulacao(Produto produto,ResultadoSimulacao resultado,Instant dataSimulacao) {
    public Simulacao(Produto produto, ResultadoSimulacao resultado) {
        this(produto, resultado, Instant.now());
    }
}
