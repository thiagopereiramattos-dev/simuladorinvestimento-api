package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.model.*;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SimulacaoService {

    @Inject
    ProdutoRepository produtoRepository;

    public Simulacao simular(SimulacaoEntrada entrada) {

        Produto produto = produtoRepository.buscarPorTipo(entrada.getTipoProduto());

        Double valorFinal = entrada.getValor() * (1 + produto.getRentabilidade());

        ResultadoSimulacao resultado = new ResultadoSimulacao(
                valorFinal,
                produto.getRentabilidade(),
                entrada.getPrazoMeses()
        );

        return new Simulacao(produto, resultado);
    }
}
