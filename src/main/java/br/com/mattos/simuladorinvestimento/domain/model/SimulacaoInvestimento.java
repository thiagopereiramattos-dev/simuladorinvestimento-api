package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.Instant;

/** Representa uma simulação de investimento completa, incluindo produto e resultado. */
public record SimulacaoInvestimento(Integer idSimulacao, Integer clientId, Produto produto, ResultadoSimulacao resultado, Instant dataSimulacao) {
    /** Construtor alternativo que define a data da simulação como o instante atual. */
    public SimulacaoInvestimento(Integer idSimulacao, Integer clientId, Produto produto, ResultadoSimulacao resultado) {
        this(idSimulacao, clientId, produto, resultado, Instant.now());
    }
}
