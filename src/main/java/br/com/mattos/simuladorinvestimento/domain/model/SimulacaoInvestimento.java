package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.Instant;

/**
 * Representa uma simulação de investimento completa, incluindo produto e resultado.
 */
public record SimulacaoInvestimento(
        Long idSimulacao,
        Long clientId,
        Produto produto,
        ResultadoSimulacao resultado,
        Instant dataSimulacao
) {

    /**
     * Cria uma simulação com id nulo e data atual.
     */
    public SimulacaoInvestimento(Long clientId, Produto produto, ResultadoSimulacao resultado) {
        this(null, clientId, produto, resultado, Instant.now());
    }

    /**
     * Cria uma simulação com data atual.
     */
    public SimulacaoInvestimento(Long idSimulacao, Long clientId, Produto produto, ResultadoSimulacao resultado) {
        this(idSimulacao, clientId, produto, resultado, Instant.now());
    }
}
