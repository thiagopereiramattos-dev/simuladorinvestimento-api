package br.com.mattos.simuladorinvestimento.domain.exception;

public class SimulacaoNaoEncontradaException extends RuntimeException {
    public SimulacaoNaoEncontradaException(String message) {
        super(message);
    }
}
