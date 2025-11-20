package br.com.mattos.simuladorinvestimento.domain.service;

import br.com.mattos.simuladorinvestimento.domain.exception.DataInvalidoException;
import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;
import br.com.mattos.simuladorinvestimento.domain.repository.TelemetriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
     * @param dtInicio data inicio
     * @param dtFim    data fim
     * @return lista de {@link Telemetria} contendo nome do serviço, quantidade de chamadas
     *         e média do tempo de resposta
     * @throws RuntimeException caso ocorra algum erro durante a consulta
     */
    public List<Telemetria> consultarTelemetriaServicos(String dtInicio, String dtFim) {
        LOGGER.debug("Iniciando consulta de telemetria dos serviços de {} até {}", dtInicio, dtFim);

        LocalDate dataInicio;
        LocalDate dataFim;

        try {

            if (dtInicio != null && !dtInicio.isBlank()) {
                dataInicio = LocalDate.parse(dtInicio);
            } else {
                dataInicio = LocalDate.now().withDayOfMonth(1);
            }

            if (dtFim != null && !dtFim.isBlank()) {
                dataFim = LocalDate.parse(dtFim);
            } else {
                dataFim = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
            }

            List<Telemetria> resultado = telemetriaRepository.obterTelemetriaServicos(dataInicio, dataFim);
            LOGGER.debug("Consulta de telemetria concluída com {} registros", resultado.size());
            return resultado;
        } catch (DateTimeParseException e) {
            throw new DataInvalidoException();
        }catch (Exception ex) {
            LOGGER.error("Erro ao consultar telemetria entre {} e {}", dtInicio, dtFim, ex);
            throw new RuntimeException("Não foi possível consultar a telemetria dos serviços. Ocorreu um erro interno.", ex);
        }
    }
}
