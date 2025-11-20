package br.com.mattos.simuladorinvestimento.infrastructure.persistence.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;
import br.com.mattos.simuladorinvestimento.domain.repository.TelemetriaRepository;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.entity.TelemetriaServicoEntity;
import br.com.mattos.simuladorinvestimento.infrastructure.persistence.mapper.TelemetriaMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementação do repositório {@link TelemetriaRepository}.
 * <p>
 * Responsável por buscar dados de telemetria dos serviços, agregando informações
 * como quantidade de chamadas e média de tempo de resposta dentro de um período definido.
 * </p>
 */
@ApplicationScoped
public class TelemetriaRepositoryImpl implements TelemetriaRepository {

    @Inject
    EntityManager em;

    @Inject
    TelemetriaMapper mapper;

    @Override
    public void salvarTelemetria(String momeMetodoFull, String momeMetodo, Integer tempoServicoMs, LocalDate data) {
        TelemetriaServicoEntity entity = TelemetriaServicoEntity.builder()
                .nomeMeotodoFull(momeMetodoFull)
                .nomeMetodo(momeMetodo)
                .tempoRespostaMs(tempoServicoMs)
                .data(data)
                .build();

        em.persist(entity);
    }

    /**
     * Consulta telemetria agregada por serviço no período informado.
     * <p>
     * Utiliza JPQL com {@code COUNT} e {@code AVG} para gerar métricas agregadas.
     * </p>
     *
     * @param dataInicio início do período
     * @param dataFim    fim do período
     * @return lista de registros de {@link Telemetria}
     */
    @Override
    public List<Telemetria> obterTelemetriaServicos(LocalDate dataInicio, LocalDate dataFim) {

        String query = """
            SELECT r.nomeMetodo, COUNT(r), AVG(r.tempoRespostaMs)
            FROM TelemetriaServicoEntity r
            WHERE r.data BETWEEN :dataInicio AND :dataFim
            GROUP BY r.nomeMetodo
            """;

        List<Object[]> results = em.createQuery(query, Object[].class)
                .setParameter("dataInicio", dataInicio)
                .setParameter("dataFim", dataFim)
                .getResultList();

        return results.stream()
                .map(r -> mapper.toDomain(r, dataInicio, dataFim))
                .toList();
    }
}
