package br.com.mattos.simuladorinvestimento.domain.exception;

/**
 * Exceção lançada quando um produto é considerado inválido no contexto da aplicação.
 */
public class SenhaInvalidaException extends DomainException {

    public SenhaInvalidaException() {
        super("Senha errada.");
    }
}
