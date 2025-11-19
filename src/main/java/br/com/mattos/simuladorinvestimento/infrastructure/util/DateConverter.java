package br.com.mattos.simuladorinvestimento.infrastructure.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Utilitário centralizado para conversões de {@link Instant} para datas locais
 * utilizando o fuso horário de "America/Sao_Paulo".
 *
 * <p>
 * Esta classe evita duplicação de lógica de conversão em diferentes
 * camadas da aplicação e garante consistência no tratamento de datas.
 * </p>
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
