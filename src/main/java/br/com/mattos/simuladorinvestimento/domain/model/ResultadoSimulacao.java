package br.com.mattos.simuladorinvestimento.domain.model;

public class ResultadoSimulacao {

    private final Double valorFinal;
    private final Double rentabilidadeEfetiva;
    private final Integer prazoMeses;

    public ResultadoSimulacao(Double valorFinal, Double rentabilidadeEfetiva, Integer prazoMeses) {
        this.valorFinal = valorFinal;
        this.rentabilidadeEfetiva = rentabilidadeEfetiva;
        this.prazoMeses = prazoMeses;
    }

    public Double getValorFinal() { return valorFinal; }
    public Double getRentabilidadeEfetiva() { return rentabilidadeEfetiva; }
    public Integer getPrazoMeses() { return prazoMeses; }
}
