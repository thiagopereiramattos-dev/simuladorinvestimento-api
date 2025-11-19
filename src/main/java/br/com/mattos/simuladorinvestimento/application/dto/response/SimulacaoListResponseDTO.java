package br.com.mattos.simuladorinvestimento.application.dto.response;

public record SimulacaoListResponseDTO(Long id, Long clienteId, String produto, String valorInvestido,
                                       String valorFinal, Integer prazoMeses, String dataSimulacao) {

}

