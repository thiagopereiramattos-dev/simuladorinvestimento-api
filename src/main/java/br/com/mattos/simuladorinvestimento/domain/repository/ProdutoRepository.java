package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.enums.RiscoProduto;
import br.com.mattos.simuladorinvestimento.domain.model.Produto;

import java.util.List;
import java.util.Optional;

/** Interface para operações de persistência e consulta de produtos. */
public interface ProdutoRepository {

    /** Busca um produto pelo seu tipo.
     * @param tipoProduto tipo do produto
     * @return um Optional com o produto, ou vazio se não encontrado
     */
    Optional<Produto> buscarPorTipo(String tipoProduto);

    /** Lista todos os produtos disponíveis. */
    List<Produto> listarTodos();

    List<Produto> listarPorRisco(RiscoProduto risco);
}
