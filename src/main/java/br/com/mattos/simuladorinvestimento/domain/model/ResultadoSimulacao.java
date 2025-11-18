package br.com.mattos.simuladorinvestimento.domain.model;

/** Resultado de uma simulação de investimento. */
public record ResultadoSimulacao(Double valorFinal,Double rentabilidadeEfetiva,Integer prazoMeses) {

}
