package br.com.mattos.simuladorinvestimento.application.dto.response;

public record ServicoTelemetriaDTO(
        String nome,
        Integer quantidadeChamadas,
        Integer mediaTempoRespostaMs
) {}