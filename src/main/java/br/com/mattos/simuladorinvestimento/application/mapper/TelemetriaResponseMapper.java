package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.response.ServicoTelemetriaDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.TelemetriaResponseDTO;
import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper responsável por converter lista de {@link Telemetria} (domínio)
 * para {@link TelemetriaResponseDTO} (API).
 */
@ApplicationScoped
public class TelemetriaResponseMapper {

    /**
     * Converte lista de registros de telemetria do domínio em DTO de resposta.
     *
     * @param resultados lista de {@link Telemetria}
     * @param dataInicio data inicial do período
     * @param dataFim    data final do período
     * @return DTO de resposta para API
     */
    public TelemetriaResponseDTO toResponse(List<Telemetria> resultados, LocalDate dataInicio, LocalDate dataFim) {

        List<ServicoTelemetriaDTO> servicos = new ArrayList<>();
        for (Telemetria t : resultados) {
            ServicoTelemetriaDTO servicoDTO = new ServicoTelemetriaDTO(
                    t.nome(),
                    t.quantidadeChamadas(),
                    t.mediaTempoRespostaMs()
            );
            servicos.add(servicoDTO);
        }

        return new TelemetriaResponseDTO(servicos, dataInicio, dataFim);
    }
}
