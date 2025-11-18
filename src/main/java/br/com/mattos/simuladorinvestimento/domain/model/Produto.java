package br.com.mattos.simuladorinvestimento.domain.model;

/** Produto financeiro com seus atributos principais. */
public record Produto(Integer id,String nome,String tipo,Double rentabilidade,String risco) {

}
