package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.ClienteNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.*;
import br.com.mattos.simuladorinvestimento.domain.repository.ClienteRepository;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.domain.repository.SimulacaoInvestimentoRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.telemetria.TelemetriaMonitor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

/**
 * Serviço responsável pela execução e consulta de simulações de investimento.
 */
@TelemetriaMonitor
@ApplicationScoped
public class SimulacaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulacaoService.class);

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    SimulacaoInvestimentoRepository simulacaoInvestimentoRepository;

    /**
     * Realiza uma simulação de investimento utilizando os dados informados.
     * Busca o produto associado, calcula o valor final e persiste a simulação.
     *
     * @param entrada dados da simulação informados pelo cliente.
     * @return {@link SimulacaoInvestimento} contendo o resultado gerado.
     * @throws ProdutoNaoEncontradoException caso o tipo de produto informado não exista.
     */
    public SimulacaoInvestimento simular(SimulacaoEntrada entrada) {

        LOGGER.info("Iniciando simulação para clienteId={} e tipoProduto={}", entrada.clienteId(), entrada.tipoProduto());
        try {

            Produto produto = produtoRepository.buscarPorTipo(entrada.tipoProduto())
                    .orElseThrow(() -> {
                        LOGGER.warn("Produto do tipo '{}' não encontrado ", entrada.tipoProduto());
                        return new ProdutoNaoEncontradoException(entrada.tipoProduto());
                    });
            LOGGER.debug("Produto encontrado: id={}, nome={}, rentabilidade={}", produto.id(), produto.nome(), produto.rentabilidade());

            Cliente cliente = clienteRepository.buscarPorId(entrada.clienteId())
                    .orElseThrow(() -> {
                        LOGGER.warn("CLiente '{}' não encontrado ", entrada.clienteId());
                        return new ClienteNaoEncontradoException("Não existe cliente com esse id: " + entrada.clienteId());
                    });
            LOGGER.debug("Cliente encontrado: id={}, nome={}", cliente.id(), cliente.nome());

            Double valorFinal = entrada.valor() * (1 + produto.rentabilidade());

            ResultadoSimulacao resultado = new ResultadoSimulacao(
                    entrada.valor(),
                    valorFinal,
                    produto.rentabilidade(),
                    entrada.prazoMeses()
            );

            LOGGER.info("Resultado da simulação calculado: valorFinal={}, rentabilidade={}, prazoMeses={}",
                    resultado.valorFinal(),
                    resultado.rentabilidadeEfetiva(),
                    resultado.prazoMeses());

            SimulacaoInvestimento simulacao = new SimulacaoInvestimento(
                    entrada.clienteId(),
                    produto,
                    resultado
            );

            simulacaoInvestimentoRepository.salvar(simulacao);
            LOGGER.info("Simulação persistida com sucesso para clienteId={} e produto={}", entrada.clienteId(), produto.nome());

            return simulacao;

        } catch (ProdutoNaoEncontradoException | ClienteNaoEncontradoException exNotFound) {
            throw exNotFound;
        } catch (Exception ex) {
            LOGGER.error("Erro ao simular investimento para clienteId={}", entrada.clienteId(), ex);
            throw new RuntimeException("Não foi possível realizar a simulação. Ocorreu um erro interno.", ex);
        }
    }

    /**
     * Retorna todas as simulações já realizadas.
     *
     * @return lista de {@link SimulacaoInvestimento}; pode estar vazia.
     */
    @TelemetriaMonitor
    public List<SimulacaoInvestimento> listarSimulacoes() {

        LOGGER.debug("Iniciando consulta de todas as simulações");
        try {
            List<SimulacaoInvestimento> lista = simulacaoInvestimentoRepository.listar();
            LOGGER.debug("Total de simulações retornadas: {}", lista.size());
            return lista;
        } catch (Exception ex) {
            LOGGER.error("Erro ao listar simulações", ex);
            throw new RuntimeException("Não foi possível listar as simulações. Ocorreu um erro interno.", ex);
        }
    }

    /**
     * Retorna as simulações agregadas por produto e dia, incluindo quantidade de simulações
     * e média do valor final no período.
     *
     * @return lista de {@link ResultadoConsultaSimulacaoPorDia}; pode estar vazia.
     */
    public List<ResultadoConsultaSimulacaoPorDia> listarSimulacoesPorProdutoEDia() {

        LOGGER.debug("Iniciando consulta de simulações agregadas por produto e dia");
        try {
            List<ResultadoConsultaSimulacaoPorDia> resultados =
                    simulacaoInvestimentoRepository.listarPorProdutoEDia();

            LOGGER.info("Consulta concluída com sucesso. Registros encontrados: {}", resultados.size());
            return resultados;
        } catch (Exception ex) {
            LOGGER.error("Erro ao consultar simulações por produto e dia", ex);
            throw new RuntimeException("Erro interno ao consultar simulações.", ex);
        }
    }
}
