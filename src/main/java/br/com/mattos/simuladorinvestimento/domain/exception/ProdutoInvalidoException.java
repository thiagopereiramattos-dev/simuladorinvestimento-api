package br.com.mattos.simuladorinvestimento.domain.exception;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
