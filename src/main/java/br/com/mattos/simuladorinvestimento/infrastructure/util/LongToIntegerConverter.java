package br.com.mattos.simuladorinvestimento.infrastructure.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converte entre {@link Long} no domínio/DTO e {@link Integer} no banco SQLite.
 * <p>
 * Resolve o problema do Hibernate + SQLite com IDs do tipo Long.
 * </p>
 */
@Converter(autoApply = true)
public class LongToIntegerConverter implements AttributeConverter<Long, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Long attribute) {
        return attribute != null ? attribute.intValue() : null;
    }

    @Override
    public Long convertToEntityAttribute(Integer dbData) {
        return dbData != null ? dbData.longValue() : null;
    }
}
