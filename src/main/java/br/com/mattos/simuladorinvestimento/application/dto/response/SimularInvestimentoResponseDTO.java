package br.com.mattos.simuladorinvestimento.application.dto.response;

/** DTO de resposta de simulação completa. */
public record SimularInvestimentoResponseDTO(ProdutoSimulacaoResponseDTO produtoValidado, ResultadoSimulacaoResponseDTO resultadoSimulacao, String dataSimulacao){

}

