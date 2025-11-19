package br.com.mattos.simuladorinvestimento.application.dto.response;

public record ClientePerfilRiscoResponseDTO(
        Long clienteId,
        String nomePerfil,
        Integer pontuacao,
        String descricao
) {}
