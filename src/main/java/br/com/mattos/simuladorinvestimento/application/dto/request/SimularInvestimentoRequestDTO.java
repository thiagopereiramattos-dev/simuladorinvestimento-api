package br.com.mattos.simuladorinvestimento.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/** DTO para requisição de simulação de investimento. */
public record SimularInvestimentoRequestDTO(@NotNull @Positive Integer clienteId, @NotNull @Positive Double valor,
                                            @NotNull @Positive Integer prazoMeses,@NotBlank String tipoProduto) {

}
