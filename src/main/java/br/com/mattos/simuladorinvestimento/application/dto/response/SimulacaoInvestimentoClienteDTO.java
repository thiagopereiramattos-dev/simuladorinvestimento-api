package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SimulacaoInvestimentoClienteDTO", description = "Informações de simulação de investimento de um cliente")
public record SimulacaoInvestimentoClienteDTO(
        @Schema(description = "ID da simulação", example = "6")
        Long id,
        @Schema(description = "Tipo do investimento", example = "LCI")
        String tipo,
        @Schema(description = "Valor investido formatado", example = "26900,45")
        String valor,
        @Schema(description = "Rentabilidade efetiva", example = "0.01")
        Double rentabilidade,
        @Schema(description = "Data da simulação", example = "2025-11-20")
        String data
) {}

