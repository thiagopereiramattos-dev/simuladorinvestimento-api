package br.com.mattos.simuladorinvestimento.domain.model;

import java.time.LocalDate;

public record ResultadoConsultaSimulacaoPorDia (
        String produto,
        LocalDate data,
        Long quantidadeSimulacoes,
        Double mediaValorFinal
) {}
