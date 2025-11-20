package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SimulacaoListResponseDTO", description = "Resumo de simulações de investimento")
public record SimulacaoListResponseDTO(
        @Schema(description = "ID da simulação", example = "1")
        Long id,
        @Schema(description = "ID do cliente", example = "10")
        Long clienteId,
        @Schema(description = "Nome do produto", example = "CDB")
        String produto,
        @Schema(description = "Valor investido formatado", example = "5000,00")
        String valorInvestido,
        @Schema(description = "Valor final da simulação", example = "5600,00")
        String valorFinal,
        @Schema(description = "Prazo da aplicação em meses", example = "12")
        Integer prazoMeses,
        @Schema(description = "Data da simulação", example = "2025-01-15")
        String dataSimulacao
) {}

