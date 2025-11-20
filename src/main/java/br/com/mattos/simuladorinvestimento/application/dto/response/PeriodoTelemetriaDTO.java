package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(name = "PeriodoTelemetria", description = "Período usado para consulta de telemetria")
public record PeriodoTelemetriaDTO(
        @Schema(description = "Data inicial do período", example = "2025-01-01")
        String inicio,
        @Schema(description = "Data final do período", example = "2025-01-31")
        String fim
) {}
