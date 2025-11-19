package br.com.mattos.simuladorinvestimento.application.mapper;

import br.com.mattos.simuladorinvestimento.application.dto.response.ClientePerfilRiscoResponseDTO;
import br.com.mattos.simuladorinvestimento.domain.model.ClientePerfilRisco;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Mapper responsável por converter {@link ClientePerfilRisco} (domínio)
 * para {@link ClientePerfilRiscoResponseDTO} (API).
 */
@ApplicationScoped
public class ClientePerfilRiscoMapper {

    /**
     * Converte record de domínio {@link ClientePerfilRisco} para DTO de resposta {@link ClientePerfilRiscoResponseDTO}.
     *
     * @param perfil Record de domínio
     * @return DTO de resposta correspondente
     */
    public ClientePerfilRiscoResponseDTO toResponse(ClientePerfilRisco perfil) {
        if (perfil == null) return null;

        return new ClientePerfilRiscoResponseDTO(
                perfil.clientId(),
                perfil.nomePerfil(),
                perfil.pontuacao(),
                perfil.descPerfil()
        );
    }
}
