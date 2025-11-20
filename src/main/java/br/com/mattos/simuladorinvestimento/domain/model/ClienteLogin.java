package br.com.mattos.simuladorinvestimento.domain.model;

public record ClienteLogin(
        Long id,
        String email,
        String senhaHash
) {}