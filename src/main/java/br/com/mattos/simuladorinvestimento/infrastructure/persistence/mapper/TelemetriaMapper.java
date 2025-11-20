package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.TelemetriaServicoEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;

/**
 * Mapper responsável por converter {@link TelemetriaServicoEntity} em {@link Telemetria}.
 * <p>
 * Centraliza a conversão entre a entidade JPA e o record do domínio
 * </p>
 */
@ApplicationScoped
public class TelemetriaMapper {


    /**
     * Converte uma linha da consulta agregada (Object[]) em {@link Telemetria}.
     * <p>
     * Este método é utilizado pelo repositório para transformar o resultado da JPQL
     * em um modelo fortemente tipado do domínio.
     * </p>
     *
     * Estrutura esperada do array:
     * <ul>
     *     <li>[0] - String: nome do serviço</li>
     *     <li>[1] - Integer: quantidade de chamadas</li>
     *     <li>[2] - Integer: média do tempo de resposta em ms</li>
     * </ul>
     *
     * @param row        linha retornada pela query JPQL
     * @param dataInicio início do período
     * @param dataFim    fim do período
     * @return instância de {@link Telemetria} preenchida
     */
    public Telemetria toDomain(Object[] row, LocalDate dataInicio, LocalDate dataFim) {

        String nomeServico = (String) row[0];
        Integer quantidadeChamadas = ((Long) row[1]).intValue();
        Integer mediaTempoRespostaMs = ((Double) row[2]).intValue();
        return new Telemetria(nomeServico, quantidadeChamadas, mediaTempoRespostaMs, dataInicio, dataFim);
    }
}
