package br.com.mattos.simuladorinvestimento.infrastructure.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Utilitário centralizado para conversões de data e hora
 * usando fuso horário de São Paulo.
 */
public final class DateConverter {

    private static final ZoneId ZONE = ZoneId.of("America/Sao_Paulo");

    private DateConverter() {}

    public static LocalDate toLocalDate(Instant instant) {
        return instant.atZone(ZONE).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        return instant.atZone(ZONE).toLocalDateTime();
    }

    public static String toDateTimeText(Instant instant) {
        return toLocalDateTime(instant).toString();
    }
}
