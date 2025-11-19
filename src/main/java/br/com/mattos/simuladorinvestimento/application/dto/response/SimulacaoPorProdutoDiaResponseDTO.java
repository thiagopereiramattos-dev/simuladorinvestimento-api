package br.com.mattos.simuladorinvestimento.application.dto.response;

import java.time.LocalDate;

/**
 * DTO de resposta para retornar informações de simulações agrupadas por produto e dia.
 */
public record SimulacaoPorProdutoDiaResponseDTO(String produto,LocalDate data,Long quantidadeSimulacoes,String mediaValorFinal) {
}
