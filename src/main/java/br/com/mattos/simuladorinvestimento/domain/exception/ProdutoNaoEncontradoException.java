package br.com.mattos.simuladorinvestimento.domain.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String tipoProduto) {
        super("Produto do tipo '" + tipoProduto + "' não encontrado.");
    }
}
