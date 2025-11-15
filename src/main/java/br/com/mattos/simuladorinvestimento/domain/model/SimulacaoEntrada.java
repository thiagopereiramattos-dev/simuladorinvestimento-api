package br.com.mattos.simuladorinvestimento.domain.model;

public class SimulacaoEntrada {

    private final Long clienteId;
    private final Double valor;
    private final Integer prazoMeses;
    private final String tipoProduto;

    public SimulacaoEntrada(Long clienteId, Double valor, Integer prazoMeses, String tipoProduto) {
        this.clienteId = clienteId;
        this.valor = valor;
        this.prazoMeses = prazoMeses;
        this.tipoProduto = tipoProduto;
    }

    public Long getClienteId() { return clienteId; }
    public Double getValor() { return valor; }
    public Integer getPrazoMeses() { return prazoMeses; }
    public String getTipoProduto() { return tipoProduto; }
}
