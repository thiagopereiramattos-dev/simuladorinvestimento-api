package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProdutoPanacheRepository implements PanacheRepository<ProdutoEntity> {

    public Optional<ProdutoEntity> findByTipoNome(String tipoNome) {
        return find("tipo.nome", tipoNome).firstResultOptional();
    }
}
