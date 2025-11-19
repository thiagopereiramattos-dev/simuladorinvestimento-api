package br.com.mattos.simuladorinvestimento.application.dto.response;

/** DTO de resposta com o resultado de uma simulação. */
public record ResultadoSimulacaoResponseDTO(String valorFinal, Double rentabilidadeEfetiva, Integer prazoMeses){

}
