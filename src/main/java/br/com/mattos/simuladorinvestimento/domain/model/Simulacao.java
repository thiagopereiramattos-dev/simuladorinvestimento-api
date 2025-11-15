package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.Instant;

public class Simulacao {

    private final Produto produto;
    private final ResultadoSimulacao resultado;
    private final Instant dataSimulacao;

    public Simulacao(Produto produto, ResultadoSimulacao resultado) {
        this.produto = produto;
        this.resultado = resultado;
        this.dataSimulacao = Instant.now();
    }

    public Produto getProduto() { return produto; }
    public ResultadoSimulacao getResultado() { return resultado; }
    public Instant getDataSimulacao() { return dataSimulacao; }
}
