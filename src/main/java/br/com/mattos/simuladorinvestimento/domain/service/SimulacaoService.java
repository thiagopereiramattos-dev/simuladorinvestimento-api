package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.model.ResultadoSimulacao;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoEntrada;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.domain.repository.SimulacaoInvestimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class SimulacaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulacaoService.class);

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    SimulacaoInvestimentoRepository simulacaoInvestimentoRepository;

    public SimulacaoInvestimento simular(SimulacaoEntrada entrada) {

        LOGGER.info("Iniciando simulação para clienteId={} e tipoProduto={}", entrada.clienteId(), entrada.tipoProduto());

        Produto produto = produtoRepository.buscarPorTipo(entrada.tipoProduto())
                .orElseThrow(() -> {
                    LOGGER.warn("Produto do tipo '{}' não encontrado para clienteId={}", entrada.tipoProduto(), entrada.clienteId());
                    return new ProdutoNaoEncontradoException(entrada.tipoProduto());
                });
        LOGGER.debug("Produto encontrado: id={}, nome={}, rentabilidade={}", produto.id(), produto.nome(), produto.rentabilidade());

        Double valorFinal = entrada.valor() * (1 + produto.rentabilidade());

        ResultadoSimulacao resultado = new ResultadoSimulacao(
                valorFinal,
                produto.rentabilidade(),
                entrada.prazoMeses()
        );

        LOGGER.info("Resultado da simulação calculado: valorFinal={}, rentabilidade={}, prazoMeses={}", resultado.valorFinal(), resultado.rentabilidadeEfetiva(), resultado.prazoMeses());

        SimulacaoInvestimento simulacao = new SimulacaoInvestimento(
                null, //id vai ger gerado na simulacao salvar
                entrada.clienteId(),
                produto,
                resultado,
                Instant.now()
        );

        simulacaoInvestimentoRepository.salvar(simulacao);
        LOGGER.info("Simulação persistida com sucesso para clienteId={} e produto={}", entrada.clienteId(), produto.nome());
        return simulacao;
    }

    public List<SimulacaoInvestimento> listarSimulacoes() {
        return simulacaoInvestimentoRepository.listar();
    }
}
