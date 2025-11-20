package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.response.LoginResponseDTO;
import br.com.mattos.simuladorinvestimento.domain.model.AuthResultado;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Mapper responsável por converter objetos de domínio relacionados à autenticação
 * em DTOs utilizados pela camada de aplicação.
 */
@ApplicationScoped
public class AuthMapper {

    /**
     * Converte o resultado de autenticação {@link AuthResultado} em um
     * DTO {@link LoginResponseDTO} que será retornado pela API.
     *
     * @param resultado Objeto contendo o ID do cliente e o token JWT
     * @return DTO contendo clientId e token pronto para resposta HTTP
     */
    public LoginResponseDTO toDTO(AuthResultado resultado) {
        return new LoginResponseDTO(resultado.token(), resultado.clienteId());
    }
}
