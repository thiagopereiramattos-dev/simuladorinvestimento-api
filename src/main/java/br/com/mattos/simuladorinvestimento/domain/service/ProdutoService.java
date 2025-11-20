package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import br.com.mattos.simuladorinvestimento.domain.exception.PerfilInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.exception.ProdutoNaoEncontradoException;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.telemetria.TelemetriaMonitor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serviço responsável pelas operações de consulta de produtos.
 */
@TelemetriaMonitor
@ApplicationScoped
public class ProdutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);

    @Inject
    ProdutoRepository produtoRepository;

    /**
     * Retorna todos os produtos cadastrados.
     *
     * @return lista de {@link Produto}; pode estar vazia caso não existam registros.
     */
    public List<Produto> listarProdutos() {

        LOGGER.debug("Iniciando consulta de todos os produtos");
        try {
            List<Produto> produtos = produtoRepository.listarTodos();
            LOGGER.debug("Total de produtos encontrados: {}", produtos.size());
            return produtos;

        } catch (Exception ex) {
            LOGGER.error("Erro ao listar produtos", ex);
            throw new RuntimeException("Não foi possível listar os produtos. Ocorreu um erro interno.", ex);
        }
    }

    /**
     * Retorna todos os produtos cadastrados para o perfil informado.
     *
     * @return lista de {@link Produto}; pode estar vazia caso não existam registros.
     */
    public List<Produto> listarProdutosRecomendados(String nomePerfilRisco) {

        LOGGER.debug("Iniciando consulta de produtos recomendados para o risco informado: " + nomePerfilRisco);
        try {
            RiscoProduto risco = RiscoProduto.fromString(nomePerfilRisco);
            List<Produto> produtos = produtoRepository.listarPorRisco(risco);
            LOGGER.debug("Total de produtos encontrados: {}", produtos.size());
            return produtos;

        } catch (Exception ex) {
            LOGGER.error("Erro ao listar produtos", ex);
            throw new RuntimeException("Não foi possível listar os produtos. Ocorreu um erro interno.", ex);
        }
    }

}
