package br.com.mattos.simuladorinvestimento.application.dto.response;

public record ProdutoSimulacaoResponseDTO(
        Long id,
        String nome,
        String tipo,
        Double rentabilidade,
        String risco
) {}

