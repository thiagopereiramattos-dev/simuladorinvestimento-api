package br.com.mattos.simuladorinvestimento.application.dto.response;

/**
 * DTO de resposta resumida de lista de simulações.
 */
public record SimulacaoListResponseDTO(Integer id, Integer clienteId, String produto, String valorInvestido,
                                       String valorFinal, Integer prazoMeses, String dataSimulacao) {

}

