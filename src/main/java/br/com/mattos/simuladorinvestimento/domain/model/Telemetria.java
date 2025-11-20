package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.LocalDate;

public record Telemetria(
        String nome,
        Integer quantidadeChamadas,
        Integer mediaTempoRespostaMs,
        LocalDate dataInicio,
        LocalDate dataFim
) {}
