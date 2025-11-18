package br.com.mattos.simuladorinvestimento.domain.model;

/** Dados de entrada para criar uma simulação de investimento. */
public record SimulacaoEntrada(Integer clienteId,Double valor,Integer prazoMeses,String tipoProduto) {

}
