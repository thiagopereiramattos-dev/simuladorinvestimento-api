package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;
import br.com.mattos.simuladorinvestimento.domain.repository.TelemetriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

/**
 * Serviço responsável pelas operações de telemetria.
 * <p>
 * Centraliza a lógica de consulta de métricas agregadas dos serviços,
 * delegando a persistência e query ao {@link TelemetriaRepository}.
 * </p>
 */
@ApplicationScoped
public class TelemetriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelemetriaService.class);

    @Inject
    TelemetriaRepository telemetriaRepository;


    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void salvar(String nomeMetodoFull, String nomeMetodo, Integer tempoServicoMs, LocalDate data) {
        telemetriaRepository.salvarTelemetria(nomeMetodoFull, nomeMetodo, tempoServicoMs, data);
    }

    /**
     * Consulta a telemetria de serviços dentro do período informado.
     *
     * @param dataInicio início do período
     * @param dataFim    fim do período
     * @return lista de {@link Telemetria} contendo nome do serviço, quantidade de chamadas
     *         e média do tempo de resposta
     * @throws RuntimeException caso ocorra algum erro durante a consulta
     */
    public List<Telemetria> consultarTelemetriaServicos(LocalDate dataInicio, LocalDate dataFim) {
        LOGGER.debug("Iniciando consulta de telemetria dos serviços de {} até {}", dataInicio, dataFim);

        try {
            List<Telemetria> resultado = telemetriaRepository.obterTelemetriaServicos(dataInicio, dataFim);
            LOGGER.debug("Consulta de telemetria concluída com {} registros", resultado.size());
            return resultado;
        } catch (Exception ex) {
            LOGGER.error("Erro ao consultar telemetria entre {} e {}", dataInicio, dataFim, ex);
            throw new RuntimeException("Não foi possível consultar a telemetria dos serviços. Ocorreu um erro interno.", ex);
        }
    }
}
