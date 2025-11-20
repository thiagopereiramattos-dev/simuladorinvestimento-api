package br.com.mattos.simuladorinvestimento.domain.exception;

/**
 * Exceção lançada para quando não é encontrado um cliente
 */
public class ClienteNaoEncontradoException extends DomainException {

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
