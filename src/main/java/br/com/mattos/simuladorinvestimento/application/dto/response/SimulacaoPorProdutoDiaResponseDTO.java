package br.com.mattos.simuladorinvestimento.application.dto.response;

import java.time.LocalDate;

public record SimulacaoPorProdutoDiaResponseDTO(
        String produto,
        LocalDate data,
        Long quantidadeSimulacoes,
        String mediaValorFinal
) {}
