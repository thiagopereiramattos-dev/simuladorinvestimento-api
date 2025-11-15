package br.com.mattos.simuladorinvestimento.domain.model;

public class Produto {

    private final Long id;
    private final String nome;
    private final String tipo;
    private final Double rentabilidade;
    private final String risco;

    public Produto(Long id, String nome, String tipo, Double rentabilidade, String risco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.rentabilidade = rentabilidade;
        this.risco = risco;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public Double getRentabilidade() { return rentabilidade; }
    public String getRisco() { return risco; }
}
