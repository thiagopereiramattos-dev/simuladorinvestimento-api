package br.com.mattos.simuladorinvestimento.application.dto.response;

/** DTO de resposta resumida de lista de simulações. */
public record SimulacaoListResponseDTO(Integer id,Integer clienteId,String produto,Double valorInvestido,Double valorFinal,
                                       Integer prazoMeses,String dataSimulacao) {

}

