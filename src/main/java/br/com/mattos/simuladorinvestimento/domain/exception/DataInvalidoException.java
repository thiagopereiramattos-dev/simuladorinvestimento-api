package br.com.mattos.simuladorinvestimento.domain.exception;

public class DataInvalidoException extends DomainException {


    public DataInvalidoException() {
        super("Data Invalida" );
    }
}