package br.com.mattos.simuladorinvestimento.domain.exception;

/**
 * Exceção lançada para quando não é encontrado um produto do tipo especificado.
 */
public class ProdutoNaoEncontradoException extends RuntimeException {

    /**
     * @param tipoProduto o tipo de produto que não foi encontrado
     */
    public ProdutoNaoEncontradoException(String tipoProduto) {
        super("Produto do tipo '" + tipoProduto + "' não encontrado.");
    }
}
