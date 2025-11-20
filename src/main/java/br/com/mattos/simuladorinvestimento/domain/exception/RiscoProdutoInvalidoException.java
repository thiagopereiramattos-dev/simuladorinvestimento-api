package br.com.mattos.simuladorinvestimento.domain.exception;

/**
 * Exceção lançada quando um produto é considerado inválido no contexto da aplicação.
 */
public class RiscoProdutoInvalidoException extends DomainException {

    /**
     * @param mensagem mensagem explicando por que o produto é inválido
     */
    public RiscoProdutoInvalidoException(String mensagem) {
        super("Produto Invalido: ");
    }
}
