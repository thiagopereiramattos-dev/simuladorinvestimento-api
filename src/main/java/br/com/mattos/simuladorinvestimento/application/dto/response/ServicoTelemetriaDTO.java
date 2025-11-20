package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "ServicoTelemetriaDTO", description = "Dados de telemetria de um serviço")
public record ServicoTelemetriaDTO(
        @Schema(description = "Nome do serviço", example = "SimuladorInvestimento")
        String nome,
        @Schema(description = "Quantidade de chamadas realizadas", example = "120")
        Integer quantidadeChamadas,
        @Schema(description = "Tempo médio de resposta em ms", example = "250")
        Integer mediaTempoRespostaMs
) {}