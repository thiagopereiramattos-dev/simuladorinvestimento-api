package br.com.mattos.simuladorinvestimento.domain.exception;

/**
 * Exceção lançada quando um produto é considerado inválido no contexto da aplicação.
 */
public class TipoProdutoInvalidoException extends DomainException {

    public TipoProdutoInvalidoException() {
        super("Tipo de Produto Invalido.");
    }
}
