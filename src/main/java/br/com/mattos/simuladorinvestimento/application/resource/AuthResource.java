package br.com.mattos.simuladorinvestimento.application.resource;

import br.com.mattos.simuladorinvestimento.application.dto.request.LoginRequestDTO;
import br.com.mattos.simuladorinvestimento.application.dto.response.LoginResponseDTO;
import br.com.mattos.simuladorinvestimento.application.mapper.AuthMapper;
import br.com.mattos.simuladorinvestimento.domain.service.ClienteAuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Recurso REST responsável pela autenticação de clientes.
 *
 * <p>Expõe endpoints relacionados ao processo de login e emissão
 * de tokens JWT utilizados para autenticação e autorização
 * nas demais rotas da aplicação.</p>
 */
@Path("/autenticacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Autenticação", description = "Endpoints relacionados à autenticação de clientes")
public class AuthResource {

    @Inject
    ClienteAuthService authService;

    @Inject
    AuthMapper mapper;

    /**
     * Endpoint para autenticação de clientes na aplicação.
     * <p>
     * Recebe as credenciais de login (e-mail e senha), valida as informações
     * e retorna um token JWT e o ID do cliente
     * </p>
     *
     * @param dto Objeto {@link LoginRequestDTO} contendo as credenciais do cliente.
     * @return {@link LoginResponseDTO} contendo o token JWT e as informações básicas do cliente.
     */
    @POST
    @Path("/login")
    @Operation(summary = "Autentica cliente", description = "Valida credenciais e retorna token JWT")
    public LoginResponseDTO login(LoginRequestDTO dto) {
        return mapper.toDTO(authService.autenticar(dto.email(), dto.senha()));
    }
}
