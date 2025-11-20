package br.com.mattos.simuladorinvestimento.domain.exception;

/**
 * Superclasse para todas as exceções de domínio da aplicação.
 * Permite que o ExceptionMapper capture apenas exceções de negócio.
 */
public abstract class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
