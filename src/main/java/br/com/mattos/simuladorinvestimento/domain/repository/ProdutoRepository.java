package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    Produto buscarPorTipo(String tipoProduto);
    List<Produto> listarTodos();
}
