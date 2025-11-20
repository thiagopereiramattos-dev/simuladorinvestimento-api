package br.com.mattos.simuladorinvestimento.domain.repository;

import br.com.mattos.simuladorinvestimento.domain.model.Cliente;
import br.com.mattos.simuladorinvestimento.domain.model.ClienteLogin;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface do repositório de telemetria.
 * <p>
 * Define operações de consulta para dados agregados de serviços.
 * </p>
 */
public interface TelemetriaRepository {
    /**
     * Retorna telemetria agregada por serviço no período especificado.
     *
     * @param dataInicio início do período (inclusive)
     * @param dataFim    fim do período (inclusive)
     * @return lista de registros de {@link Telemetria}
     */
    List<Telemetria> obterTelemetriaServicos(LocalDate dataInicio, LocalDate dataFim);

    /**
     * Persiste a entidade de telemetria no banco.
     */
    void salvarTelemetria( String momeMetodoFull, String momeMetodo, Integer tempoServicoMs, LocalDate data);

}