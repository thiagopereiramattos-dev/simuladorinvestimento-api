package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "ResultadoSimulacaoResponse", description = "DTO com resultado da simulação")
public record ResultadoSimulacaoResponseDTO(
        @Schema(description = "Valor final do investimento", example = "10500.50")
        String valorFinal,
        @Schema(description = "Rentabilidade efetiva obtida", example = "0.05")
        Double rentabilidadeEfetiva,
        @Schema(description = "Prazo em meses da simulação", example = "12")
        Integer prazoMeses
){}
