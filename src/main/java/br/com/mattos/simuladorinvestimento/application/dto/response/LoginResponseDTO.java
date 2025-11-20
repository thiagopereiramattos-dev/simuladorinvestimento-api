package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "LoginResponse", description = "DTO retornado após autenticação")
public record LoginResponseDTO(
        @Schema(description = "Token JWT de autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token,
        @Schema(description = "ID do cliente autenticado", example = "1")
        Long clientId
){}
