package br.com.mattos.simuladorinvestimento.application.dto.response;

public record SimulacaoListResponseDTO(Integer id,Integer clienteId,String produto,Double valorInvestido,Double valorFinal,
                                       Integer prazoMeses,String dataSimulacao) {

}

