package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.response.ClientePerfilRiscoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.ClientePerfilRiscoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.domain.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Recurso REST responsável por expor endpoints de clientes.
 */
@Path("/")
@Consumes("application/json")
@Produces("application/json")
@Tag(name = "Cliente", description = "Endpoints relacionados a Cliente")
public class ClienteResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteResource.class);

    @Inject
    ClienteService clienteService;

    @Inject
    ClientePerfilRiscoMapper mapper;

    /**
     * Endpoint para consultar o perfil de risco de um cliente pelo ID.
     *
     * @param clienteId ID do cliente
     * @return DTO {@link ClientePerfilRiscoResponseDTO} com nome, pontuação e descrição do perfil
     */
    @GET
    @Path("/perfil-risco/{clienteId}")
    @Operation(summary = "Consultar Perfil de Risco", description = "Consulta Cliente por id do cliente e retorna o perfil de risco do cliente")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Perfil de risco encontrado",
                    content = @Content(schema = @Schema(implementation = ClientePerfilRiscoResponseDTO.class))),
            @APIResponse(responseCode = "400", description = "Nenhum perfil de risco encontrado para o cliente"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ClientePerfilRiscoResponseDTO consultarPerfilRisco(@PathParam("clienteId") Long clienteId) {
        LOGGER.info("Consultando perfil de risco do cliente ID: {}", clienteId);
        ClientePerfilRisco clientePerfilRisco = clienteService.consultarPerfilRiscoCliente(clienteId);
        return mapper.toResponse(clientePerfilRisco);
    }
}
