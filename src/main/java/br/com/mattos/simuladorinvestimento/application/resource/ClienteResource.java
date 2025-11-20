package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.response.ClientePerfilRiscoResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.ClientePerfilRiscoMapper;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import br.com.mattos.simuladorinvestimento.domain.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Recurso REST responsável por expor endpoints de clientes.
 */
@Path("/")
@Consumes("application/json")
@Produces("application/json")
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
    public ClientePerfilRiscoResponseDTO consultarPerfilRisco(@PathParam("clienteId") Long clienteId) {
        LOGGER.info("Consultando perfil de risco do cliente ID: {}", clienteId);
        ClientePerfilRisco clientePerfilRisco = clienteService.consultarPerfilRiscoCliente(clienteId);
        return mapper.toResponse(clientePerfilRisco);
    }
}
