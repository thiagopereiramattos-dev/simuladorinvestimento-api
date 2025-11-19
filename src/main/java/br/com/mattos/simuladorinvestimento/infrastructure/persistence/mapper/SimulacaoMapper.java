package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.model.ResultadoConsultaSimulacaoPorDia;
import br.com.mattos.simuladorinvestimento.domain.model.ResultadoSimulacao;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ClienteEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.util.DateConverter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Mapper responsável por converter entre a entidade {@link SimulacaoInvestimentoEntity}
 * e o objeto de domínio {@link SimulacaoInvestimento}.
 * <p>
 * Também utiliza {@link ProdutoMapper} para conversão de produtos relacionados.
 * </p>
 */
@ApplicationScoped
public class SimulacaoMapper {

    @Inject
    ProdutoMapper produtoMapper;

    /**
     * Converte um objeto de domínio {@link SimulacaoInvestimento} para a entidade
     * {@link SimulacaoInvestimentoEntity}, incluindo o preenchimento dos campos:
     * <ul>
     *     <li>{@code dataSimulacao} (Instant)</li>
     *     <li>{@code dataSimulacaoTexto} (String formatada)</li>
     *     <li>{@code dataSimulacaoDate} (LocalDate para relatórios)</li>
     * </ul>
     *
     * @param simulacao objeto de domínio contendo os dados da simulação
     * @return entidade preenchida e pronta para persistência
     */

    public SimulacaoInvestimentoEntity toEntity(SimulacaoInvestimento simulacao) {

        SimulacaoInvestimentoEntity entity = new SimulacaoInvestimentoEntity();

        var produtoEntity = produtoMapper.toEntity(simulacao.produto());
        entity.setProduto(produtoEntity);

        ClienteEntity cliente = new ClienteEntity();;
        if (simulacao.clientId() != null) {
            cliente.setId(simulacao.clientId());
        }
        entity.setCliente(cliente);

        entity.setValorInvestido(simulacao.resultado().valorInvestido());
        entity.setValorFinal(simulacao.resultado().valorFinal());
        entity.setRentabilidade(simulacao.resultado().rentabilidadeEfetiva());
        entity.setPrazo(simulacao.resultado().prazoMeses());

        Instant instant = simulacao.dataSimulacao();
        entity.setDataSimulacao(instant);
        entity.setDataSimulacaoDate(DateConverter.toLocalDate(instant));
        entity.setDataSimulacaoTexto(DateConverter.toDateTimeText(instant));

        return entity;
    }

    /**
     * Converte uma entidade {@link SimulacaoInvestimentoEntity} em um objeto de domínio {@link SimulacaoInvestimento}.
     *
     * @param entity entidade a ser convertida
     * @return objeto {@link SimulacaoInvestimento} domínio preenchido
     */
    public SimulacaoInvestimento toDomain(SimulacaoInvestimentoEntity entity) {
        return new SimulacaoInvestimento(
                entity.getId(),
                entity.getCliente().getId(),
                produtoMapper.toDomain(entity.getProduto()),
                new ResultadoSimulacao(
                        entity.getValorInvestido(),
                        entity.getValorFinal(),
                        entity.getRentabilidade(),
                        entity.getPrazo()
                ),
                entity.getDataSimulacao()
        );
    }

    /**
     * Converte uma linha da consulta agregada (Object[]) para o objeto de domínio {@link ResultadoConsultaSimulacaoPorDia}.
     * Este método é utilizado pelo repositório para transformar o resultado da JPQL em um modelo fortemente tipado.
     *
     * Estrutura esperada do array:
     * <ul>
     *     <li>[0] - String: nome do produto</li>
     *     <li>[1] - LocalDate: data da simulação (campo dataSimulacaoDate)</li>
     *     <li>[2] - Number: quantidade de simulações</li>
     *     <li>[3] - Number: média do valor final</li>
     * </ul>
     *
     * @param row linha retornada pela query JPQL
     * @return instância de {@link ResultadoConsultaSimulacaoPorDia}
     */
    public ResultadoConsultaSimulacaoPorDia toResultadoConsultaPorDia(Object[] row) {

        String nomeProduto = (String) row[0];
        LocalDate data = (LocalDate) row[1];
        Long quantidade = ((Number) row[2]).longValue();
        Double mediaValorFinal = ((Number) row[3]).doubleValue();

        return new ResultadoConsultaSimulacaoPorDia(
                nomeProduto,
                data,
                quantidade,
                mediaValorFinal
        );
    }
}
