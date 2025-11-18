package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

/**
 * Repositório Panache para acesso direto à entidade {@link ProdutoEntity}.
 * <p>
 * Fornece métodos de consulta simples baseados em PanacheRepository.
 * </p>
 */
@ApplicationScoped
public class ProdutoPanacheRepository implements PanacheRepository<ProdutoEntity> {

    /**
     * Busca um produto pelo nome do tipo.
     *
     * @param tipoNome nome do tipo do produto
     * @return {@link Optional} com a entidade encontrada, ou vazio se não houver correspondência
     */
    public Optional<ProdutoEntity> findByTipoNome(String tipoNome) {
        return find("tipo.nome", tipoNome).firstResultOptional();
    }
}
