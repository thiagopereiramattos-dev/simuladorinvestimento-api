package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.*;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class SimulacaoService {

    @Inject
    ProdutoRepository produtoRepository;

    public Simulacao simular(SimulacaoEntrada entrada) {

        Produto produto = produtoRepository.buscarPorTipo(entrada.tipoProduto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(entrada.tipoProduto()));

        Double valorFinal = entrada.valor() * (1 + produto.rentabilidade());

        ResultadoSimulacao resultado = new ResultadoSimulacao(
                valorFinal,
                produto.rentabilidade(),
                entrada.prazoMeses()
        );

        return new Simulacao(produto, resultado);
    }
}
