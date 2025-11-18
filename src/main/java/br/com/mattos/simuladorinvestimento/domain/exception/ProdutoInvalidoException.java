package br.com.mattos.simuladorinvestimento.domain.exception;

/**
 * Exceção lançada quando um produto é considerado inválido no contexto da aplicação.
 */
public class ProdutoInvalidoException extends RuntimeException {

    /**
     * @param mensagem mensagem explicando por que o produto é inválido
     */
    public ProdutoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
