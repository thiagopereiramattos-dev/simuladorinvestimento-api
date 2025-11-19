package br.com.mattos.simuladorinvestimento.domain.model;

public record ClientePerfilRisco(
        Long clientId,
        String nomePerfil,
        Integer pontuacao,
        String descPerfil
) {}
