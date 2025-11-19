package br.com.mattos.simuladorinvestimento.domain.model;

public record Cliente(
        Long id,
        String nome,
        String cpf,
        String email,
        String perfilRisco
) {}