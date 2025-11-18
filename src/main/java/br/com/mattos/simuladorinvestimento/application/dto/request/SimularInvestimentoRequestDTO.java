package br.com.mattos.simuladorinvestimento.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SimularInvestimentoRequestDTO(@NotNull @Positive Integer clienteId, @NotNull @Positive Double valor,
                                            @NotNull @Positive Integer prazoMeses,@NotBlank String tipoProduto) {

}
