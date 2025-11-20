package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "TelemetriaResponseDTO", description = "Resposta de telemetria de serviços")
public record TelemetriaResponseDTO(
        @Schema(description = "Lista de serviços com métricas")
        List<ServicoTelemetriaDTO> servicos,
        @Schema(description = "Período da telemetria")
        PeriodoTelemetriaDTO periodo
) {}
