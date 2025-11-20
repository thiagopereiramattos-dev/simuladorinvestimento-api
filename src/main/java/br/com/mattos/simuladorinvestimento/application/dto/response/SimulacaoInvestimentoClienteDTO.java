package br.com.mattos.simuladorinvestimento.application.dto.response;

public record SimulacaoInvestimentoClienteDTO(
        Long id,
        String tipo,
        String valor,
        Double rentabilidade,
        String data
) {}

