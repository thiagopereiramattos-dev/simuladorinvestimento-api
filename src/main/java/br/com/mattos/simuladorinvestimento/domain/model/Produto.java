package br.com.mattos.simuladorinvestimento.domain.model;

import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;

public record Produto(
        Long id,
        String nome,
        String tipo,
        Double rentabilidade,
        RiscoProduto risco
) {}
