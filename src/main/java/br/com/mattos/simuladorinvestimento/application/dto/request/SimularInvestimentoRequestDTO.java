package br.com.mattos.simuladorinvestimento.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SimularInvestimentoRequest", description = "DTO para requisição de simulação de investimento")
public record SimularInvestimentoRequestDTO(
        @NotNull @Positive
        @Schema(description = "ID do cliente", example = "1", required = true)
        Long clienteId,
        @NotNull @Positive
        @Schema(description = "Valor a ser investido", example = "10000.50", required = true)
        Double valor,
        @NotNull @Positive @Schema(description = "Prazo em meses", example = "12", required = true)
        Integer prazoMeses,
        @NotBlank @Schema(description = "Tipo do produto de investimento", example = "CDB", required = true)
        String tipoProduto
) {}
