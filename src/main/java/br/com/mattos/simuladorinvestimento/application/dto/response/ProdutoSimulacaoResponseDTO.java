package br.com.mattos.simuladorinvestimento.application.dto.response;

/** DTO de resposta com informações do produto para simulação. */
public record ProdutoSimulacaoResponseDTO(Integer id, String nome, String tipo, Double rentabilidade, String risco) {

}

