package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ProdutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);

    @Inject
    ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        LOGGER.debug("Buscando todos os produtos no repositório");
        List<Produto> produtos = produtoRepository.listarTodos();
        LOGGER.info("Total de produtos encontrados: {}", produtos.size());
        return produtos;
    }
}
