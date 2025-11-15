package br.com.mattos.simuladorinvestimento.application.dto;

public record SimularInvestimentoRequestDTO(Long clienteId,Double valor,Integer prazoMeses,String tipoProduto) {

}

