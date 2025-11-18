package br.com.mattos.simuladorinvestimento.application.dto.response;

/** DTO de resposta contendo informações do produto. */
public record ProdutoResponseDTO(Integer id,String nome,String tipo,Double rentabilidade,String risco) {

}
