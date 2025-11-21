package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.response.TelemetriaResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.TelemetriaResponseMapper;
import br.com.mattos.simuladorinvestimento.domain.model.Telemetria;
import br.com.mattos.simuladorinvestimento.domain.service.TelemetriaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Recurso REST responsável por expor endpoints de telemetria dos serviços.
 */
@Path("/telemetria")
@Consumes("application/json")
@Produces("application/json")
@Tag(name = "Telemetria", description = "Endpoints relacionados a Telemetria")
public class TelemetriaResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelemetriaResource.class);

    @Inject
    TelemetriaService telemetriaService;

    @Inject
    TelemetriaResponseMapper mapper;

    /**
     * Endpoint para consultar telemetria agregada de serviços no período informado.
     *
     * @param dataInicio data inicial do período no formato yyyy-MM-dd
     * @param dataFim    data final do período no formato yyyy-MM-dd
     * @return DTO {@link TelemetriaResponseDTO} contendo lista de serviços com métricas
     */
    @GET
    @Operation(summary = "Consultar Telemetria", description = "Consulta a Telemetria dos servicos pela data informado ou 30 dias do mes atual")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Telemetria retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = TelemetriaResponseDTO.class))),
            @APIResponse(responseCode = "400", description = "Data inválida"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public TelemetriaResponseDTO consultarTelemetria(
            @Parameter(description = "Data inicial no formato yyyy-MM-dd", required = false) @QueryParam("inicio") String dataInicio,
            @Parameter(description = "Data final no formato yyyy-MM-dd", required = false) @QueryParam("fim") String dataFim
    ) {
        LOGGER.info("Consultando telemetria de serviços de {} até {}", dataInicio, dataFim);
        List<Telemetria> resultados = telemetriaService.consultarTelemetriaServicos(dataInicio, dataFim);

        LocalDate dataInicioConsulta;
        LocalDate dataFimConsulta;
        if (dataInicio != null && !dataInicio.isBlank()) {
            dataInicioConsulta = LocalDate.parse(dataInicio);
        } else {
            dataInicioConsulta = LocalDate.now().withDayOfMonth(1);
        }

        if (dataFim != null && !dataFim.isBlank()) {
            dataFimConsulta = LocalDate.parse(dataFim);
        } else {
            dataFimConsulta = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        }

        return mapper.toResponse(resultados, dataInicioConsulta.toString(), dataFimConsulta.toString());
    }
}
