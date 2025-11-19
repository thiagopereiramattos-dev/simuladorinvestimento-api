package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serviço responsável pelas operações de consulta de produtos.
 */
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
    public List<Produto> listarTodos() {

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
}
