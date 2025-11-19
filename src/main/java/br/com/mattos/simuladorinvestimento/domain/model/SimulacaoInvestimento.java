package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.Instant;

/** Representa uma simulação de investimento completa, incluindo produto e resultado. */
public record SimulacaoInvestimento(Long idSimulacao, Long clientId, Produto produto, ResultadoSimulacao resultado, Instant dataSimulacao) {

    /** Construtor alternativo que define a data como agora e o id como null */
    public SimulacaoInvestimento(Long clientId, Produto produto, ResultadoSimulacao resultado) {
        this(null, clientId, produto, resultado, Instant.now());
    }

    /** Construtor alternativo que define apenas a data como agora */
    public SimulacaoInvestimento(Long idSimulacao, Long clientId, Produto produto, ResultadoSimulacao resultado) {
        this(idSimulacao, clientId, produto, resultado, Instant.now());
    }
}
