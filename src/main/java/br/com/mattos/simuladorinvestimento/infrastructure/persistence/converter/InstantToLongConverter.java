package br.com.mattos.simuladorinvestimento.infrastructure.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;

/**
 * Conversor JPA que transforma objetos {@link Instant} em {@link Long} para persistência no banco de dados e vice-versa.
 * <p>
 * Este conversor armazena a data/hora como milissegundos desde a época (Epoch).
 * A anotação {@code @Converter(autoApply = true)} garante que ele seja aplicado
 * automaticamente a todos os atributos do tipo {@link Instant} nas entidades JPA.
 * </p>
 */
@Converter(autoApply = true)
public class InstantToLongConverter implements AttributeConverter<Instant, Long> {

    /**
     * Converte um {@link Instant} em {@link Long} para armazenar no banco de dados.
     *
     * @param attribute o valor {@link Instant} da entidade
     * @return o valor em milissegundos desde a época, ou {@code null} se o atributo for {@code null}
     */
    @Override
    public Long convertToDatabaseColumn(Instant attribute) {
        return (attribute == null) ? null : attribute.toEpochMilli();
    }

    /**
     * Converte um valor {@link Long} do banco de dados para {@link Instant}.
     *
     * @param dbData o valor em milissegundos armazenado no banco
     * @return um {@link Instant} correspondente, ou {@code null} se dbData for {@code null}
     */
    @Override
    public Instant convertToEntityAttribute(Long dbData) {
        return (dbData == null) ? null : Instant.ofEpochMilli(dbData);
    }
}
