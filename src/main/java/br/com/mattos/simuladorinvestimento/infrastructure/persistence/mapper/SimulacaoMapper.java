package br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper;

import br.com.mattos.simuladorinvestimento.domain.model.ResultadoConsultaSimulacaoPorDia;
import br.com.mattos.simuladorinvestimento.domain.model.ResultadoSimulacao;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ClienteEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

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
    ProdutoMapper produtoMapper; // Injetando o mapper de Produto

    /**
     * Converte um objeto de domínio {@link SimulacaoInvestimento} em uma entidade {@link SimulacaoInvestimentoEntity}.
     *
     * @param simulacao objeto de domínio a ser convertido
     * @return entidade pronta para persistência
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

        double valorFinal = simulacao.resultado().valorFinal();
        double rentabilidade = simulacao.resultado().rentabilidadeEfetiva();
        entity.setValorInvestido(simulacao.resultado().valorInvestido());
        entity.setValorFinal(valorFinal);
        entity.setRentabilidade(rentabilidade);
        entity.setPrazo(simulacao.resultado().prazoMeses());
        entity.setDataSimulacao(simulacao.dataSimulacao());

        entity.setDataSimulacaoTexto(
                simulacao.dataSimulacao()
                        .atZone(ZoneId.of("America/Sao_Paulo"))
                        .toLocalDateTime()
                        .toString()
        );

        return entity;
    }

    /**
     * Converte uma entidade {@link SimulacaoInvestimentoEntity} em um objeto de domínio {@link SimulacaoInvestimento}.
     *
     * @param entity entidade a ser convertida
     * @return objeto de domínio equivalente
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
     * Converte a linha da consulta JPQL (Object[]) para {@link ResultadoConsultaSimulacaoPorDia}.
     *
     * @param row Array contendo [nomeProduto, dataSimulacao (Instant), quantidade, mediaValorFinal]
     * @return objeto {@link ResultadoConsultaSimulacaoPorDia}
     */
//    public ResultadoConsultaSimulacaoPorDia toResultadoConsultaPorDia(Object[] row) {
//        String produto = (String) row[0];
//        java.sql.Date dataSimulacao = (java.sql.Date) row[1]; // agora é Date
//        Long quantidade = (Long) row[2];
//        Double mediaValorFinal = (Double) row[3];
//
//        return new ResultadoConsultaSimulacaoPorDia(
//                produto,
//                dataSimulacao.toLocalDate(),
//                quantidade,
//                mediaValorFinal
//        );
//    }

    public ResultadoConsultaSimulacaoPorDia toResultadoConsultaPorDia(Object[] row) {

        String nomeProduto = (String) row[0];
        String dataString = (String) row[1];      // VEM DO strftime → yyyy-MM-dd
        Long quantidade = ((Number) row[2]).longValue();
        Double mediaValorFinal = ((Number) row[3]).doubleValue();

        return new ResultadoConsultaSimulacaoPorDia(
                nomeProduto,
                LocalDate.parse(dataString),        // converte a string para data
                quantidade,
                mediaValorFinal
        );
    }
}
