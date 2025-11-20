package br.com.mattos.simuladorinvestimento.application.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PeriodoTelemetriaDTO(
        LocalDate inicio,
        LocalDate fim
) {}
