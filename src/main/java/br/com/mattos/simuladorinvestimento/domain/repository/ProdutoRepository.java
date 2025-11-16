package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    Optional<Produto> buscarPorTipo(String tipoProduto);
    List<Produto> listarTodos();
}
