package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.ResultadoConsultaSimulacaoPorDia;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;

import java.util.List;

/** Interface para persistência e consulta de simulações de investimento. */
public interface SimulacaoInvestimentoRepository {

    /**
     * Salva uma simulação de investimento.
     * @param simulacao simulação de investimento a ser salva
     */
    void salvar(SimulacaoInvestimento simulacao);

    /**
     * Lista todas as simulações registradas.
     * @return Lista de SimulacaoInvestimento contendo todas simulacoes
     */
    List<SimulacaoInvestimento> listar();

    /**
     * Lista todas as simulações registradas.
     * @return Lista de SimulacaoInvestimento contendo todas simulacoes
     */
    List<SimulacaoInvestimento> listarPorCliente(Long clientId);

    /**
     * Lista as simulações agrupadas por produto e dia,retornando quantidade de simulações e média do valor final.
     * @return Lista de ResultadoConsultaSimulacaoPorDia contendo informações agregadas por produto e dia
     */
    List<ResultadoConsultaSimulacaoPorDia> listarPorProdutoEDia();

}
