package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ClienteEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.ProdutoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;
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
public class ClientePanacheRepository implements PanacheRepository<ClienteEntity> {

}

