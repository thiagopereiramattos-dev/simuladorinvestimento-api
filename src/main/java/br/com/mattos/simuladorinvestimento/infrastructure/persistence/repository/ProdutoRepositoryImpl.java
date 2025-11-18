package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Produto;
import br.com.mattos.simuladorinvestimento.domain.repository.ProdutoRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper.ProdutoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do repositório {@link ProdutoRepository} usando PanacheRepository
 * e {@link ProdutoMapper} para conversão entre entidades e objetos de domínio.
 */
@ApplicationScoped
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @Inject
    ProdutoPanacheRepository panacheRepo;

    @Inject
    ProdutoMapper mapper;

    /**
     * Busca um produto pelo tipo.
     *
     * @param tipoProduto nome do tipo do produto
     * @return {@link Optional} com o objeto de domínio encontrado, ou vazio se não houver correspondência
     */
    @Override
    public Optional<Produto> buscarPorTipo(String tipoProduto) {
        return panacheRepo.findByTipoNome(tipoProduto)
                .map(mapper::toDomain);
    }

    /**
     * Lista todos os produtos disponíveis.
     *
     * @return lista de produtos no domínio
     */
    @Override
    public List<Produto> listarTodos() {
        return panacheRepo.listAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
