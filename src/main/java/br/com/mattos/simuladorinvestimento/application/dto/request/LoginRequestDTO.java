package br.com.mattos.simuladorinvestimento.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "LoginRequest", description = "DTO para autenticação do cliente")
public record LoginRequestDTO(
        @NotBlank
        @Schema(description = "E-mail do cliente", example = "cliente@email.com", required = true)
        String email,
        @NotBlank
        @Schema(description = "Senha do cliente", example = "senha123", required = true)
        String senha

) {}
