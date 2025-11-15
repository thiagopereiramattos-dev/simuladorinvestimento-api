package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;

public interface ProdutoRepository {
    Produto buscarPorTipo(String tipoProduto);
}
