package br.com.mattos.simuladorinvestimento.domain.enums;

/**
 * Enum que representa o nível de risco de um produto.
 */
public enum RiscoProduto {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private final String descricao;

    RiscoProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}