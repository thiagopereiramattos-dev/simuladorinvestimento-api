package br.com.mattos.simuladorinvestimento.application.dto.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "ProdutoResponse", description = "DTO com informações do produto")
public record ProdutoResponseDTO(
        @Schema(description = "ID do produto", example = "1")
        Long id,
        @Schema(description = "Nome do produto", example = "CDB Banco X")
        String nome,
        @Schema(description = "Tipo do produto", example = "CDB")
        String tipo,
        @Schema(description = "Rentabilidade do produto", example = "0.12")
        Double rentabilidade,
        @Schema(description = "Risco do produto", example = "Baixo")
        String risco
){}
