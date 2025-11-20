package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "ClientePerfilRiscoResponse", description = "DTO com informações do perfil de risco do cliente")
public record ClientePerfilRiscoResponseDTO(
        @Schema(description = "ID do cliente", example = "1")
        Long clienteId,
        @Schema(description = "Nome do perfil de risco", example = "Conservador")
        String nomePerfil,
        @Schema(description = "Pontuação do perfil", example = "25")
        Integer pontuacao,
        @Schema(description = "Descrição detalhada do perfil", example = "Cliente com baixa tolerância a risco")
        String descricao
) {}
