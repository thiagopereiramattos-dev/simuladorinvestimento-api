package br.com.mattos.simuladorinvestimento.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LoginRequestDTO(
        @NotBlank String email,
        @NotBlank String senha

) {}
