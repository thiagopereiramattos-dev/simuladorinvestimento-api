package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.SimulacaoInvestimentoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repositório Panache para acesso direto à entidade {@link SimulacaoInvestimentoEntity}.
 * <p>
 * Usado para operações básicas de persistência, como listar e salvar simulações.
 * </p>
 */
@ApplicationScoped
public class SimulacaoPanacheRepository implements PanacheRepository<SimulacaoInvestimentoEntity> {
}
