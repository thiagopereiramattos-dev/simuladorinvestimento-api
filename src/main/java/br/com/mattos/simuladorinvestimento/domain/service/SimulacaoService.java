package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.*;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.domain.repository.SimulacaoInvestimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

/**
 * Serviço responsável pela simulação de investimentos.
 * <p>
 * Realiza cálculos de simulação, persiste resultados e consulta a listaa de simulações existentes.
 * </p>
 */
@ApplicationScoped
public class SimulacaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulacaoService.class);

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    SimulacaoInvestimentoRepository simulacaoInvestimentoRepository;

    /**
     * Realiza uma simulação de investimento para um cliente.
     * <p>
     * Busca o produto pelo tipo informado na entrada da simulação,
     * calcula o valor final baseado na rentabilidade do produto e cria
     * um objeto {@link SimulacaoInvestimento}, que será persistido no repositório.
     * </p>
     *
     * @param entrada dados da simulação fornecidos pelo {@link SimulacaoEntrada}
     * @return um objeto {@link SimulacaoInvestimento} com o resultado da simulação
     * @throws ProdutoNaoEncontradoException caso não exista produto do tipo informado
     * @throws RuntimeException caso ocorra algum problema ao persistir a simulação
     */
    public SimulacaoInvestimento simular(SimulacaoEntrada entrada) {

        LOGGER.info("Iniciando simulação para clienteId={} e tipoProduto={}", entrada.clienteId(), entrada.tipoProduto());
        try {

            Produto produto = produtoRepository.buscarPorTipo(entrada.tipoProduto())
                    .orElseThrow(() -> {
                        LOGGER.warn("Produto do tipo '{}' não encontrado para clienteId={}", entrada.tipoProduto(), entrada.clienteId());
                        return new ProdutoNaoEncontradoException(entrada.tipoProduto());
                    });
            LOGGER.debug("Produto encontrado: id={}, nome={}, rentabilidade={}", produto.id(), produto.nome(), produto.rentabilidade());

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
                    null, // id será gerado ao persistir
                    entrada.clienteId(),
                    produto,
                    resultado,
                    Instant.now()
            );

            simulacaoInvestimentoRepository.salvar(simulacao);
            LOGGER.info("Simulação persistida com sucesso para clienteId={} e produto={}", entrada.clienteId(), produto.nome());

            return simulacao;

        } catch (ProdutoNaoEncontradoException exNaoEncontrado) {
            throw exNaoEncontrado;
        } catch (Exception ex) {
            LOGGER.error("Erro ao simular investimento para clienteId={}", entrada.clienteId(), ex);
            throw new RuntimeException("Não foi possível realizar a simulação. Ocorreu um erro interno.", ex);
        }
    }

    /**
     * Lista todas as simulações de investimento realizadas.
     *
     * @return lista de {@link SimulacaoInvestimento} contendo todas as simulações persistidas
     * @throws RuntimeException caso ocorra algum problema ao acessar o repositório
     */
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
     * Lista as simulações agrupadas por produto e dia, retornando a quantidade de simulações e a média do valor final para cada agrupamento.
     *
     * @return Lista de {@link ResultadoConsultaSimulacaoPorDia} contendo os dados agregados,
     *  pode ir  vazia se não houver registros.
     *
     * @throws RuntimeException se ocorrer algum erro inesperado ao acessar o repositório
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
