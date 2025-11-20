package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.request.SimularInvestimentoRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimulacaoInvestimentoClienteDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimulacaoListResponseDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimulacaoPorProdutoDiaResponseDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.SimularInvestimentoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.SimulacaoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoEntrada;
import br.com.mattos.simuladorinvestimento.domain.model.SimulacaoInvestimento;
import br.com.mattos.simuladorinvestimento.domain.service.SimulacaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
@Tag(name = "Simulacao Investimento", description = "Endpoints relacionados a Simulação")
public class SimulacaoInvestimentoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulacaoInvestimentoResource.class);

    @Inject
    SimulacaoService simulacaoService;

    @Inject
    SimulacaoMapper mapper;

    /**
     * Endpoint para simular um investimento.
     *
     * @param request {@link SimularInvestimentoRequestDTO} DTO com os dados da simulação (cliente, valor, prazo e tipo de produto)
     * @return {@link SimularInvestimentoResponseDTO} DTO com os resultados da simulação
     */
    @POST
    @Path("/simular-investimento")
    @Operation(summary = "Simular Investimento", description = "Simula um investimento para o cliente com base no valor, prazo e tipo de produto")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Simulação realizada com sucesso",
                    content = @Content(schema = @Schema(implementation = SimularInvestimentoResponseDTO.class))),
            @APIResponse(responseCode = "400", description = "Tipo de Produtonão encontrado"),
            @APIResponse(responseCode = "400", description = "Não existe cliente com esse id"),
            @APIResponse(responseCode = "400", description = "Erros de validação"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public SimularInvestimentoResponseDTO simular(
            @Parameter(description = "DTO com dados da simulação", required = true)
            @Valid SimularInvestimentoRequestDTO request) {
        LOGGER.info("Requisição recebida: simular investimento");
        SimulacaoEntrada dominio = mapper.toDomain(request);
        SimulacaoInvestimento simulacao = simulacaoService.simular(dominio);
        LOGGER.info("Simulação realizada com sucesso: clienteId={} produto={} valorFinal={}",
                simulacao.clientId(),
                simulacao.produto().nome(),
                simulacao.resultado().valorFinal()
        );
        return mapper.toResponse(simulacao);
    }

    /**
     * Endpoint para listar todas as simulações realizadas.
     *
     * @return Lista de {@link SimulacaoListResponseDTO} com informações resumidas das simulações
     */
    @GET
    @Path("/simulacoes")
    @Operation(summary = "Listar Simulações", description = "Lista todas as simulações realizadas")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de simulações",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SimulacaoListResponseDTO.class)
                    )),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<SimulacaoListResponseDTO> listar() {
        LOGGER.info("Requisição recebida: listar simulações");
        List<SimulacaoInvestimento> simulacoes = simulacaoService.listarSimulacoes();
        LOGGER.info("Retornando {} simulações", simulacoes.size());
        return mapper.toListResponseList(simulacoes);
    }

    /**
     * Endpoint para listar simulações agregadas por produto e dia.
     *
     * @return Lista de {@link SimulacaoPorProdutoDiaResponseDTO} com quantidade e média de valor final
     */
    @GET
    @Path("/simulacoes/por-produto-dia")
    @Operation(summary = "Listar Simulações por Produto e Dia", description = "Lista simulações agregadas por produto e dia, mostrando quantidade e média de valor final")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de simulações agregadas",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SimulacaoPorProdutoDiaResponseDTO.class)
                    )),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<SimulacaoPorProdutoDiaResponseDTO> listarPorProdutoEDia() {
        LOGGER.info("Requisição recebida: listar simulações por produto e dia");

        var resultados = simulacaoService.listarSimulacoesPorProdutoEDia();
        LOGGER.info("Retornando {} registros agregados por produto e dia", resultados.size());

        return resultados.stream()
                .map(mapper::toResponsePorProdutoDia)
                .toList();
    }

    /**
     * Endpoint para listar todas as simulações realizadas.
     *
     * @return Lista de {@link SimulacaoListResponseDTO} com informações resumidas das simulações
     */
    @GET
    @Path("/investimentos/{clienteId}")
    @Operation(summary = "Listar Investimentos por Cliente", description = "Lista todas as simulações realizadas por um cliente específico")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de simulações do cliente",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SimulacaoInvestimentoClienteDTO.class)
                    )),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<SimulacaoInvestimentoClienteDTO> listarSimulacoesPorIdCliente(
            @Parameter(description = "ID do cliente para listar simulações", required = true)
            @PathParam("clienteId") Long idCliente) {
        LOGGER.info("Requisição recebida: listar simulações");
        List<SimulacaoInvestimento> simulacoes = simulacaoService.listarSimulacoesPorCliente(idCliente);
        LOGGER.info("Retornando {} simulações", simulacoes.size());
        return mapper.toResponseSimulacaoClienteList(simulacoes);
    }
}
