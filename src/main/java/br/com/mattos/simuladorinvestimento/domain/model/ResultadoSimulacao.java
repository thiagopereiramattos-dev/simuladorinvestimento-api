package br.com.mattos.simuladorinvestimento.domain.model;

public record ResultadoSimulacao(
        Double valorInvestido,
        Double valorFinal,
        Double rentabilidadeEfetiva,
        Integer prazoMeses
) {}
