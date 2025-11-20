package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "SimulacaoPorProdutoDiaResponseDTO", description = "Simulações agregadas por produto e dia")
public record SimulacaoPorProdutoDiaResponseDTO(
        @Schema(description = "Nome do produto", example = "CDB")
        String produto,
        @Schema(description = "Data da simulação", example = "2025-01-15")
        LocalDate data,
        @Schema(description = "Quantidade de simulações no dia", example = "5")
        Long quantidadeSimulacoes,
        @Schema(description = "Média do valor final das simulações", example = "5300,00")
        String mediaValorFinal
) {}
