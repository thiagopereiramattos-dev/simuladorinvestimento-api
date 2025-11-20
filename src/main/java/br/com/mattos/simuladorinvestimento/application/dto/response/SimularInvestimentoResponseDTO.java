package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SimularInvestimentoResponseDTO", description = "Resultado de simulação de investimento")
public record SimularInvestimentoResponseDTO(
        @Schema(description = "Produto validado da simulação")
        ProdutoSimulacaoResponseDTO produtoValidado,
        @Schema(description = "Resultado da simulação")
        ResultadoSimulacaoResponseDTO resultadoSimulacao,
        @Schema(description = "Data da simulação", example = "2025-11-20")
        String dataSimulacao
){}

